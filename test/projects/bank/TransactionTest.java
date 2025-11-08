// package projects.bank;

// import static org.junit.jupiter.api.Assertions.*;

// import java.io.IOException;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// public class TransactionTest {

//     private Account ckgAccount;
//     private Transaction defaultDeposit, defaultWithdrawal;
//     private Audit audit;

//     @BeforeEach
//     void setUp() {
//         ckgAccount = new CheckingAccount("id", "owner", 10.0);
//         defaultDeposit = new Deposit("id", 2.51);
//         defaultWithdrawal = new Withdrawal("id", 1.75);
//         String fileName = "test/audittest.log";
//         try {
//             audit = new Audit(fileName);
//         } catch (IOException e) {e.printStackTrace();}
//     }

//     @Test
//     void testConstructorDataValidation() {

//         /* Tests logic in Transaction class, 
//            so I can use either subclass here */

//         Exception e = assertThrows(
//             IllegalArgumentException.class,
//             () -> {new Deposit(null, 0.01);}
//         );
//         assertEquals(
//             "Parameter accountID cannot be null.",
//             e.getMessage()
//         );
        
//         e = assertThrows(
//             IllegalArgumentException.class,
//             () -> {new Deposit("id", -0.01);}
//         );
//         assertEquals(
//             "Parameter amount must be positive.",
//             e.getMessage()
//         );
//     }

//     @Test
//     void testMakeThrowsOnNullInput() {
        
//         /* Tests logic in Transaction class, 
//            so I can use either subclass here */

//         Exception e = assertThrows(
//             IllegalArgumentException.class,
//             () -> {Transaction.make(null);}
//         );
//         assertEquals(
//             "Parameter inputLine cannot be null.",
//             e.getMessage()
//         );
//     }

//     @Test
//     void testMakePreservesData() {
//         String[] tokens1 = {"withdrawal", "rp332960", "267.57"};
//         String[] tokens2 = {"deposit", "vc906780", "766.53"};

//         Transaction tx1 = Transaction.make(String.join(",", tokens1));
//         assertEquals(Withdrawal.class, tx1.getClass());
//         assertEquals(tokens1[1], tx1.getAccountID());
//         assertEquals(Double.valueOf(tokens1[2]), tx1.getAmount(), 0.01);

//         Transaction tx2 = Transaction.make(String.join(",", tokens2));
//         assertEquals(Deposit.class, tx2.getClass());
//         assertEquals(tokens2[1], tx2.getAccountID());
//         assertEquals(Double.valueOf(tokens2[2]), tx2.getAmount(), 0.02);
//     }

//     @Test
//     void testValidateDeposit() {
//         Transaction tx = new Deposit(ckgAccount.getID(), 11.0);
//         assertEquals(true, tx.validate(ckgAccount, audit));
//     }

//     @Test
//     void testValidateWithdrawal() {
//         Transaction tx1 = new Withdrawal(ckgAccount.getID(), 11.0);
//         assertEquals(false, tx1.validate(ckgAccount, audit));
//         Transaction tx2 = new Withdrawal(ckgAccount.getID(), 9.90);
//         assertEquals(true, tx2.validate(ckgAccount, audit));
//     }

//     @Test
//     void testExecuteDeposit() {
//         // test that depositing increases balance as expected
//         double initBalance = ckgAccount.getBalance();
//         defaultDeposit.execute(ckgAccount, audit);
//         assertEquals(
//             initBalance + defaultDeposit.getAmount(),
//             ckgAccount.getBalance()
//         );
//     }

//     @Test
//     void testExecuteWithdrawal() {
//         // test that withdrawing decreases balance as expected
//         double initBalance = ckgAccount.getBalance();
//         defaultWithdrawal.execute(ckgAccount, audit);
//         assertEquals(
//             initBalance - defaultWithdrawal.getAmount(),
//             ckgAccount.getBalance()
//         );
//     }

// }
