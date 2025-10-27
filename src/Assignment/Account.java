public class Account {
private String accountID;
private String ownerName;
private double balance;
private String accountType;

public Account(String accountID, String ownerName, String accountType, double balance) {
// Validate account ID
if (accountID == null || accountID.isEmpty()) {
throw new IllegalArgumentException("Account ID must not be empty."); // || this call or operator
}

// Validate owner name
if (ownerName == null || ownerName.isEmpty()) {
throw new IllegalArgumentException("Owner name must not be empty.");
}

// Validate account type
if (!accountType.equalsIgnoreCase("Checking") && !accountType.equalsIgnoreCase("Savings")) {
throw new IllegalArgumentException("Account type must be 'Checking' or 'Savings'.");
}

// Validate balance
if (balance < 0) {
throw new IllegalArgumentException("Balance cannot be negative.");
}

// Assign fields after validation
this.accountID = accountID;
this.ownerName = ownerName;
this.accountType = accountType;
this.balance = balance;
}

public String getAccountID() {
return accountID;
}

public String getOwnerName() {
return ownerName;
}

public double getBalance() {
return balance;
}

public String getAccountType() {
return accountType;
}

@Override
public String toString() {
return "Account{" +
"ID='" + accountID + '\'' +
", Owner='" + ownerName + '\'' +
", Type='" + accountType + '\'' +
", Balance=" + balance +
'}';
}
