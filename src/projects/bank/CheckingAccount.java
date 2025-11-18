package projects.bank;

public class CheckingAccount extends Account {

    private static final double PENALTY_THRESHOLD = 500.00;
    private static final double MONTHLY_PENALTY = 15.00; 

    public CheckingAccount(String id, String ownerName, double balance) {
        super(id, ownerName, balance);
    }

    @Override
    public String getAccountType() {
        return "checking";
    }

    // Part 5: End-of-month action: impose penalty if balance is too low
    @Override
    public void doEndOfMonthActions(Audit audit) {
        if (getBalance() < PENALTY_THRESHOLD) {
            boolean success = debit(MONTHLY_PENALTY);
            
            audit.write(
                this, 
                "monthly-fee",
                MONTHLY_PENALTY,
                success ? "SUCCESS" : "FAILURE (Internal Error)"
            );
        } else {
             audit.write(
                this, 
                "monthly-review", 
                0.0, 
                "OK (above threshold)"
             );
        }
    }
}