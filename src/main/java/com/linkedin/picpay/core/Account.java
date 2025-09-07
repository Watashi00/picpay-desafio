package com.linkedin.picpay.core;

import java.math.BigDecimal;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class Account {
    private String firstName;
    private String lastName;
    private String document;
    private String password;
    private AccountsType typeAcc;
    private BigDecimal balance;
    private BigDecimal credit;
    private String email;

    protected Account(String document, String firstName, String lastName, String password, boolean isEnterprise) {
        this.setDocument(document);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPassword(password);
        this.typeAcc = (isEnterprise == true) ? AccountsType.ENTERPRISE : AccountsType.COMMON;
    }



}
