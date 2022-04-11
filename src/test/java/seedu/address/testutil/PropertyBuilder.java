package seedu.address.testutil;

import seedu.address.model.person.Address;
import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.property.Region;
import seedu.address.model.property.Size;

public class PropertyBuilder {

    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_REGION = "West";
    public static final String DEFAULT_SIZE = "5-room";
    public static final String DEFAULT_PRICE = "$850000";

    private Address address;
    private Region region;
    private Size size;
    private Price price;

    /**
     * Creates a {@code PropertyBuilder} with the default values.
     */
    public PropertyBuilder() {
        address = new Address(DEFAULT_ADDRESS);
        region = Region.fromString(DEFAULT_REGION);
        size = Size.fromString(DEFAULT_SIZE);
        price = new Price(DEFAULT_PRICE);
    }

    /**
     * Initializes the PropertyBuilder with the data of {@code propertyToCopy}.
     */
    public PropertyBuilder(Property propertyToCopy) {
        address = propertyToCopy.getAddress();
        region = propertyToCopy.getRegion();
        size = propertyToCopy.getSize();
        price = propertyToCopy.getPrice();
    }

    /**
     * Sets the {@code address} of the {@code Property} that we are building.
     */
    public PropertyBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code region} of the {@code Property} that we are building.
     */
    public PropertyBuilder withRegion(String region) {
        this.region = Region.fromString(region);
        return this;
    }

    /**
     * Sets the {@code size} of the {@code Property} that we are building.
     */
    public PropertyBuilder withSize(String size) {
        this.size = Size.fromString(size);
        return this;
    }

    /**
     * Sets the {@code price} of the {@code Property} that we are building.
     */
    public PropertyBuilder withPrice(String price) {
        this.price = new Price(price);
        return this;
    }


    public Property build() {
        return new Property(region, address, size, price);
    }
}
