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
// Factory method to create Account from CSV line
public static Account fromCSV(String line) {
String[] parts = line.split(",");
if (parts.length != 4) {
throw new IllegalArgumentException("Invalid CSV format: " + line);
}

String accountType = parts[0].trim();
String accountID = parts[1].trim();
String ownerName = parts[2].trim();
double balance = Double.parseDouble(parts[3].trim());

return new Account(accountID, ownerName, accountType, balance);
}

// Convert Account to CSV line
public String toCSV() {
return accountType + "," + accountID + "," + ownerName + "," + balance;
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

@Override
public boolean equals(Object o) {
if (this == o) return true;
if (!(o instanceof Account)) return false;
Account account = (Account) o;
return Objects.equals(accountID, account.accountID);
}

@Override
public int hashCode() {
return Objects.hash(accountID);
}
}


