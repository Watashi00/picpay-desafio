package com.linkedin.picpay.core;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "pixkey", unique = true, nullable = false, updatable = false)
  private String pixkey;

  @Column(name = "firstName", nullable = false)
  private String firstName;

  @Column(name = "lastName", nullable = false)
  private String lastName;

  @Column(name = "document", nullable = false, unique = true)
  private String document;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "typeAcc", nullable = false)
  private AccountsType typeAcc;

  @Column(name = "balance")
  private BigDecimal balance;

  @Column(name = "credit")
  private BigDecimal credit;

  @Column(name = "email")
  private String email;

  protected Account(
    String document,
    String firstName,
    String lastName,
    String password,
    boolean isEnterprise
  ) {
    this.setDocument(document);
    this.setFirstName(firstName);
    this.setLastName(lastName);
    this.setPassword(password);
    if (isEnterprise) this.isEnterprise();
    else this.isCommon();
  }

  private void isEnterprise() {
    this.balance = new BigDecimal(0);
    this.credit = new BigDecimal(1000);
  }

  private void isCommon() {
    this.balance = new BigDecimal(0);
    this.credit = new BigDecimal(0);
  }
}
