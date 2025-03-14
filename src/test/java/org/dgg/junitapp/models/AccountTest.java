package org.dgg.junitapp.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void testAccountName(){
        Account account = new Account("John Doe", new BigDecimal("1000.12345"));
        assertEquals("John Doe", account.getPerson());
    }

    @Test
    void testAccountBalance(){
        Account account = new Account("John Doe", new BigDecimal("1000.12345"));
        assertEquals(new BigDecimal("1000.12345"), account.getBalance());
        assertEquals(1000.12345, account.getBalance().doubleValue());
        assertFalse(new BigDecimal("1000.12345").compareTo(account.getBalance()) < 0);
        assertTrue(new BigDecimal("1000.12345").compareTo(account.getBalance()) >= 0);
    }

    @Test
    void accountReference() {
        Account account = new Account("John Doe", new BigDecimal("1000.12345"));
        Account account2 = new Account("John Doe", new BigDecimal("1000.12345"));
//        assertNotEquals(account2, account);
        assertEquals(account2, account);
    }

    @Test
    void testDebit() {
        Account account = new Account("John Doe", new BigDecimal("1000.12345"));
        account.debit(new BigDecimal("100"));
        assertNotNull(account.getBalance());
        assertEquals(new BigDecimal("900.12345"), account.getBalance());
        assertEquals("900.12345", account.getBalance().toPlainString());
    }

    @Test
    void testCredit() {
        Account account = new Account("John Doe", new BigDecimal("1000.12345"));
        account.credit(new BigDecimal("100"));
        assertNotNull(account.getBalance());
        assertEquals(new BigDecimal("1100.12345"), account.getBalance());
        assertEquals("1100.12345", account.getBalance().toPlainString());
    }
}