package com.linkedin.picpay.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.Cache;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linkedin.picpay.paymentservice.DTO.PaymentDTO;
import com.linkedin.picpay.services.UserService;

@RestController
@RequestMapping("/api/client")
public class UserController {

    private final CacheManager cacheManager;

    private final UserService userService;

    UserController(UserService userService, CacheManager cacheManager) {
        this.userService = userService;
        this.cacheManager = cacheManager;
    }

    @PostMapping("/pay")
    ResponseEntity<String> payFromTo(@RequestBody PaymentDTO request) {
        Cache cache = cacheManager.getCache("oAuth");
        if(cache != null) {
            var token = cache.get("token", String.class);
            userService.sendToPixkey(token, "pixtest", null);
        }
        

        return ResponseEntity.ok("Pagamento feito");
    }

}
