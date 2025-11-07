public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        //Create sample accounts
        Account acc1 = new CheckingAccount("A001", "Michael Jackson", 1200.50);
        Account acc2 = new SavingsAccount("A002", "John Smith", 2000.00);
        Account acc3 = new SavingsAccount("A003", "Michael Prosper", 500.75);

        bank.add(acc1);
        bank.add(acc2);
        bank.add(acc3);
        bank.writeAccounts("accounts.csv");

        System.out.println("\n--- Transactions ---");
        new Deposit("T001", acc1, 300).process();
        new Withdrawal("T002", acc2, 150).process();

        System.out.println("\n--- Updated Accounts ---");
        System.out.println(acc1);
        System.out.println(acc2);
        System.out.println(acc3);

        Bank newBank = new Bank();
        newBank.loadAccounts("accounts.csv");
        System.out.println("\nLoaded " + newBank.getCount() + " accounts from file:");
        for (Account acc : newBank.findAccountsByOwner("Miguel Gomez")) {
            System.out.println(acc);
        }
    }
}
