public class CheckingAccount extends Account {

public CheckingAccount(String accountID, String ownerName, double balance) {
super(accountID, ownerName, "Checking", balance);
}

@Override
public void deposit(double amount) {
if (amount <= 0)
throw new IllegalArgumentException("Deposit amount must be positive.");
balance += amount;
}

@Override
public void withdraw(double amount) {
if (amount <= 0)
throw new IllegalArgumentException("Withdrawal amount must be positive.");
if (balance < amount)
throw new IllegalArgumentException("Insufficient funds.");
balance -= amount;
}
}