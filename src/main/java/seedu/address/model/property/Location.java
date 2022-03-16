package seedu.address.model.property;

import java.util.Objects;

/**
 * Location is an area in Singapore in the map, for example Serangoon, Kovan, Kent Ridge, etc.
 */
public class Location {

    private String location;

    public Location(String location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }

    @Override
    public String toString() {
        return location;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Location // instanceof handles nulls
            && location.equals(((Location) other).location)); // state check
    }
}
