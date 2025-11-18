package projects.bank;

import java.io.IOException;
import java.util.Scanner; // Import the Scanner class

public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank();
        Audit audit = null; 

        try {
            // Part 4: Audit Initialization
            audit = new Audit("data/bank.audit.csv"); 
            
            // Part 2: Load accounts
            audit.write("Loading Accounts...");
            bank.loadAccounts("data/accounts.csv");
            
            // NEW CODE: Print total count to console
            System.out.println("\n--- Bank Simulation Output ---");
            System.out.println("Total Accounts Loaded: " + bank.getCount());
            audit.write("INFO: Loaded " + bank.getCount() + " accounts.");
            // END NEW CODE

            // Part 3 & 4: Process transactions
            audit.write("Processing Transactions...");
            bank.processTransactions("data/transactions.csv", audit);
            
            // Part 5: End-of-month actions (Interest/Fees)
            audit.write("Executing End-of-Month Actions (Interest/Fees)...");
            bank.doAllEndOfMonthActions(audit);

            // Part 2: Save final state
            audit.write("Writing final account state...");
            bank.writeAccounts("data/accounts.end.csv");
            
            // START OF NEW CODE for Interactive Lookup
            runInteractiveLookup(bank, audit);
            // END OF NEW CODE
            
        } catch (IOException e) {
            System.err.println("Fatal error during bank operation or audit setup.");
            e.printStackTrace();
        } finally {
            // Part 4: Ensure close() is called
            if (audit != null) {
                audit.write("Bank Project Execution Completed.");
                audit.close(); 
            }
        }
    }
    
    /**
     * Dedicated method for interactive name lookup.
     */
    private static void runInteractiveLookup(Bank bank, Audit audit) {
        System.out.println("\n--- Interactive Account Lookup ---");
        System.out.println("Enter owner name to search (or 'quit' to exit):");

        try (Scanner consoleScanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("> Search Name: ");
                String name = consoleScanner.nextLine().trim();

                if (name.equalsIgnoreCase("quit")) {
                    System.out.println("Exiting lookup.");
                    break;
                }

                Account[] matches = bank.findByOwnerName(name);

                if (matches.length > 0) {
                    System.out.println("Found " + matches.length + " accounts for '" + name + "':");
                    for (Account acct : matches) {
                        // Print the CSV representation to show all details
                        System.out.println("  - " + acct.toCSV());
                    }
                    audit.write("INFO", "lookup", 0.0, "SUCCESS: Found " + matches.length + " accounts for " + name);
                } else {
                    System.out.println("No accounts found for that name.");
                    audit.write("INFO", "lookup", 0.0, "FAILURE: No accounts found for " + name);
                }
                System.out.println("----------------------------------");
            }
        }
    }
}