package seedu.address.testutil;


import seedu.address.model.property.House;
import seedu.address.model.property.PriceRange;
import seedu.address.model.property.PropertyToBuy;

/**
 * A utility class to help with building PropertyToBuy objects.
 */
public class PropertyToBuyBuilder {

    public static final House DEFAULT_HOUSE = new HouseBuilder().build();
    public static final PriceRange DEFAULT_PRICE_RANGE = new PriceRange(0 , 0);

    private House house;
    private PriceRange priceRange;

    /**
     * Creates a {@code PropertyToBuyBuilder} with the default details.
     */
    public PropertyToBuyBuilder() {
        this.house = DEFAULT_HOUSE;
        this.priceRange = DEFAULT_PRICE_RANGE;
    }

    /**
     * Initializes the houseBuilder with the data of {@code PropertyToBuyToCopy}.
     */
    public PropertyToBuyBuilder(PropertyToBuy propertyToBuyToCopy) {
        this.house = propertyToBuyToCopy.getHouse();
        this.priceRange = propertyToBuyToCopy.getPriceRange();
    }

    /**
     * Sets the {@code House} of the {@code PropertyToBuy}.
     */
    public PropertyToBuyBuilder withHouse(House house) {
        this.house = house;
        return this;
    }

    /**
     * Sets the {@code PriceRange} of the {@code PropertyToBuy}.
     */
    public PropertyToBuyBuilder withPriceRange(PriceRange priceRange) {
        this.priceRange = priceRange;
        return this;
    }

    public PropertyToBuy build() {
        return new PropertyToBuy(house, priceRange);
    }
}
