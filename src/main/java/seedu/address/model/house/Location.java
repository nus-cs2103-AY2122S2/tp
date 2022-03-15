package seedu.address.model.house;

import java.util.Objects;

/**
 * Location of a place
 */
public class Location {

    private String address;

    public Location(String address) {
        this.address = address;
    }

    public void updateAddress(String newAddress) {
        this.address = newAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Location location = (Location) o;
        return address.equals(location.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    public String toString() {
        return address;
    }
}
