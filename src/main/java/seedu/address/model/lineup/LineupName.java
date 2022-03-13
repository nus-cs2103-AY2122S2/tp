package seedu.address.model.lineup;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class LineupName {
    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /**
     * The first character of the lineup must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     **/
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String lineupName;

    /**
     * Constructs a {@code LineupName}.
     *
     * @param lineupName A valid lineup name.
     */
    public LineupName(String lineupName) {
        requireNonNull(lineupName);
        checkArgument(isValidName(lineupName), MESSAGE_CONSTRAINTS);
        this.lineupName = lineupName;
    }

    /**
     * Returns true if a given string is a valid lineup name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return lineupName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LineupName // instanceof handles nulls
                && lineupName.equals(((LineupName) other).lineupName)); // state check
    }

    @Override
    public int hashCode() {
        return lineupName.hashCode();
    }
}
