package projects.bank;

public class Deposit extends Transaction {

    public Deposit(String accountID, double amount) {
        super(accountID, amount);
    }

    @Override
    public boolean validate(Account account, Audit audit) {
        if (getAmount() <= 0) {
            audit.write(
                account,
                "deposit-validation",
                getAmount(),
                "ERROR: Cannot deposit non-positive amount"
            );
            return false;
        }
        return true;
    }

    @Override
    public void execute(Account account, Audit audit) {
        if (validate(account, audit)) { 
            boolean success = account.credit(getAmount());
            
            audit.write(
                account, 
                "deposit", 
                getAmount(), 
                success ? "SUCCESS" : "FAILURE (Internal Credit Error)"
            );
        }
    }

    @Override
    public String toString() {
        return String.format("deposit $%.2f into account %s", amount, accountID);
    }
}