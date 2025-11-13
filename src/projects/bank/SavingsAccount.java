package projects.bank;

public class SavingsAccount extends Account {

    // Part 5: Class data for annual interest rate
    // Stored as class data because all savings accounts typically use the same rate
    private static final double ANNUAL_INTEREST_RATE = 0.005; // 0.5% (Source: Plausible banking rate)
    private static final int MONTHS_PER_YEAR = 12;

    public SavingsAccount(String id, String ownerName, double balance) {
        super(id, ownerName, balance);
    }

    @Override
    public AccountType getType() {
        return AccountType.SAVINGS;
    }
    
    // Part 5: End-of-month action: credit interest
    @Override
    void doEndOfMonthActions(Audit audit) {
        double monthlyInterest = getBalance() * (ANNUAL_INTEREST_RATE / MONTHS_PER_YEAR);
        credit(monthlyInterest);
        audit.write(String.format(
            "%s INFO: Interest: $%.2f credited to account %s. Balance forward %.2f",
            Utils.timestamp(),
            monthlyInterest,
            getID(),
            getBalance()
        ));
    }
}