package projects.bank;

public class Withdrawal extends Transaction {

    public Withdrawal(String accountID, double amount) {
        super(accountID, amount);
    }

    @Override
    public void execute(Account account, Audit audit) {
        account.debit(getAmount());
        audit.recordValid(this, account);
    }

    @Override
    public boolean validate(Account account, Audit audit) {
        boolean sufficientFunds = getAmount() <= account.getBalance();
        if (!sufficientFunds) audit.recordNSF(this, account);
        return sufficientFunds;
    }

    @Override
    public String toString() {
        return String.format("withdraw $%.2f from account %s", amount, accountID);
    }
}
