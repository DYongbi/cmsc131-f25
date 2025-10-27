public class Bank {
    private Account[] accounts;
    private int count;

    public Bank(int size) {
        accounts = new Account[size];
        count = 0;
    }

 public void add(Account account) {
        if (count >= accounts.length) {
            throw new IllegalStateException("Bank account list is full");
        }
        accounts[count] = account;
        count++;
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

}