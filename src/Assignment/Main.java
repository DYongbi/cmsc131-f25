package Assignment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    
    public static void main(String[] args) {
        phase1();
        phase2();
        phase3();
    }

    public static void phase1() {
        String logName = "data/phase1.log";
        try {
            FileWriter writer = new FileWriter(new File(logName));

            Account acct = new Account("id1", "Owner Name", "SAVINGS", 1.0);
            
            writer.write(
                String.format(
                    "Account setup: acct id=%s, owner=%s, balance=%f",
                    acct.getAccountID(),
                    acct.getOwnerName(),
                    acct.getBalance()
                ) + System.lineSeparator()
            );

            Bank bank = new Bank();
            int numAccounts0 = bank.getCount();
            int findAcct0 = bank.find(acct.getAccountID()); // TODO fix return value

            boolean addResult = bank.add(acct); // TODO fix return value
            int numAccounts1 = bank.getCount();
            int findAcct1 = bank.find(acct.getAccountID()); // TODO fix return value

            writer.write(
                String.format(
                    "Bank init: getCount=%d, find=%d",
                    numAccounts0, 
                    findAcct0
                ) + System.lineSeparator()
            );

            writer.write(
                String.format(
                    "After add: result=%b, getCount=%d, find=%d",
                    addResult,
                    numAccounts1, 
                    findAcct1
                ) + System.lineSeparator()
            );

            writer.close();
        } catch (IOException e) { e.printStackTrace(); }

    }

    public static void phase2() {
        String accountsFilename = "data/accounts.csv";
        Bank bank = new Bank();
        boolean result = bank.loadAccounts(accountsFilename); // TODO implement

        System.out.println("Result of loading account: " + result);
        System.out.println("Number of accounts: " + bank.getCount());

        String outputFilename = "data/phase2.csv";
        result = bank.writeAccounts(outputFilename); // TODO implement
        System.out.println("Result of writing account: " + result);
    }

    public static void phase3() {
        Bank bank = new Bank();
        bank.loadAccounts("data/accounts.csv"); // ignore output // TODO implement
        int step2 = bank.processTransactions("data/transactions.csv"); // TODO implement
        bank.writeAccounts("data/accounts.csv"); // TODO implement
        System.out.println("Number of transactions processed: " + step2); // ignore output
    }

}
