package seedu.address.model.property;


/**
 * A demand indicating the property a buyer wants to buy a house for, and the PriceRange it is willing to buy for.
 * Note that a buyer is not looking for a specific address, unlike the seller who knows the specific address of the
 * house it is selling.
 */
public class PropertyToBuy {

    /**
     * The House a buyer is interested in buying.
     */
    private House house;

    /**
     * The PriceRange a buyer is willing to pay to buy the House.
     */
    private PriceRange buyRange;

    public PropertyToBuy(House house, PriceRange buyRange) {
        this.house = house;
        this.buyRange = buyRange;
    }

    public House getHouse() {
        return this.house;
    }

    public PriceRange getBuyRange() {
        return this.buyRange;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof PropertyToBuy // instanceof handles nulls
            && house.equals(((PropertyToBuy) other).house)
            && buyRange.equals(((PropertyToBuy) other).buyRange)); // state check
    }
}
