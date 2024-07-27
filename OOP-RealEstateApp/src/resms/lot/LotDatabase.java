package resms.lot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import resms.transactions.TransactionDatabase;

public final class LotDatabase {

    private final String filename = "property.txt";
    private static LotDatabase instance;
    private final ArrayList<Lot> lots;

    private LotDatabase() {
        lots = new ArrayList<>();
        exportDetails(); 
    }

    public static LotDatabase getInstance() {
        if (instance == null) {
            instance = new LotDatabase();
        }
        return instance;
    }

    public boolean addProperty(Lot property) {
        String targetPropertyCode = property.getPropertyCode();
        for (Lot p : lots) {
            if (targetPropertyCode.equals(p.getPropertyCode())) {
                return false; 
            }
        }
        lots.add(property);
        return true;
    }

    public boolean removeProperty(Lot property) {
        String targetPropertyCode = property.getPropertyCode();
        for (Lot p : lots) {
            if (targetPropertyCode.equals(p.getPropertyCode())) {
                lots.remove(p);
                return true;
            }
        }
        return false;
    }

    public Lot getLotById(String propertyCode) {
        for (Lot p : lots) {
            if (propertyCode.equals(p.getPropertyCode())) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<LotAvailability> getPropertySales(TransactionDatabase purchase) {
    var reports = new ArrayList<LotAvailability>();
    for (Lot lot : lots) {
        boolean isAvailable = purchase.isAvailable(lot); // Check availability in TransactionDatabase
        var availability = new LotAvailability(lot, isAvailable);
        reports.add(availability);
    }
    return reports;
    }   

    public void exportDetails() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] components = line.split(",");
                Lot property = createLotFromComponents(components);
                if (property != null) {
                    lots.add(property);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading from file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void importDetails() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Lot property : lots) {
                writer.write(property.getBlockNo() + "," +
                        property.getLotNo() + "," +
                        property.getPropertyType() + "," +
                        property.getSize() + "," +
                        property.getPrice() + "," +
                        property.getStatus() + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving to file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Lot createLotFromComponents(String[] components) {
        int blockNo = Integer.parseInt(components[0]);
        int lotNo = Integer.parseInt(components[1]);
        String propertyType = components[2];
        double size = Double.parseDouble(components[3]);
        double price = Double.parseDouble(components[4]);
        String status = components[5];

        return LotFactory.createLot(blockNo, lotNo, propertyType, size, price, status);
    }
}
