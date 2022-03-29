package seedu.address.model.team;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Team in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTeamName(String)}
 */
public class Team {

    public static final String MESSAGE_CONSTRAINTS = "teams names should be alphanumeric";
    public static final String VALIDATION_REGEX = "^[\\w|\\d]+[\\w|\\d|\\s]+[\\w|\\d]+$";

    public final String teamName;

    /**
     * Constructs a {@code team}.
     *
     * @param teamName A valid team name.
     */
    public Team(String teamName) {
        requireNonNull(teamName);
        checkArgument(isValidTeamName(teamName), MESSAGE_CONSTRAINTS);
        this.teamName = teamName;
    }

    /**
     * Returns true if a given string is a valid team name.
     */
    public static boolean isValidTeamName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Team // instanceof handles nulls
                && teamName.equals(((Team) other).teamName)); // state check
    }

    @Override
    public int hashCode() {
        return teamName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + teamName + ']';
    }

}
