public class Withdrawal extends Transaction {

public Withdrawal(String transactionID, Account account, double amount) {
super(transactionID, account, amount);
}

@Override
public void process() {
account.withdraw(amount);
System.out.println("Withdrew " + amount + " from " + account.getAccountID());
}
}

