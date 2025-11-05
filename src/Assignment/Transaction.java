import java.time.LocalDateTime;

public abstract class Transaction {
protected String transactionID;
protected Account account;
protected double amount;
protected LocalDateTime timestamp;

public Transaction(String transactionID, Account account, double amount) {
this.transactionID = transactionID;
this.account = account;
this.amount = amount;
this.timestamp = LocalDateTime.now();
}

public abstract void process();

@Override
public String toString() {
return "Transaction{" +
"ID='" + transactionID + '\'' +
", AccountID='" + account.getAccountID() + '\'' +
", Amount=" + amount +
", Time=" + timestamp +
'}';
}
}