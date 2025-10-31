public class Main {
public static void main(String[] args) {

// Create a bank with capacity for 5 accounts
Bank bank = new Bank(5);

// Create some accounts
Account acc1 = new Account("A001", "Michael Prosper", "Checking", 1200.50);
Account acc2 = new Account("A002", "John Smith", "Savings", 2000.00);
Account acc3 = new Account("A003", "Michael Prosper", "Savings", 500.75);

// Add them to the bank
bank.add(acc1);
bank.add(acc2);
bank.add(acc3);

// Find an account by ID
System.out.println(" Searching for Account ID A002...");
Account found = bank.find("A002");
System.out.println(found != null ? "Found: " + found : "Account not found.");

// Find accounts by owner name
System.out.println("\nSearching accounts owned by 'Michael Prosper'...");
Account[] michaelAccounts = bank.findAccountsByOwner("Michael Prosper");
for (Account acc : michaelAccounts) {
System.out.println(acc);
}

// Display total number of accounts
System.out.println("\n Total number of accounts in bank: " + bank.getCount());
}
}