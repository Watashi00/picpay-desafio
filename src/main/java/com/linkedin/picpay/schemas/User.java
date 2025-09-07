package com.linkedin.picpay.schemas;

import com.linkedin.picpay.core.Account;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends Account {
    // No duplicate id here - inherited from Account (@MappedSuperclass)

    public User() {
        // JPA
    }

    public User(String document, String firstName, String lastName, String password, boolean isEnterprise) {
        super(document, firstName, lastName, password, isEnterprise);
    }

    public String getFullname() {
        String fn = this.getFirstName() == null ? "" : this.getFirstName();
        String ln = this.getLastName() == null ? "" : this.getLastName();
        return (fn + " " + ln).trim();
    }


    @Override
    public String toString() {
        StringBuilder uStringReturn = new StringBuilder();

        uStringReturn.append("====================\n");
        uStringReturn.append("ID: ").append(getId()).append("\n");
        uStringReturn.append("Fullname: ").append(getFullname()).append("\n");
        uStringReturn.append("CPF/CNPJ: ").append(getDocument()).append("\n");
        uStringReturn.append("Email: ").append(getEmail()).append("\n");
        uStringReturn.append("Tipo: ").append(getTypeAcc()).append("\n");
        uStringReturn.append("Saldo: ").append(getBalance()).append("\n");
        uStringReturn.append("CREDITO: ").append(getCredit()).append("\n");
        uStringReturn.append("PIXKEY: ").append(getPixkey()).append("\n");
        uStringReturn.append("====================\n");

        return uStringReturn.toString();
    }

}
