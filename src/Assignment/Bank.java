import java.util.Arrays;

public class Bank {

private Account[] accounts;
private int count;
private static final int DEFAULT_CAPACITY = 100;


public Bank() {
this.accounts = new Account[DEFAULT_CAPACITY];
this.count = 0;
}


public Bank(int size) {
this.accounts = new Account[size];
this.count = 0;
}


public void add(Account account) {
if (count >= accounts.length) {
throw new IllegalStateException("Bank account list is full.");
}
accounts[count++] = account;
}


public Account find(String accountID) {
for (int i = 0; i < count; i++) {
if (accounts[i].getAccountID().equals(accountID)) {
return accounts[i];
}
}
return null;
}


public int getCount() {
return count;
}

public Account[] findAccountsByOwner(String ownerName) {
Account[] temp = new Account[count];
int foundCount = 0;

for (int i = 0; i < count; i++) {
if (accounts[i].getOwnerName().equalsIgnoreCase(ownerName)) {
temp[foundCount++] = accounts[i];
}
}

return Arrays.copyOf(temp, foundCount);
}


public void loadAccounts() {
System.out.println("The loadAccounts method will be implemented in a later phase to read data.");
}
}