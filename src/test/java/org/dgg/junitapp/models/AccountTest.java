package org.dgg.junitapp.models;

import org.dgg.junitapp.exceptions.NotEnoughMoneyException;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Account account;

    @BeforeEach
    void setUp() {
        this.account = new Account("John Doe", new BigDecimal("1000.12345"));
        System.out.println("Running test...");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test completed.");
    }

    @Test
    @DisplayName("Test account name")
    void testAccountName() {
        assertEquals("John Doe", account.getPerson());
    }

    @Test
    @DisplayName("Test account balance")
    void testAccountBalance() {
        assertEquals(new BigDecimal("1000.12345"), account.getBalance());
        assertEquals(1000.12345, account.getBalance().doubleValue());
        assertFalse(new BigDecimal("1000.12345").compareTo(account.getBalance()) < 0);
        assertTrue(new BigDecimal("1000.12345").compareTo(account.getBalance()) >= 0);
    }

    @Test
    @DisplayName("Test account reference")
    void accountReference() {
        Account account2 = new Account("John Doe", new BigDecimal("1000.12345"));
//        assertNotEquals(account2, account);
        assertEquals(account2, account);
    }

    @Test
    void testDebit() {
        account.debit(new BigDecimal("100"));
        assertNotNull(account.getBalance());
        assertEquals(new BigDecimal("900.12345"), account.getBalance());
        assertEquals("900.12345", account.getBalance().toPlainString());
    }

    @Test
    void testCredit() {
        account.credit(new BigDecimal("100"));
        assertNotNull(account.getBalance(), "Account can't be null");
        assertEquals(new BigDecimal("1100.12345"), account.getBalance(), "Account balance should be 1100.12345");
        assertEquals("1100.12345", account.getBalance().toPlainString());
    }

    @Test
    void testDebitNotEnoughMoney() {
        Exception e = assertThrows(NotEnoughMoneyException.class, () -> account.debit(new BigDecimal("1000.12346")));
        assertEquals("Not enough money", e.getMessage());
    }

    @Test
    void testTransfer() {
        Account to = new Account("Jane Doe", new BigDecimal("1000.12345"));
        Bank bank = new Bank("Bank of America");
        bank.transfer(account, to, new BigDecimal("100"));
        assertEquals(new BigDecimal("900.12345"), account.getBalance());
        assertEquals(new BigDecimal("1100.12345"), to.getBalance());
    }

    @Test
    void testBankAccountRelation() {
        Account to = new Account("Jane Doe", new BigDecimal("1000.12345"));
        Bank bank = new Bank("Bank of America");
        bank.addAccount(account);
        bank.addAccount(to);
        bank.transfer(account, to, new BigDecimal("100"));
        assertAll(
                () -> assertEquals(new BigDecimal("900.12345"), account.getBalance()),
                () -> assertEquals(new BigDecimal("1100.12345"), to.getBalance()),
                () -> assertEquals(bank, account.getBank()),
                () -> assertEquals(bank, to.getBank()),
                () -> assertEquals("Bank of America", account.getBank().getName()),
                () -> assertEquals(
                        "John Doe",
                        bank
                            .getAccounts()
                            .stream()
                            .filter(c -> c.getPerson().equals("John Doe"))
                            .findFirst()
                            .get()
                            .getPerson())
        );
    }

}