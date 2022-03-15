package seedu.address.model.house;

public class House {

    //identifiers of a house
    private HouseType housetype;
    private Location location;

    /**
     * Constructor
     * @param housetype
     * @param address
     */
    public House(HouseType housetype, String address) {
        this.housetype = housetype;
        this.location = new Location(address);
    }

    /**
     * Update the address of a house
     * @param address
     */
    public void updateAddress(String address) {
        this.location = new Location(address);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof House // instanceof handles nulls
                && housetype.equals(((House) other).housetype)
                && location.equals(((House) other).location)); // state check
    }

    @Override
    public int hashCode() {
        return location.hashCode();
    }

}
