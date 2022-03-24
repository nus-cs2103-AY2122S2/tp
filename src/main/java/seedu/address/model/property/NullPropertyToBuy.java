package seedu.address.model.property;

/**
 * Represents a PropertyToBuy instance which has not been assigned any values yet.
 * Exists when the user first creates a Buyer, or if he decides to remove the property later on.
 */
public class NullPropertyToBuy extends PropertyToBuy {

    private final static NullPropertyToBuy nullPropertyToBuy =
            new NullPropertyToBuy(new House(HouseType.NULLHOUSETYPE, "NullHouseLocation"),
                    new PriceRange(0,0));

    public NullPropertyToBuy(House house, PriceRange price) {
        super(house, price);
    }

    public static NullPropertyToBuy getNullPropertyToBuy() {
        return nullPropertyToBuy;
    }

    // TODO: Add special properties for this property type since we do not want to
    // misuse


    @Override
    public boolean equals(Object other) {
        return (other == this || other instanceof NullPropertyToBuy);
    }
}
