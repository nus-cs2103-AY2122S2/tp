package seedu.address.model.property;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;

/**
 * Represents a Property's region.
 * Guarantees: is valid as declared in {@link #isValidRegion(String)}
 */
public enum Region {
    NORTH("North"),
    SOUTH("South"),
    EAST("East"),
    WEST("West"),
    CENTRAL("Central");

    public static final String MESSAGE_CONSTRAINTS = "Region must be one of: " + Arrays.toString(Region.values());
    public static final String VALIDATION_REGEX = "^(?i)(north|south|east|west|central)$";

    public final String value;

    Region(String region) {
        value = region;
    }

    /**
     * Maps a string to a {@code Region}.
     */
    public static Region fromString(String region) {
        requireNonNull(region);

        for (Region r : Region.values()) {
            if (r.value.equalsIgnoreCase(region)) {
                return r;
            }
        }

        throw new IllegalArgumentException("Invalid region: " + region + ". " + MESSAGE_CONSTRAINTS);
    }

    public static boolean isValidRegion(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

}
