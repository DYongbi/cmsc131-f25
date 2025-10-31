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


public void loadAccounts(String filename) {
try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
String line;
while ((line = br.readLine()) != null) {
if (line.trim().isEmpty()) continue;
Account acc = Account.fromCSV(line);
add(acc);
}
} catch (IOException e) {
throw new RuntimeException("Error loading accounts: " + e.getMessage());
}
}

// Write accounts to CSV file (append mode)
public boolean writeAccounts(String filename) {
boolean result = true;
File file = new File(filename);

try (FileWriter fw = new FileWriter(file, true); // append = true
PrintWriter pw = new PrintWriter(fw)) {

for (int i = 0; i < count; i++) {
pw.println(accounts[i].toCSV());
}

} catch (IOException e) {
e.printStackTrace();
result = false;
}
return result;
}
}
