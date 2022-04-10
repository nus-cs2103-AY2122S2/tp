package seedu.address.testutil;

import seedu.address.model.property.Address;
import seedu.address.model.property.House;
import seedu.address.model.property.PriceRange;
import seedu.address.model.property.PropertyToSell;

public class PropertyToSellBuilder {

    public static final House DEFAULT_HOUSE = new HouseBuilder().build();
    public static final PriceRange DEFAULT_PRICE_RANGE = new PriceRange(0 , 0);
    public static final String DEFAULT_ADDRESS = "200 Flower Road";

    private House house;
    private PriceRange priceRange;
    private Address address;

    /**
     * Creates a {@code PropertyToBuyBuilder} with the default details.
     */
    public PropertyToSellBuilder() {
        this.house = DEFAULT_HOUSE;
        this.priceRange = DEFAULT_PRICE_RANGE;
        this.address = new Address(DEFAULT_ADDRESS);
    }

    /**
     * Initializes the houseBuilder with the data of {@code PropertyToBuyToCopy}.
     */
    public PropertyToSellBuilder(PropertyToSell propertyToSellToCopy) {
        this.house = propertyToSellToCopy.getHouse();
        this.priceRange = propertyToSellToCopy.getPriceRange();
        this.address = propertyToSellToCopy.getAddress();
    }

    /**
     * Sets the {@code House} of the {@code PropertyToBuy}.
     */
    public PropertyToSellBuilder withHouse(House house) {
        this.house = house;
        return this;
    }

    /**
     * Sets the {@code PriceRange} of the {@code PropertyToBuy}.
     */
    public PropertyToSellBuilder withPriceRange(PriceRange priceRange) {
        this.priceRange = priceRange;
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code PropertyToBuy}.
     */
    public PropertyToSellBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    public PropertyToSell build() {
        return new PropertyToSell(house, priceRange, address);
    }
}
