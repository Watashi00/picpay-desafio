package com.linkedin.picpay.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.linkedin.picpay.core.AccountsType;
import com.linkedin.picpay.paymentservice.Bank;
import com.linkedin.picpay.repo.AccountRepo;
import com.linkedin.picpay.schemas.User;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    private final AccountRepo serviceAccountRepo;
    private final Bank bank;
    UserService(AccountRepo repo, Bank bank) {
        this.serviceAccountRepo = repo;
        this.bank = bank;
    }

    public void createUser(User u) {
        serviceAccountRepo.save(u);
    }
    

    @Transactional
    public String deposity(String token, Integer value) {
        if (!bank.bankIsActive()) return "Error: Bank is not active to dep code: 500";
        Optional<User> u = serviceAccountRepo.findByEmail(token);
        if (u.isPresent()) {
            User user = u.get();
            BigDecimal newBalance = user.getBalance().add(BigDecimal.valueOf(value));
            user.setBalance(newBalance);
            serviceAccountRepo.save(user);
            return "Deposit efetuado code:200";
        }
        return "Error: UserNotFound code: 404";
    }
    public Optional<User> findByEmail(String email) {
        return serviceAccountRepo.findByEmail(email);
    }


    @Transactional
    public String sendToPixkey(String email, String pix, Integer value) {
        if (!bank.bankIsActive())
            return "Error: Bank is not active to transaction code: 500";

        Optional<User> from = serviceAccountRepo.findByEmail(email); // email vindo do "token"
        Optional<User> to = serviceAccountRepo.findByPixkey(pix);

        if (from.isPresent() && to.isPresent()) {
            User fromUser = from.get();
            User toUser = to.get();

            if (fromUser.getTypeAcc() == AccountsType.ENTERPRISE) {
                return "Error: Unauthorized - ENTERPRISE accounts cannot send money code: 403";
            }

            boolean fromBalance = fromUser.getBalance().compareTo(new BigDecimal(value)) >= 0;
            if (!fromBalance) return "Error: Insufficient balance code: 403";

            // debita e credita
            fromUser.setBalance(fromUser.getBalance().subtract(new BigDecimal(value)));
            toUser.setBalance(toUser.getBalance().add(new BigDecimal(value)));

            serviceAccountRepo.save(fromUser);
            serviceAccountRepo.save(toUser);

            return String.format("%s : %d ---> %s", fromUser.getFirstName(), value, toUser.getFirstName());
        }
        return "Error: UserNotFound code: 404";
    }

}
