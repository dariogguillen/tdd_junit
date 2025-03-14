package org.dgg.junitapp.models;

import java.math.BigDecimal;

public class Account {
    private String person;
    private BigDecimal balance;

    public Account(String person, BigDecimal balance) {
        this.balance = balance;
        this.person = person;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public void debit(BigDecimal amount) {
    }

    public void credit(BigDecimal amount) {
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Account account)) {
            return false;
        }
        if (this.person == null || this.balance == null) {
            return false;
        }
        return (account.getPerson().equals(this.person) && account.getBalance().equals(this.balance));
    }
}
