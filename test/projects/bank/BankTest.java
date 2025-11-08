// package projects.bank;

// import static org.junit.jupiter.api.Assertions.*;

// import java.io.IOException;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;


// public class BankTest {
//     private Bank bank;
//     private Account acct;

//     @BeforeEach
//     void setup() {
//         bank = new Bank();
//         acct = new CheckingAccount(
//             "id0",
//             "Owner Name",
//             1.0
//         );
//     }

//     // tests for add method

//     @Test
//     void testAddDataValidation(){
//         Exception e = assertThrows(
//             IllegalArgumentException.class,
//             () -> {bank.add(null);}
//         );
//         assertEquals("account must not be null.", e.getMessage());
//     }

//     @Test
//     void testAddAccount() {
//         // add account that's absent from bank's accounts
//         boolean addAccountResult = bank.add(acct);
//         assertEquals(
//             true,
//             addAccountResult,
//             "bank.add should return true"
//         );
//         assertEquals(
//             1,
//             bank.getCount(),
//             "bank.getCount should be 1"
//         );

//         // add account that's present in bank's accounts (no effect)
//         addAccountResult = bank.add(acct);
//         assertEquals(
//             false,
//             addAccountResult,
//             "bank.add should return false"
//         );
//         assertEquals(
//             1,
//             bank.getCount(),
//             "bank.getCount should still be 1"
//         );
//     }

//     @Test
//     void testAddAccountOverflow() {
//         for (int idx = 0; idx <= 100; idx++) {
//             Integer id = idx;
//             bank.add(
//                 new SavingsAccount(
//                     id.toString(),
//                     "Owner Name",
//                     1.0
//                 )
//             );
//         }

//         // also serves as a test for getCount
//         assertEquals(
//             101,
//             bank.getCount(),
//             "bank should hold 101 accounts"
//         );
//     }

//     // tests for find method

//     @Test
//     void testFindDataValidation() {
//         Exception e = assertThrows(
//             IllegalArgumentException.class,
//             () -> {bank.find(null);}
//         );
//         assertEquals("accountID must not be null.", e.getMessage());
//     }

//     @Test
//     void testFind() {
//         bank.add(acct);
//         assertEquals(
//             0,
//             bank.find(acct.getID()),
//             "acct should be at index 0"
//         );
//         assertEquals(
//             -1,
//             bank.find("id1"),
//             "result should be -1 when finding absent account"
//         );
//     }

//     // CSV I/O tests for phase 2

//     @Test
//     void testLoadAccountsFail() {
//         assertEquals(false, bank.loadAccounts("not/a/real.file"));
//     }

//     @Test
//     void testLoadAccounts() {
//         String accountsFilename = "data/testaccounts.csv";
//         boolean result = bank.loadAccounts(accountsFilename);
//         assertEquals(
//             true,
//             result
//         );
//         Account[] accounts = bank.getAccounts();
//         assertEquals(
//             2,
//             bank.getCount()
//         );

//         // check validity of stored rental item
//         Account account = accounts[0];
//         assertEquals(
//             "wz240833",
//             account.getID()
//         );
//         assertEquals(
//             "Anna Gomez",
//             account.getOwnerName()
//         );
//         assertEquals(
//             AccountType.SAVINGS,
//             account.getType()
//         );
//         assertEquals(
//             8111.00,
//             account.getBalance(),
//             1e-2
//         );

//         account = accounts[1];
//         assertEquals(
//             "hr108256",
//             account.getID()
//         );
//         assertEquals(
//             "Anna Gomez",
//             account.getOwnerName()
//         );
//         assertEquals(
//             AccountType.CHECKING,
//             account.getType()
//         );
//         assertEquals(
//             1715.18,
//             account.getBalance(),
//             1e-2
//         );
//     } // end: testLoadAccounts

//     @Test
//     void testWriteAccountsFail() {
//         assertEquals(false, bank.writeAccounts("not/a/real.file"));
//     }

//     @Test
//     void testWriteAccounts() {
//         // loading rental items already tested
//         String accountsFilename = "data/testaccounts.csv";
//         boolean result = bank.loadAccounts(accountsFilename);
//         assertEquals(true, result);
        
//         // write
//         accountsFilename = "data/testaccounts-out.csv";
//         bank.writeAccounts(accountsFilename);

//         // reload and compare
//         Bank bankReload = new Bank();
//         bankReload.loadAccounts(accountsFilename);
        
//         assertEquals(bank.getCount(), bankReload.getCount());

//         Account[] bankAccounts = bank.getAccounts();
//         Account[] bankReloadAccounts = bank.getAccounts();
//         assertEquals(bankAccounts.length, bankReloadAccounts.length);
//         for (int idx = 0; idx < bank.getCount(); idx++) {
//             assertEquals(
//                 bankAccounts[idx],
//                 bankReloadAccounts[idx],
//                 String.format("Rental item %s should match.", idx)
//             );
//         }
//     }

//     // phase 3

//     @Test
//     void testProcessTransactionsFailure() {
//         int nTx = bank.processTransactions("not/a/real.csv");
//         assertEquals(0, nTx);
//     }

//     @Test
//     void testProcesTransactionsSuccess() {
//         // testtransactions.csv has 4 lines
//         int nTx = bank.processTransactions("data/testtransactions.csv");
//         assertEquals(4, nTx);
//     }

// } // end: class BankTest

