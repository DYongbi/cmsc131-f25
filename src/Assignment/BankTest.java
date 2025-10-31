<dependency>
<groupId>org.junit.jupiter</groupId>
<artifactId>junit-jupiter-api</artifactId>
<version>5.10.2</version>
<scope>test</scope>
</dependency>
import org.junit.jupiter.api.*;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {

private Bank bank;
private static final String TEST_FILE = "test_accounts.csv";

@BeforeEach
void setUp() {
bank = new Bank(5);
new File(TEST_FILE).delete(); // clean old test file
}

@Test
void testWriteAccounts() {
Account acc1 = new Account("T001", "Tester One", "Checking", 1500.0);
Account acc2 = new Account("T002", "Tester Two", "Savings", 2200.0);

bank.add(acc1);
bank.add(acc2);

boolean result = bank.writeAccounts(TEST_FILE);
assertTrue(result, "File writing should succeed");

File file = new File(TEST_FILE);
assertTrue(file.exists(), "CSV file should be created");
assertTrue(file.length() > 0, "CSV file should not be empty");
}

@Test
void testLoadAccounts() {
// Write first
testWriteAccounts();

// Now read back into a new Bank
Bank newBank = new Bank(5);
newBank.loadAccounts(TEST_FILE);

assertEquals(2, newBank.getCount(), "Should load 2 accounts");
assertNotNull(newBank.find("T001"), "Account T001 should exist");
assertNotNull(newBank.find("T002"), "Account T002 should exist");
}

@AfterEach
void tearDown() {
new File(TEST_FILE).delete(); // clean after test
}
}