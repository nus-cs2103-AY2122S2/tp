package seedu.address.model.property;

/**
 * A demand indicating the property a seller wants to sell a house for, and the PriceRange it is willing to sell for.
 */
public class PropertyToSell {

    /**
     * The House a seller is selling.
     */
    private House house;

    /**
     * The PriceRange a buyer is willing to receive to sell the House.
     */
    private PriceRange sellRange;

    /**
     * The exact address of this house.
     */
    private String address;

    public PropertyToSell(House house, PriceRange sellRange, String address) {
        this.house = house;
        this.sellRange = sellRange;
        this.address = address;
    }

    public House getHouse() {
        return this.house;
    }

    public PriceRange getSellRange() {
        return this.sellRange;
    }

    public String getAddress() {
        return this.address;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof PropertyToSell // instanceof handles nulls
            && house.equals(((PropertyToSell) other).house)
            && sellRange.equals(((PropertyToSell) other).sellRange)
            && address.equals((((PropertyToSell) other).address))); // state check
    }
}
