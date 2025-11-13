package projects.bank;

public class Deposit extends Transaction {

    public Deposit(String accountID, double amount) {
        super(accountID, amount);
    }

    @Override
    public void execute(Account account, Audit audit) {
        account.credit(getAmount());
        audit.recordValid(this, account);
    }

    @Override
    public boolean validate(Account account, Audit audit) {
        return true; // Deposits are always valid
    }

    @Override
    public String toString() {
        return String.format("deposit $%.2f into account %s", amount, accountID);
    }
}