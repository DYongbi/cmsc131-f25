package projects.bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Bank {

    private Account[] accounts;
    private int idxNextAccount;
    private final int newAccountsIncrement = 100;

    public Bank() {
        accounts = new Account[newAccountsIncrement];
    }

    public boolean add(Account acct) {
        if (acct == null) throw new IllegalArgumentException("account must not be null");
        if (find(acct.getID()) == -1) {
            if (idxNextAccount >= accounts.length) {
                Account[] newAccounts = new Account[accounts.length + newAccountsIncrement];
                System.arraycopy(accounts, 0, newAccounts, 0, accounts.length);
                accounts = newAccounts;
            }
            accounts[idxNextAccount++] = acct;
            return true;
        }
        return false;
    }

    public int find(String accountID) {
        if (accountID == null) throw new IllegalArgumentException("accountID must not be null");
        for (int i = 0; i < idxNextAccount; i++) {
            if (accounts[i].getID().equals(accountID)) return i;
        }
        return -1;
    }

    public int getCount() { return idxNextAccount; }
    public Account[] getAccounts() { return accounts; }

    public boolean loadAccounts(String filename) {
        boolean result = true;
        try (Scanner scan = new Scanner(new File(filename))) {
            while (scan.hasNextLine()) {
                String csvString = scan.nextLine();
                Account account = Account.make(csvString);
                add(account);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public boolean writeAccounts(String filename) {
        try (FileWriter writer = new FileWriter(new File(filename))) {
            for (int i = 0; i < idxNextAccount; i++) {
                writer.write(accounts[i].toCSV() + System.lineSeparator());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int processTransactions(String filename) {
        int numTxProcessed = 0;
        try {
            // Part 4: Audit object creation
            Audit audit = new Audit("phase4.log");
            try (Scanner scan = new Scanner(new File(filename))) {
                while (scan.hasNextLine()) {
                    Transaction tx = Transaction.make(scan.nextLine());
                    int idx = find(tx.getAccountID());
                    if (idx >= 0) {
                        Account acct = accounts[idx];
                        // Part 3/4: Validate before executing
                        if (tx.validate(acct, audit)) {
                            tx.execute(acct, audit);
                        }
                    } else {
                        // Part 3/4: Account not found error
                        audit.recordNSA(tx);
                    }
                    numTxProcessed++;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            
            // Part 5: End-of-Month Actions
            for (int i = 0; i < idxNextAccount; i++) {
                accounts[i].doEndOfMonthActions(audit);
            }

            audit.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numTxProcessed;
    }
}