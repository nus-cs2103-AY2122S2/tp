package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Student's GitHub Username in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGithubUsername(String)}
 */
public class GithubUsername {

    public static final String MESSAGE_CONSTRAINTS =
            "GitHub usernames should only contain at most 39 alphanumeric characters and hyphens,"
                    + " but cannot have consecutive hyphens and cannot begin or end with hyphens.";

    /*
     * The first and last character of the GitHub Username must not be a hyphen.
     * GitHub Usernames can't have consecutive hyphens
     * and can't be longer than 39 alphanumeric characters.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}](?:[\\p{Alnum}]|-(?=[\\p{Alnum}])){0,38}";

    public final String username;

    /**
     * Constructs an {@code GithubUsername}.
     *
     * @param githubUsername A valid GitHub Username.
     */
    public GithubUsername(String githubUsername) {
        requireNonNull(githubUsername);
        checkArgument(isValidGithubUsername(githubUsername), MESSAGE_CONSTRAINTS);
        username = githubUsername;
    }

    /**
     * Returns true if a given string is a valid GitHub Username.
     */
    public static boolean isValidGithubUsername(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return username;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GithubUsername // instanceof handles nulls
                && username.equals(((GithubUsername) other).username)); // state check
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
