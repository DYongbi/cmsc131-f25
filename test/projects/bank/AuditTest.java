// package projects.bank;

// import static org.junit.jupiter.api.Assertions.*;

// import java.io.IOException;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import java.io.File;
// import java.util.Scanner;

// public class AuditTest {

//     private String fileName;
//     private Audit audit;

//     @BeforeEach
//     void setup() {
//         fileName = "test/audittest.log";
//         try {
//             audit = new Audit(fileName);
//         } catch (IOException e) {e.printStackTrace();}
//     }

//     @Test
//     void testRecordNSA() {
//         Transaction d = new Deposit("id", 1.11);
//         audit.recordNSA(d);
//         audit.close();

//         // compare audit file to expected result
//         Scanner scan;
//         try {
//             scan = new Scanner(new File(fileName));

//             // read the transaction line
//             assertEquals(true, scan.hasNextLine());
//             String line = scan.nextLine();
//             assertEquals(
//                 true,
//                 // avoiding timestamps
//                 line.contains("ERROR no such account: " + d.toString())
//             );

//             // confirm end of file
//             assertEquals(false, scan.hasNextLine());
//             scan.close();
//         } catch (IOException e) {e.printStackTrace();}
//     }

//     @Test
//     void testRecordNSF() {
//         Transaction w = new Withdrawal("id", 1.01);
//         Account a = new CheckingAccount("id", "owner", 1.0);
//         audit.recordNSF(w, a);
//         audit.close();

//         Scanner scan;
//         try {
//             scan = new Scanner(new File(fileName));

//             assertEquals(true, scan.hasNextLine());
//             String line = scan.nextLine();
//             assertEquals(
//                 true,
//                 line.contains("ERROR nonsufficient funds: " + w.toString())
//             );

//             assertEquals(false, scan.hasNextLine());
//             scan.close();
//         } catch (IOException e) {e.printStackTrace();}
//     }

//     @Test
//     void testRecordValid() {
//         Transaction w = new Withdrawal("id", 0.01);
//         Account a = new CheckingAccount("id", "owner", 1.0);
//         audit.recordValid(w, a);
//         audit.close();

//         Scanner scan;
//         try {
//             scan = new Scanner(new File(fileName));

//             assertEquals(true, scan.hasNextLine());
//             String line = scan.nextLine();
//             assertEquals(
//                 true,
//                 line.contains(
//                     String.format(
//                         "INFO: %s balance forward %.2f",
//                         w.toString(), a.getBalance()
//                     )
//                 )
//             );

//             assertEquals(false, scan.hasNextLine());
//             scan.close();
//         } catch (IOException e) {e.printStackTrace();}
//     }

// }
