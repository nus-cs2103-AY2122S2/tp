package seedu.address.model.procedure;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Procedure's information in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidInformation(String)}
 */
public class Information implements Comparable<Information> {

    public static final String MESSAGE_CONSTRAINTS =
            "Information can take any values but it should not be blank";

    /*
     * The first character of the information must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String info;

    /**
     * Constructs an {@code Information}.
     *
     * @param info A valid Information.
     */
    public Information(String info) {
        requireNonNull(info);
        checkArgument(isValidInformation(info), MESSAGE_CONSTRAINTS);
        this.info = info;
    }

    /**
     * Returns true if a given string is a valid information.
     */
    public static boolean isValidInformation(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return info;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Information // instanceof handles nulls
                && info.equals(((Information) other).info)); // state check
    }

    @Override
    public int hashCode() {
        return info.hashCode();
    }

    @Override
    public int compareTo(Information otherInfo) {
        return info.compareTo(otherInfo.info);
    }
}
