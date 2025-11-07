package projects.Bank;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bank {

    private List<Account> accounts;
    private final int newAccountsIncrement = 100;

    public Bank() {
        accounts = new ArrayList<>(newAccountsIncrement);
    }

    public boolean add(Account acct) {
        if (acct == null) {
            throw new IllegalArgumentException("account must not be null.");
        }
        if (find(acct.getID()) == -1) {
            accounts.add(acct);
            return true;
        } else {
            return false;
        }
    }

    public int find(String accountID) {
        if (accountID == null) {
            throw new IllegalArgumentException("accountID must not be null.");
        }
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getID().equals(accountID)) {
                return i;
            }
        }
        return -1;
    }

    public int getCount() {
        return accounts.size();
    }

    public boolean loadAccounts(String filename) {
        boolean result = true;
        File inputFile = new File(filename);
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Account account = Account.make(line);
                add(account);
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public boolean writeAccounts(String filename) {
        File file = new File(filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Account account : accounts) {
                writer.write(account.toCSV());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Account[] getAccounts() {
        return accounts.toArray(new Account[0]);
    }

    public int processTransactions(String filename) {
        throw new UnsupportedOperationException("Student must implement.");
    }

    public List<Account> findAccountsByOwner(String ownerName) {
        return accounts.stream()
                .filter(acc -> acc.getOwnerName().equalsIgnoreCase(ownerName))
                .collect(Collectors.toList());
    }
}
