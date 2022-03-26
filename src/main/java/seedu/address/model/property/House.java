package seedu.address.model.property;

import java.util.Objects;

public class House {

    //identifiers of a house
    private HouseType houseType;
    private Location location;

    /**
     * Constructor
     * @param houseType the HouseType of a house.
     * @param location the location of a house.
     */
    public House(HouseType houseType, String location) {
        this.houseType = houseType;
        this.location = new Location(location);
    }


    public HouseType getHouseType() {
        return houseType;
    }

    public String getHouseTypeToString() {
        return houseType.toString();
    }

    public Location getLocation() {
        return location;
    }

    public String getLocationToString() {
        return location.toString();
    }

    /**
     * Updates the location of a house.
     *
     * @param location the location of a house.
     */
    public void updateLocation(String location) {
        this.location = new Location(location);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof House // instanceof handles nulls
                && houseType.equals(((House) other).houseType)
                && location.equals(((House) other).location)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(houseType, location);
    }

}
