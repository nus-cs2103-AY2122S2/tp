package seedu.address.model.house;

/**
 * Price range of a house, not associated with House class as the price is decided by seller
 */
public class PriceRange {
    private final int lower;
    private final int upper;

    /**
     * Constructor
     * @param lower
     * @param upper
     */
    public PriceRange(int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
    }

    /**
     * update a new priceRange
     * @param lower
     * @param upper
     */
    public PriceRange updatePrice(int lower, int upper) {
        return new PriceRange(lower, upper);
    }

    @Override
    public String toString() {
        return "["
                + lower
                + ','
                + upper
                + ']';
    }
}
