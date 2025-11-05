public class SavingsAccount extends Account {

public SavingsAccount(String accountID, String ownerName, double balance) {
super(accountID, ownerName, "Savings", balance);
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
if (balance - amount < 100)
throw new IllegalArgumentException("Savings balance cannot go below 100.");
balance -= amount;
}
}