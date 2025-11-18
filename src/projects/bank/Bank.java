package projects.bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Bank {

    private Account[] accounts;
    private int idxNextAccount;
    private final int NEW_ACCOUNTS_INCREMENT = 100;

    public Bank() {
        accounts = new Account[NEW_ACCOUNTS_INCREMENT];
    }

    public boolean add(Account acct) {
        if (acct == null) throw new IllegalArgumentException("account must not be null");
        if (find(acct.getID()) == null) {
            if (idxNextAccount >= accounts.length) {
                Account[] newAccounts = new Account[accounts.length + NEW_ACCOUNTS_INCREMENT];
                System.arraycopy(accounts, 0, newAccounts, 0, accounts.length);
                accounts = newAccounts;
            }
            accounts[idxNextAccount++] = acct;
            return true;
        }
        return false;
    }

    public Account find(String accountID) {
        if (accountID == null) throw new IllegalArgumentException("accountID must not be null");
        for (int i = 0; i < idxNextAccount; i++) {
            if (accounts[i].getID().equals(accountID)) {
                return accounts[i];
            }
        }
        return null; 
    }
    
    // START OF NEW CODE for Interactive Lookup
    
    /**
     * Searches for all accounts belonging to a given owner name.
     */
    public Account[] findByOwnerName(String ownerName) {
        if (ownerName == null || ownerName.isEmpty()) {
            return new Account[0];
        }
        
        // Use a temporary array for simplicity
        Account[] matches = new Account[idxNextAccount];
        int matchCount = 0;
        
        for (int i = 0; i < idxNextAccount; i++) {
            // Case-insensitive comparison for user-friendly searching
            if (accounts[i].getOwnerName().equalsIgnoreCase(ownerName.trim())) {
                matches[matchCount++] = accounts[i];
            }
        }
        
        // Return a trimmed array containing only the matches
        Account[] result = new Account[matchCount];
        System.arraycopy(matches, 0, result, 0, matchCount);
        return result;
    }
    
    // END OF NEW CODE

    public int getCount() { return idxNextAccount; }

    public boolean loadAccounts(String filename) {
        try (Scanner scan = new Scanner(new File(filename))) {
            while (scan.hasNextLine()) {
                String csvString = scan.nextLine();
                Account account = Account.makeFromCSV(csvString); 
                if (account != null) {
                    add(account);
                } else {
                    System.err.println("ERROR: Could not create account from line: " + csvString);
                }
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
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
    
    public void doAllEndOfMonthActions(Audit audit) {
        audit.write("Beginning End-of-Month Processing");
        for (int i = 0; i < idxNextAccount; i++) {
            accounts[i].doEndOfMonthActions(audit);
        }
        audit.write("Completed End-of-Month Processing");
    }

    public int processTransactions(String filename, Audit audit) { 
        int numTxProcessed = 0;
        
        try (Scanner scan = new Scanner(new File(filename))) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                Transaction tx = Transaction.create(line); 

                if (tx == null) {
                    audit.write("UNKNOWN", "transaction-parse", 0.0, "ERROR: Invalid transaction format/type: " + line);
                } else {
                    Account acct = find(tx.getAccountID()); 

                    if (acct == null) {
                        audit.write(tx.getAccountID(), "transaction", tx.getAmount(), "ERROR: Account not found");
                    } else {
                        tx.execute(acct, audit); 
                        numTxProcessed++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // FIX: Uses the correct single-argument write() method
            audit.write("ERROR: Transactions file not found: " + filename);
            e.printStackTrace();
        }
        
        return numTxProcessed;
    }
}