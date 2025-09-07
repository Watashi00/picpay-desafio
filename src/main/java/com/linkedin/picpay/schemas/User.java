package com.linkedin.picpay.schemas;

import com.linkedin.picpay.core.Account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(name = "fullname")
    private String fullname = this.getFirstName() +' '+ this.getLastName();

    public User(String document, String firstName, String lastName, String password, boolean isEnterprise) {
        super(document, firstName, lastName, password, isEnterprise);
    }


    @Override
    public String toString() {
        StringBuilder uStringReturn = new StringBuilder();

        uStringReturn.append("====================\n");
        uStringReturn.append("ID: ").append(getId()).append("\n");
        uStringReturn.append("Fullname: ").append(getFullname()).append("\n");
        uStringReturn.append("CPF: ").append(getDocument()).append("\n");
        uStringReturn.append("Email: ").append(getEmail()).append("\n");
        uStringReturn.append("Tipo: ").append(getTypeAcc()).append("\n");
        uStringReturn.append("Saldo: ").append(getBalance()).append("\n");
        uStringReturn.append("CREDITO: ").append(getCredit()).append("\n");
        uStringReturn.append("====================");

        return uStringReturn.toString();
    }

}
