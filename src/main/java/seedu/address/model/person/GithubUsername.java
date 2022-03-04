package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Person's GitHub Username in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGithubUsername(String)}
 */
public class GithubUsername {

    public static final String MESSAGE_CONSTRAINTS =
            "Github usernames should only contain at most 39 alphanumeric characters and hypens,"
                    + " but cannot have consecutive hypens and cannot begin or end with hypens.";

    /*
     * The first character of the GitHub Username must not be a whitespace or a hypen,
     * otherwise " " (a blank string) and "-" becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}](?:[\\p{Alnum}]|-(?=[\\p{Alnum}])){0,38}";

    public final String value;

    /**
     * Constructs an {@code GithubUsername}.
     *
     * @param githubUsername A valid address.
     */
    public GithubUsername(String githubUsername) {
        requireNonNull(githubUsername);
        checkArgument(isValidGithubUsername(githubUsername), MESSAGE_CONSTRAINTS);
        value = githubUsername;
    }

    /**
     * Returns true if a given string is a valid GitHub Username.
     */
    public static boolean isValidGithubUsername(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GithubUsername // instanceof handles nulls
                && value.equals(((GithubUsername) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
