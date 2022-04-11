package seedu.address.model.property;

import java.util.Objects;

/**
 * Location is an area in Singapore in the map, for example Serangoon, Kovan, Kent Ridge, etc.
 */
public class Location {

    public static final String MESSAGE_CONSTRAINTS = "Location should not be empty";

    private String location;

    public Location(String location) {
        this.location = location.toLowerCase();
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

    /**
     * Checks if the location string is empty or not.
     *
     * @param location The string.
     * @return False if string is empty, True otherwise.
     */
    public static boolean isValidLocation(String location) {
        return !location.isEmpty();
    }
}
