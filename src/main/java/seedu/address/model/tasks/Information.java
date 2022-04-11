package seedu.address.model.tasks;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a task's information in the taskList.
 * Guarantees: immutable; is valid as declared in {@link #isValidInformation(String)}
 */
public class Information {

    public static final String MESSAGE_CONSTRAINTS = "Information should only contain alphanumeric "
            + "characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullInformation;

    /**
     * Constructs a {@code Information}.
     * @param information A valid information.
     */
    public Information(String information) {
        requireNonNull(information);
        checkArgument(isValidInformation(information), MESSAGE_CONSTRAINTS);
        fullInformation = information;
    }

    /**
     * Returns true if a given string is a valid information.
     */
    public static boolean isValidInformation(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean contains(String s) {
        return fullInformation.toLowerCase().contains(s.toLowerCase());
    }

    @Override
    public String toString() {
        return fullInformation;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Information // instanceof handles nulls
                        && fullInformation.equals(((Information) other).fullInformation)); // state check
    }

    @Override
    public int hashCode() {
        return fullInformation.hashCode();
    }

}
