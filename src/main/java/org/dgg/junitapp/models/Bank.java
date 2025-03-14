package org.dgg.junitapp.models;

import java.math.BigDecimal;

public class Bank {
    private String name;

    public Bank(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void transfer(Account from, Account to, BigDecimal amount) {
        from.debit(amount);
        to.credit(amount);
    }
}
