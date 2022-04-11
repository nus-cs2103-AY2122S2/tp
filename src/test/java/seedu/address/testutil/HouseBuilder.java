package seedu.address.testutil;

import seedu.address.model.property.House;
import seedu.address.model.property.HouseType;
import seedu.address.model.property.Location;

/**
 * A utility class to help with building House objects.
 */
public class HouseBuilder {

    public static final HouseType DEFAULT_HOUSE_TYPE = HouseType.BUNGALOW;
    public static final String DEFAULT_LOCATION = "Clementi";

    private HouseType houseType;
    private Location location;

    /**
     * Creates a {@code houseBuilder} with the default details.
     */
    public HouseBuilder() {
        this.houseType = DEFAULT_HOUSE_TYPE;
        this.location = new Location(DEFAULT_LOCATION);
    }

    /**
     * Initializes the houseBuilder with the data of {@code houseToCopy}.
     */
    public HouseBuilder(House houseToCopy) {
        houseType = houseToCopy.getHouseType();
        location = houseToCopy.getLocation();
    }

    /**
     * Sets the {@code HouseType} of the {@code House}.
     */
    public HouseBuilder withHouseType(HouseType houseType) {
        this.houseType = houseType;
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code House}.
     */
    public HouseBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    public House build() {
        return new House(houseType, location);
    }
}
