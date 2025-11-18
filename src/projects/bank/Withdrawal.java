package projects.bank;

public class Withdrawal extends Transaction {
    
    private static final double NSF_PENALTY = 10.00;

    public Withdrawal(String accountID, double amount) {
        super(accountID, amount);
    }

    @Override
    public boolean validate(Account account, Audit audit) {
        if (getAmount() <= 0) {
            audit.write(getAccountID(), "withdrawal-validation", getAmount(), "ERROR: Invalid withdrawal amount.");
            return false;
        }
        return true;
    }

    // Part 5: Execution Method (Handles NSF Check and Penalty Logic)
    @Override
    public void execute(Account account, Audit audit) {
        if (!validate(account, audit)) {
            return; 
        }

        boolean withdrawalSuccessful = account.getBalance() >= getAmount();
        
        if (withdrawalSuccessful) {
            // SUCCESS: Debit the amount and log success
            account.debit(getAmount());
            audit.write(account, "withdrawal", getAmount(), "SUCCESS");
        } else {
            // FAILURE: NSF occurred (Balance remains unchanged from withdrawal)

            // 1. Log the failed withdrawal attempt
            audit.write(
                account, 
                "withdrawal", 
                getAmount(), 
                "NSF ERROR: Withdrawal rejected (Balance unchanged)"
            );

            // 2. Impose the $10 NSF penalty
            boolean penaltySuccess = account.debit(NSF_PENALTY); 
            
            audit.write(
                account, 
                "NSF-fee", 
                NSF_PENALTY, 
                penaltySuccess ? "SUCCESS: Penalty applied" : "FAILURE: Penalty rejected"
            );
        }
    }

    @Override
    public String toString() {
        return String.format("withdraw $%.2f from account %s", amount, accountID);
    }
}