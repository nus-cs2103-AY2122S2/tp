package seedu.address.model.student;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's GitHub in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGitHub(String)}
 */
public class GitHub {

    public static final String MESSAGE_CONSTRAINTS = "Github username must only contain alphanumeric "
        + "characters or hyphens.\n    Github username cannot have multiple consecutive hyphens.\n"
        + "    Github username cannot begin or end with a hyphen.\n"
        + "    Maximum is 39 characters and minimum of 2 characters.\n    GitHub can be blank.";

    /*
     * The first character of the gitHub must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[A-Za-z0-9][A-Za-z0-9-]{0,37}[A-Za-z0-9]";

    public final String value;

    /**
     * Constructs an {@code GitHub}.
     *
     * @param gitHub A valid gitHub.
     */
    public GitHub(String gitHub) {
        if (gitHub == null) { //if gitHub is empty it will exist as an empty string
            value = "";
        } else {
            checkArgument(isValidGitHub(gitHub), MESSAGE_CONSTRAINTS);
            value = gitHub;
        }
    }

    /**
     * Returns true if a given string is a valid gitHub.
     */
    public static boolean isValidGitHub(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the gitHub is null.
     */
    public boolean isNull() {
        return value.equals("");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GitHub // instanceof handles nulls
                && value.equals(((GitHub) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
