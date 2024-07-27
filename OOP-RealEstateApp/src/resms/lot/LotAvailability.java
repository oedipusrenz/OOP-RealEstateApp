package resms.lot;

public class LotAvailability {

    private final Lot lot;
    private final boolean isAvailable;

    public LotAvailability(Lot lot, boolean isAvailable) {
        this.lot = lot;
        this.isAvailable = isAvailable;
    }

    public Lot getLot() {
        return this.lot;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }
}
