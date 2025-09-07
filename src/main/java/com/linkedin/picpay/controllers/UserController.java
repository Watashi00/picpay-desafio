package com.linkedin.picpay.controllers;

import com.linkedin.picpay.paymentservice.DTO.PaymentDTO;
import com.linkedin.picpay.schemas.User;
import com.linkedin.picpay.services.UserService;
import java.math.BigDecimal;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class UserController {


  private final UserService userService;

  UserController(UserService userService, CacheManager cacheManager) {
    this.userService = userService;
  }

  @PostMapping("/register")
  ResponseEntity<String> createAccount(@RequestBody User newUser) {
    userService.createUser(newUser);
    return ResponseEntity.ok().body(newUser.getEmail());
  }

  @PostMapping("/deposit")
  public ResponseEntity<String> deposit(
    @RequestParam String email,
    @RequestParam Integer value
  ) {
    String result = userService.deposity(email, value);
    return ResponseEntity.ok(result);
  }

  @PostMapping("/pay")
  public ResponseEntity<String> payFromTo(
      @RequestBody PaymentDTO request,
      @RequestHeader("Authorization") String authHeader
  ) {
      if (authHeader == null || !authHeader.startsWith("Bearer ")) {
          return ResponseEntity.status(401).body("Erro: token ausente ou inválido");
      }

      String email = authHeader.replace("Bearer ", "").trim();

      String result = userService.sendToPixkey(
          email,
          request.getTo(),
          request.getAmount().intValue()
      );

      return ResponseEntity.ok(result);
  }

  @GetMapping("/balance")
  public ResponseEntity<BigDecimal> getBalance(
    @RequestParam String email,
    @RequestHeader("Authorization") String authHeader
  ) {
    return userService
      .findByEmail(email)
      .map(user -> ResponseEntity.ok(user.getBalance()))
      .orElseGet(() -> ResponseEntity.status(404).body(BigDecimal.ZERO));
  }
}
