package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's GitHub username in HackNet.
 * Guarantees: immutable; is valid as declared in {@link #isValidUsername(String)}
 */
public class GithubUsername {

    public static final String MESSAGE_CONSTRAINTS = "GitHub usernames can only contain alphanumeric"
            + " and hyphen characters, and must not be blank.\nUsername must be lesser than 40 characters and also not "
            + "have consecutive hyphens or start/end with a hyphen.";

    public static final int GITHUB_USERNAME_LENGTH = 39;
    public static final String VALIDATION_REGEX = "[\\p{Alnum}-]+";
    private static final String GITHUB_HANDLE_FORMAT = "@%s";
    private static final String GITHUB_LINK_FORMAT = "https://github.com/%s";


    public final String value;

    /**
     * Constructs an {@code GitHubUsername}.
     *
     * @param username A valid username.
     */
    public GithubUsername(String username) {
        requireNonNull(username);
        checkArgument(isValidUsername(username), MESSAGE_CONSTRAINTS);
        value = username;
    }

    /**
     * Returns true if a given string is a valid GitHub username.
     */
    public static boolean isValidUsername(String test) {
        return test.matches(VALIDATION_REGEX) && isValidUsernameLength(test) && isValidUsernameHyphens(test);
    }

    /**
     * Returns true if given string is lesser than specified length
     */
    public static boolean isValidUsernameLength(String test) {
        return test.length() <= GITHUB_USERNAME_LENGTH;
    }

    /**
     * Returns true if given string does not end/start with hyphens and do not contain consecutive hyphens
     */
    public static boolean isValidUsernameHyphens(String test) {
        boolean startWithHyphen = test.charAt(0) == '-';
        boolean endWithHyphen = test.charAt(test.length() - 1) == '-';
        return !containsConsecutiveHyphens(test) && !startWithHyphen && !endWithHyphen;
    }

    /**
     * Returns true if given string contains consecutive hyphens
     */
    public static boolean containsConsecutiveHyphens(String test) {
        boolean containsConsecHyphen = false;
        for (int i = 0; i < test.length(); i++) {
            if (i + 1 < test.length()) { //if there is a next char
                if ((test.charAt(i) == '-') && test.charAt(i + 1) == '-') {
                    containsConsecHyphen = true;
                    break;
                }
            }
        }
        return containsConsecHyphen;
    }


    /**
     * Returns the GitHub handle of this username.
     */
    public String getGithubHandle() {
        return String.format(GITHUB_HANDLE_FORMAT, value);
    }

    /**
     * Returns the link to the GitHub profile of this username.
     */
    public String getGithubProfileLink() {
        return String.format(GITHUB_LINK_FORMAT, value);
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
