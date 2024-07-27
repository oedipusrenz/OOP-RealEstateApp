package resms.lot;

public class TwoStory extends Lot {
    public TwoStory(int blockNo, int lotNo, String propertyType, double size, double price, String status) {
        super(blockNo, lotNo, "TwoStory", size, price, status);
    }

    @Override
    public String getPropertyType() {
        return "TwoStory";
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
