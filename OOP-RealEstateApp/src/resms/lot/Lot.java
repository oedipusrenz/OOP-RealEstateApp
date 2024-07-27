package resms.lot;

public abstract class Lot {
    private int blockNo;
    private int lotNo;
    private String propertyType;
    private double size;
    private double price;
    private String status;

    public Lot(int blockNo, int lotNo, String propertyType, double size, double price, String status) {
        this.blockNo = blockNo;
        this.lotNo = lotNo;
        this.propertyType = propertyType;
        this.size = size;
        this.price = price;
        this.status = status;
    }

    public abstract String getPropertyType();
    public abstract double calculatePrice(String transactionType);

    public String getPropertyCode() {
        return "B" + blockNo + "L" + lotNo;
    }

    public int getBlockNo() {
        return blockNo;
    }

    public void setBlockNo(int blockNo) {
        this.blockNo = blockNo;
    }

    public int getLotNo() {
        return lotNo;
    }

    public void setLotNo(int lotNo) {
        this.lotNo = lotNo;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
