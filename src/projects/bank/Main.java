package projects.bank;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        phase1();
        phase2();
        phases3And4();
    }

    public static void phase1() {
        String logName = "data/phase1.log";
        try (FileWriter writer = new FileWriter(new File(logName))) {

            Account acct = new SavingsAccount("id1", "Owner Name", 1.0);
            writer.write(String.format("Account setup: acct id=%s, owner=%s, balance=%.2f",
                    acct.getID(), acct.getOwnerName(), acct.getBalance()) + System.lineSeparator());

            Bank bank = new Bank();
            int numAccounts0 = bank.getCount();
            int findAcct0 = bank.find(acct.getID());

            boolean addResult = bank.add(acct);
            int numAccounts1 = bank.getCount();
            int findAcct1 = bank.find(acct.getID());

            writer.write(String.format("Bank init: getCount=%d, find=%d", numAccounts0, findAcct0) + System.lineSeparator());
            writer.write(String.format("After add: result=%b, getCount=%d, find=%d", addResult, numAccounts1, findAcct1) + System.lineSeparator());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void phase2() {
        Bank bank = new Bank();
        boolean result = bank.loadAccounts("data/accounts.csv");
        System.out.println("Result of loading account: " + result);
        System.out.println("Number of accounts: " + bank.getCount());

        result = bank.writeAccounts("data/phase2.csv");
        System.out.println("Result of writing account: " + result);
    }

    public static void phases3And4() {
        Bank bank = new Bank();
        bank.loadAccounts("data/accounts.csv");
        int txProcessed = bank.processTransactions("data/transactions.csv");
        bank.writeAccounts("data/accounts-Phase3Out.csv");
        System.out.println("Number of transactions processed: " + txProcessed);
    }
}
