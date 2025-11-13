package projects.bank;

abstract class Transaction {

    protected final String accountID;
    protected final double amount;

    public String getAccountID() { return accountID; }
    public double getAmount() { return amount; }

    protected Transaction(String accountID, double amount) {
        if (accountID == null) throw new IllegalArgumentException("accountID cannot be null");
        if (amount <= 0) throw new IllegalArgumentException("amount must be positive");
        this.accountID = accountID;
        this.amount = amount;
    }

    abstract void execute(Account account, Audit audit);
    abstract boolean validate(Account account, Audit audit);

    protected static Transaction make(String inputLine) {
        if (inputLine == null) throw new IllegalArgumentException("inputLine cannot be null");
        String[] tokens = inputLine.split(",");
        TransactionType type = TransactionType.valueOf(tokens[0].toUpperCase());
        String id = tokens[1];
        double amount = Double.parseDouble(tokens[2]);
        // Note: Assumes Withdrawal is named Withdrawal, not Withdrawal
        if (type == TransactionType.DEPOSIT) { 
            return new Deposit(id, amount);
        } else {
            return new Withdrawal(id, amount);
        }
    }
}