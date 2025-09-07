package com.linkedin.picpay.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linkedin.picpay.schemas.User;

public interface AccountRepo extends JpaRepository<User, Long> {
    Optional<User> findByPixkey(String pix);
    Optional<User> findByEmail(String email);
}
