package projects.bank;

public abstract class Account {

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
        this.ownerName = ownerName.replace(",", "").trim(); 
        this.balance = round(balance); 
    }

    // --- Accessors (Part 1) ---
    public String getID() { return id; }
    public String getAccountID() { return id; }
    public String getOwnerName() { return ownerName; }
    public double getBalance() { return balance; }
    
    // --- Abstract Methods (Part 5) ---
    public abstract String getAccountType(); 
    abstract void doEndOfMonthActions(Audit audit); 

    // --- Part 3: Credit/Debit Methods ---
    public boolean credit(double amount) {
        if (amount <= 0) return false;
        this.balance += amount;
        this.balance = round(this.balance);
        return true;
    }

    public boolean debit(double amount) {
        if (amount <= 0) return false;
        this.balance -= amount;
        this.balance = round(this.balance);
        return true;
    }

    // --- Part 3 Fix: Mandatory Rounding Logic (Uses Math.floor) ---
    private double round(double value) {
        return Math.floor(value * 100.0) / 100.0;
    }
    
    // --- Part 2: Factory Method ---
    public static Account makeFromCSV(String inputLine) {
        if (inputLine == null) return null;
        try {
            String[] tokens = inputLine.split(",");
            if (tokens.length < 4) return null; 

            String typeStr = tokens[0].trim();
            String id = tokens[1].trim();
            String ownerName = tokens[2].trim();
            double balance = Double.parseDouble(tokens[3].trim());

            if (typeStr.equalsIgnoreCase("checking")) {
                return new CheckingAccount(id, ownerName, balance);
            } else if (typeStr.equalsIgnoreCase("savings")) {
                return new SavingsAccount(id, ownerName, balance);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public String toCSV() { 
        return String.format(
            "%s,%s,%s,%.2f",
            getAccountType().toLowerCase(), 
            getID(),
            getOwnerName(),
            getBalance()
        );
    }
}