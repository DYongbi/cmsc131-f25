package projects.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

enum AccountType {
    CHECKING, SAVINGS
}

public class CsvProcessor {

    public static void main(String[] args) {
        String inputPath = "/mnt/data/accounts (2).csv";
        String outputPath = "/mnt/data/accounts_final.csv";

        openAndParseCsv(inputPath);
        writeDataToCsv(outputPath);
    }

    public static void openAndParseCsv(String filename) {
        File file = new File(filename);
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (line.trim().isEmpty()) continue;

            String[] tokens = line.split(",");
            if (tokens.length < 3) continue;

            String typeText = tokens[0].trim().toUpperCase();
            String id = tokens[1].trim();
            String owner = tokens[2].trim();

            AccountType type = AccountType.valueOf(typeText);

            double balance = 0.0;
            if (tokens.length > 3) {
                balance = Double.parseDouble(tokens[3]);
            }

            System.out.println(type + " " + id + " " + owner + " " + balance);
        }

        scan.close();
    }

    public static void writeDataToCsv(String filename) {
        File file = new File(filename);
        FileWriter writer = new FileWriter(file);

        for (int i = 0; i < 5; i++) {
            String type = (i % 2 == 0) ? "checking" : "savings";
            String id = "acc" + (100 + i);
            String name = "Customer_" + (i + 1);
            double amount = 200 + (i * 150);

            String[] data = { type, id, name, String.valueOf(amount) };
            String csvLine = String.join(",", data);
            writer.write(csvLine + System.lineSeparator());
        }

        writer.close();
    }
}
