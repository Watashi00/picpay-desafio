package com.linkedin.picpay.paymentservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankDTO {
    private String status;
    private Data data;

    @Getter
    @Setter
    public class Data {
        private boolean authorization;
        
    }

    
}
