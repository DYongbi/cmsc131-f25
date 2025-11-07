import java.time.LocalDateTime;

abstract class Transaction {
   protected String transactionID;
   protected Account account;
   protected double amount;
   protected LocalDateTime timestamp;

   public Transaction(String var1, Account var2, double var3) {
      this.transactionID = var1;
      this.account = var2;
      this.amount = var3;
      this.timestamp = LocalDateTime.now();
   }

   public abstract void process();

   public String toString() {
      String var10000 = this.transactionID;
      return "Transaction{ID='" + var10000 + "', AccountID='" + this.account.getAccountID() + "', Amount=" + this.amount + ", Time=" + String.valueOf(this.timestamp) + "}";
   }
}
