package resms.transactions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import resms.lot.Lot;
import resms.lot.LotDatabase;

public class TransactionDatabase {

    private final String filename = "purchases.txt";
    private static TransactionDatabase instance;
    private final ArrayList<Transaction> transactions;

    private TransactionDatabase() {
        transactions = new ArrayList<>();
    }

    public static synchronized TransactionDatabase getInstance(LotDatabase lotDatabase) {
        if (instance == null) {
            instance = new TransactionDatabase();
            instance.exportDetails(lotDatabase);
        }
        return instance;
    }

    public boolean recordTransaction(Lot property, String type, String payMethod, String customerName, String customerContact, double finalPrice) {
        Transaction transaction = new Transaction(property, type, payMethod, customerName, customerContact, finalPrice);
    transactions.add(transaction);
        return true;
    }

    public ArrayList<TransactionReport> generateReport() {
        ArrayList<TransactionReport> reports = new ArrayList<>();
        for (Transaction transaction : transactions) {
            reports.add(new TransactionReport(transaction));
        }
        return reports;
    }

    public boolean isAvailable(Lot property) {
        if (property == null) {
            return false;
        }
        for (Transaction transaction : transactions) {
            if (transaction.getLot().equals(property)) {
                String transactionType = transaction.getTransactionType();
                if ("SOLD".equalsIgnoreCase(transactionType) || "RENTED".equalsIgnoreCase(transactionType)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void deleteTransaction(String propertyCode) {
        Transaction transactionToRemove = transactions.stream()
                .filter(t -> t.getLot().getPropertyCode().equals(propertyCode))
                .findFirst()
                .orElse(null);

        if (transactionToRemove != null) {
            transactions.remove(transactionToRemove);
        }
    }

    public void exportDetails(LotDatabase lotDatabase) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] components = line.split(",");
            if (components.length != 6) { 
                continue;
            }

            String propertyCode = components[0];
            String propertyType = components[1];
            double price = Double.parseDouble(components[2]);
            String transactionType = components[3];
            String payMethod = components[4];
            String customerName = components[5];
            String customerContact = components[6];
            double finalPrice = Double.parseDouble(components[7]); 

            Lot property = lotDatabase.getLotById(propertyCode);
            if (property != null) {
                Transaction transaction = new Transaction(
                    property, 
                    transactionType, 
                    payMethod, 
                    customerName, 
                    customerContact, 
                    finalPrice
                );
                transactions.add(transaction);
            }
        }
    } catch (IOException | NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Error reading transaction details.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    public void importDetails() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
            for (Transaction transaction : transactions) {
                writer.write(transaction.getLot().getPropertyCode() + "," +
                        transaction.getLot().getPropertyType() + "," +
                        transaction.getLot().getPrice() + "," +
                        transaction.getCustomerName() + "," +
                        transaction.getCustomerContact() + "," +
                        transaction.getTransactionType() + "," +
                        transaction.getPaymentMethod() + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving transaction details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public TransactionReport getTransactionByCode(String propertyCode) {
        Transaction transaction = transactions.stream()
                .filter(t -> t.getLot().getPropertyCode().equals(propertyCode))
                .findFirst()
                .orElse(null);

        if (transaction != null) {
            return new TransactionReport(transaction);
        } else {
            return null;
        }
    }
}
