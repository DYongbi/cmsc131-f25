package projects.bank;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Audit {

    private FileWriter writer;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Audit(String fileName) throws IOException {
        if (fileName == null) throw new IllegalArgumentException("fileName cannot be null");
        this.writer = new FileWriter(fileName); 
    }
    
    // --- Core Formatting and Writing Method ---
    // Handles timestamp and writing to file privately.
    private void writeLine(String line) {
        try {
            // Internal timestamp generation for encapsulation
            String timestamp = LocalDateTime.now().format(FORMATTER);
            writer.write(timestamp + "," + line + System.lineSeparator()); 
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error writing to audit file: " + e.getMessage());
        }
    }

    // --- 1. Structured Write (Account object passed) ---
    // Used by Deposit, Withdrawal, CheckingAccount, SavingsAccount
    public void write(Account account, String action, double amount, String result) {
        String accountID = account.getID();
        String ownerName = account.getOwnerName().replace(",", ""); 
        
        String line = String.format("%s,%s,%s,%.2f,%s", 
            accountID, 
            ownerName, 
            action, 
            amount, 
            result
        );
        writeLine(line);
    }
    
    // --- 2. Structured Write (String ID passed for UNKNOWN accounts) ---
    // Used by Bank.processTransactions() for un-loadable transactions or unknown account errors.
    public void write(String accountID, String action, double amount, String result) {
        String line = String.format("%s,%s,%s,%.2f,%s", 
            accountID, 
            "UNKNOWN", // Owner name is UNKNOWN here
            action, 
            amount, 
            result
        );
        writeLine(line);
    }

    // --- 3. Simple Write (General Status Messages) ---
    // Used by Main and Bank for general status updates (like "Loading Accounts..." or the file not found error)
    public void write(String message) {
        // This method automatically prefixes the line with "MESSAGE," 
        writeLine("MESSAGE," + message.replace(",", ""));
    }

    // --- Cleanup Method ---
    public void close() {
        try {
            if (writer != null) {
                writer.flush(); 
                writer.close();
                writer = null;
            }
        } catch (IOException e) {
            System.err.println("Error closing audit file: " + e.getMessage());
        }
    }
}