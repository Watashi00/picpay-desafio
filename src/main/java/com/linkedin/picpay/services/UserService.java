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

    public void createUser(String document, String firstName, String lastName, String password, boolean isEnterprise ) {
        User u = new User(document, firstName, lastName, password, isEnterprise);
        serviceAccountRepo.save(u);
    }
    

    public String deposity(String token, Integer value) {
        if(!bank.bankIsActive()) return "Error: Bank is not active to dep code: 500";
        Optional<User> u = serviceAccountRepo.findByPixkey("pixtest");
        if(u.isPresent()) {
            User user = u.get();
            BigDecimal newBalance = user.getBalance().add(new BigDecimal(value));
            user.setBalance(newBalance);
            serviceAccountRepo.save(user);
            return "deposity is efetued code:200";
        }
        return "Error: UserNotFound code: 404";
    }

    @Transactional
    public String sendToPixkey(String token, String pix , Integer value) {
        if(!bank.bankIsActive()) return "Error: Bank is not active to transaction code: 500";
        Optional<User> from = serviceAccountRepo.findByPixkey("pixtest");
        Optional<User> to = serviceAccountRepo.findByPixkey(pix);   
        
        if(from.isPresent() && to.isPresent() && from.get().getTypeAcc() != AccountsType.COMMON) {
            boolean fromBalance = from.get().getBalance().compareTo(new BigDecimal(value)) >= 0;
            if(!fromBalance) return "Error: Unauthorized code: 403";
            System.out.printf("%s : %d ---> %s ", from.get().getFirstName(), value, to.get().getFirstName());
        }
        return "Error: UserNotFound code: 404";
    }

}
