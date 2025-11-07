import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Account {
    protected String accountID;
    protected String ownerName;
    protected double balance;
    protected String accountType;

    public Account(String accountID, String ownerName, String accountType, double balance) {
        if (accountID == null || accountID.isEmpty())
            throw new IllegalArgumentException("Account ID must not be empty.");
        if (ownerName == null || ownerName.isEmpty())
            throw new IllegalArgumentException("Owner name must not be empty.");
        if (balance < 0)
            throw new IllegalArgumentException("Balance cannot be negative.");

        this.accountID = accountID;
        this.ownerName = ownerName;
        this.accountType = accountType;
        this.balance = balance;
    }

    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);

    public String getAccountID() { return accountID; }
    public String getOwnerName() { return ownerName; }
    public double getBalance() { return balance; }
    public String getAccountType() { return accountType; }

    public String toCSV() {
        return accountType + "," + accountID + "," + ownerName + "," + balance;
    }

    public static Account fromCSV(String line) {
        String[] parts = line.split(",");
        if (parts.length != 4)
            throw new IllegalArgumentException("Invalid CSV format: " + line);

        String type = parts[0].trim();
        String id = parts[1].trim();
        String name = parts[2].trim();
        double bal = Double.parseDouble(parts[3].trim());

        if (type.equalsIgnoreCase("Checking"))
            return new CheckingAccount(id, name, bal);
        else if (type.equalsIgnoreCase("Savings"))
            return new SavingsAccount(id, name, bal);
        else
            throw new IllegalArgumentException("Unknown account type: " + type);
    }

    @Override
    public String toString() {
        return accountType + " Account {" +
                "ID='" + accountID + '\'' +
                ", Owner='" + ownerName + '\'' +
                ", Balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(accountID, account.accountID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountID);
    }

    //
    public static void main(String[] args) {
        System.out.println("Classes loaded successfully.");
    }
}


// Transaction class

abstract class Transaction {
    protected String transactionID;
    protected Account account;
    protected double amount;
    protected LocalDateTime timestamp;

    public Transaction(String transactionID, Account account, double amount) {
        this.transactionID = transactionID;
        this.account = account;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public abstract void process();

    @Override
    public String toString() {
        return "Transaction{" +
                "ID='" + transactionID + '\'' +
                ", AccountID='" + account.getAccountID() + '\'' +
                ", Amount=" + amount +
                ", Time=" + timestamp +
                '}';
    }
}
