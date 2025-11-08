package projects.bank;

import java.io.FileWriter;
import java.io.IOException;

public class Audit {

    private FileWriter writer;

    public Audit(String fileName) throws IOException {
        if (fileName == null) throw new IllegalArgumentException("fileName cannot be null");
        writer = new FileWriter(fileName);
    }

    public void close() {
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write(String s) {
        try {
            writer.write(s + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recordNSA(Transaction t) {
        write(String.format("%s ERROR no such account: %s", Utils.timestamp(), t.toString()));
    }

    public void recordNSF(Transaction t, Account a) {
        write(String.format("%s ERROR nonsufficient funds: %s", Utils.timestamp(), t.toString()));
    }

    public void recordValid(Transaction t, Account a) {
        write(String.format("%s INFO: %s balance forward %.2f", Utils.timestamp(), t.toString(), a.getBalance()));
    }
}
