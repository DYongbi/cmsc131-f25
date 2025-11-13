package projects.bank;

public class Withdrawal extends Transaction {
    
    private static final double NSF_PENALTY = 10.00;

    public Withdrawal(String accountID, double amount) {
        super(accountID, amount);
    }

    @Override
    public void execute(Account account, Audit audit) {
        // Only executes if validate() passed and funds were sufficient
        account.debit(getAmount());
        audit.recordValid(this, account);
    }

    @Override
    public boolean validate(Account account, Audit audit) {
        boolean sufficientFunds = getAmount() <= account.getBalance();
        
        if (!sufficientFunds) {
            audit.recordNSF(this, account);
            
            // Part 5: Impose $10 penalty for NSF
            account.debit(NSF_PENALTY);
            
            // Log the penalty fee action
            audit.write(String.format(
                "%s INFO: NSF Fee: $%.2f deducted from account %s. Balance forward %.2f",
                Utils.timestamp(),
                NSF_PENALTY,
                account.getID(),
                account.getBalance()
            ));
        }
        return sufficientFunds;
    }

    @Override
    public String toString() {
        return String.format("withdraw $%.2f from account %s", amount, accountID);
    }
}