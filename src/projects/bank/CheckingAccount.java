package projects.bank;

public class CheckingAccount extends Account {

    // Part 5: Class data for penalty and threshold
    private static final double PENALTY_THRESHOLD = 500.00;
    private static final double MONTHLY_PENALTY = 15.00; // Source: Plausible general banking fee

    public CheckingAccount(String id, String ownerName, double balance) {
        super(id, ownerName, balance);
    }

    @Override
    public AccountType getType() {
        return AccountType.CHECKING;
    }

    // Part 5: End-of-month action: impose penalty if balance is too low
    @Override
    void doEndOfMonthActions(Audit audit) {
        if (getBalance() < PENALTY_THRESHOLD) {
            debit(MONTHLY_PENALTY);
            audit.write(String.format(
                "%s INFO: Checking fee: $%.2f deducted from account %s. Balance forward %.2f",
                Utils.timestamp(),
                MONTHLY_PENALTY,
                getID(),
                getBalance()
            ));
        }
    }
}