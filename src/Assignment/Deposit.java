public class Deposit extends Transaction {

public Deposit(String transactionID, Account account, double amount) {
super(transactionID, account, amount);
}

@Override
public void process() {
account.deposit(amount);
System.out.println("Deposited " + amount + " to " + account.getAccountID());
}
}