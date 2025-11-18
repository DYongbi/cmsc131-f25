package projects.bank;

public abstract class Transaction {

    protected final String accountID;
    protected final double amount;

    public String getAccountID() { return accountID; }
    public double getAmount() { return amount; }

    protected Transaction(String accountID, double amount) {
        if (accountID == null) throw new IllegalArgumentException("accountID cannot be null");
        this.accountID = accountID;
        this.amount = amount;
    }

    public abstract void execute(Account account, Audit audit);
    public abstract boolean validate(Account account, Audit audit);
    public abstract String toString();


    // FIX: Factory Method required by Bank.java
    public static Transaction create(String inputLine) {
        if (inputLine == null || inputLine.trim().isEmpty()) return null;

        try {
            String[] tokens = inputLine.split(",");
            if (tokens.length < 3) return null;

            String typeStr = tokens[0].trim();
            String id = tokens[1].trim();
            double amount = Double.parseDouble(tokens[2].trim());

            if (typeStr.equalsIgnoreCase("deposit")) {
                return new Deposit(id, amount); 
            } else if (typeStr.equalsIgnoreCase("withdrawal")) {
                return new Withdrawal(id, amount);
            } else {
                return null; 
            }
        } catch (Exception e) {
            return null; 
        }
    }
}