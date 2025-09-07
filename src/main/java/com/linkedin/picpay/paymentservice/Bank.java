package com.linkedin.picpay.paymentservice;


import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.linkedin.picpay.paymentservice.DTO.BankDTO;

import org.springframework.web.client.DefaultResponseErrorHandler;

@Service
public class Bank {
    private final RestTemplate rest;
    private String url = "https://util.devi.tools/api/v2/authorize";

    public Bank() {
        this.rest = new RestTemplate();
        this.rest.setErrorHandler(new DefaultResponseErrorHandler() {
            //https://util.devi.tools/api/v2/authorize ->  403 
            @Override
            public boolean hasError(ClientHttpResponse response) {
                return false;
            }
        });
    }

    public BankDTO bankStatus() {
        ResponseEntity<BankDTO> response = rest.exchange(url, HttpMethod.GET, null, BankDTO.class);
        return response.getBody();
    }

    public boolean bankIsActive() {
        BankDTO dto = bankStatus();
        return dto != null && dto.getData() != null && dto.getData().isAuthorization();
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
