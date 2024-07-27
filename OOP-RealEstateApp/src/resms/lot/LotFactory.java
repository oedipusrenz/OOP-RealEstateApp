package resms.lot;

public class LotFactory {

    public static Lot createLot(int blockNo, int lotNo, String propertyType, double size, double price, String status) {
        
        switch (propertyType) {
            case "EmptyLot" -> {
                return new EmptyLot(blockNo, lotNo, "EmptyLot", size, price, status);
            }
            case "OneStory" -> {
                return new OneStory(blockNo, lotNo, "OneStory", size, price, status);
            }
            case "TwoStory" -> {
                return new TwoStory(blockNo, lotNo, "TwoStory", size, price, status);
            }
            default -> throw new IllegalArgumentException("Unknown property type: " + propertyType);
        }
    }
}