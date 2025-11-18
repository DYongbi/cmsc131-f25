package projects.bank;

public class SavingsAccount extends Account {

    private static final double ANNUAL_INTEREST_RATE = 0.005; // 0.5% 
    private static final int MONTHS_PER_YEAR = 12;

    public SavingsAccount(String id, String ownerName, double balance) {
        super(id, ownerName, balance);
    }

    @Override
    public String getAccountType() {
        return "savings";
    }
    
    // Part 5: End-of-month action: credit interest
    @Override
    public void doEndOfMonthActions(Audit audit) {
        double monthlyInterest = getBalance() * (ANNUAL_INTEREST_RATE / MONTHS_PER_YEAR);
        
        boolean success = credit(monthlyInterest);
        
        audit.write(
            this, 
            "interest-credit",
            monthlyInterest,
            success ? "SUCCESS" : "FAILURE (Internal Credit Error)"
        );
    }
}