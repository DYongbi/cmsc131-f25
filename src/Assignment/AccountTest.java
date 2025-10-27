import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
        
        account = new Account("A1001", "John Doe", "Checking", 500.0);
    }

    @Test
    void constructorSetsValues() {
        assertEquals("A1001", account.getAccountID());
        assertEquals("John Doe", account.getOwnerName());
        assertEquals("Checking", account.getAccountType());
        assertEquals(500.0, account.getBalance());
    }

    @Test
    void constructorThrowsForEmptyID() {
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> { new Account("", "Jane Doe", "Checking", 200.0); }
        );
        assertEquals("Account ID must not be empty.", exception.getMessage());
    }

    @Test
    void constructorThrowsForEmptyOwnerName() {
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> { new Account("A2002", "", "Savings", 200.0); }
        );
        assertEquals("Owner name must not be empty.", exception.getMessage());
    }

    @Test
    void constructorThrowsForInvalidAccountType() {
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> { new Account("A3003", "Jane Doe", "Investment", 200.0); }
        );
        assertEquals("Account type must be 'Checking' or 'Savings'.", exception.getMessage());
    }

    @Test
    void constructorThrowsForNegativeBalance() {
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> { new Account("A4004", "Jane Doe", "Checking", -50.0); }
        );
        assertEquals("Balance cannot be negative.", exception.getMessage());
    }

    @Test
    void gettersReturnCorrectValues() {
        assertEquals("A1001", account.getAccountID());
        assertEquals("John Doe", account.getOwnerName());
        assertEquals("Checking", account.getAccountType());
        assertEquals(500.0, account.getBalance());
    }
}
