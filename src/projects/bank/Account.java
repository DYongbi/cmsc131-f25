package projects.bank;

import java.math.BigDecimal;
import java.math.RoundingMode;

abstract class Account {

    private final String id;
    private final String ownerName;
    private double balance;

    protected Account(String id, String ownerName, double balance) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("id cannot be null or empty");
        }
        if (ownerName == null || ownerName.isEmpty()) {
            throw new IllegalArgumentException("ownerName cannot be null or empty");
        }
        this.id = id;
        this.ownerName = ownerName;
        this.balance = round(balance);
    }

    public String getID() { return id; }
    public String getOwnerName() { return ownerName; }
    public double getBalance() { return balance; }
    abstract AccountType getType();

    public static Account make(String inputLine) {
        if (inputLine == null) throw new IllegalArgumentException("inputLine cannot be null");
        String[] tokens = inputLine.split(",");
        AccountType type = AccountType.valueOf(tokens[0].toUpperCase());
        String id = tokens[1];
        String ownerName = tokens[2];
        double balance = Double.parseDouble(tokens[3]);
        if (type == AccountType.CHECKING) {
            return new CheckingAccount(id, ownerName, balance);
        } else {
            return new SavingsAccount(id, ownerName, balance);
        }
    }

    @Override
    public String toString() {
        return String.format(
            "%s,%s,%s,%.2f",
            getType().name().toLowerCase(),
            getID(),
            getOwnerName(),
            getBalance()
        );
    }

    public String toCSV() { return toString(); }

    public void credit(double amount) {
        if (amount < 0) throw new IllegalArgumentException("Amount cannot be negative");
        balance = round(balance + amount);
    }

    public void debit(double amount) {
        if (amount < 0) throw new IllegalArgumentException("Amount cannot be negative");
        balance = round(balance - amount);
    }

    private double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
