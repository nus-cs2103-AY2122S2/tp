package seedu.address.model.position;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Position's name in HireLah.
 * Guarantees: immutable; is valid as declared in {@link #isValidPositionName(String)}
 */
public class PositionName {

    public static final String MESSAGE_CONSTRAINTS =
            "Position name can contain any characters and spaces, and it should not be blank.\n"
                    + "Position name should contain at least one alphanumeric character (e.g. \"1\" or \"a\")\n"
                    + "Length of position name is restricted to a maximum of 100 characters.";

    /*
     * The first character of the position must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "(?=.*[\\p{Alnum}].*)[^\\s].{0,99}";

    public final String positionName;

    /**
     * Constructs a {@code PositionName}.
     *
     * @param name A valid and unique position name.
     */
    public PositionName(String name) {
        requireNonNull(name);
        checkArgument(isValidPositionName(name), MESSAGE_CONSTRAINTS);
        positionName = name;
    }

    /**
     * Returns true if a given string is a valid position name.
     *
     * @param test
     * @return
     */
    public static boolean isValidPositionName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return positionName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PositionName // instanceof handles nulls
                && positionName.equals(((PositionName) other).positionName)); // state check
    }

    @Override
    public int hashCode() {
        return positionName.hashCode();
    }

}
