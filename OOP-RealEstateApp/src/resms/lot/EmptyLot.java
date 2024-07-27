package resms.lot;

public class EmptyLot extends Lot {
    public EmptyLot(int blockNo, int lotNo, String propertyType, double size, double price, String status) {
        super(blockNo, lotNo, "EmptyLot", size, price, status);
    }

    @Override
    public String getPropertyType() {
        return "EmptyLot";
    }

    @Override
    public double calculatePrice(String transactionType) {
        if ("Rent".equalsIgnoreCase(transactionType)) {
            return getPrice() * 0.10; 
        } else if ("Buy".equalsIgnoreCase(transactionType)) {
            return getPrice(); 
        } else {
            throw new IllegalArgumentException("Invalid transaction type");
        }
    }
}
