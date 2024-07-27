package resms.lot;

public class OneStory extends Lot {
    public OneStory(int blockNo, int lotNo, String propertyType, double size, double price, String status) {
        super(blockNo, lotNo, "OneStory", size, price, status);
    }

    @Override
    public String getPropertyType() {
        return "OneStory";
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
