package seedu.address.model.team;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class TeamName {
    public static final String MESSAGE_CONSTRAINTS =
            "Team Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /**
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String teamName;

    /**
     * Constructs a {@code TeamName}.
     *
     * @param teamName A valid TeamName.
     */
    public TeamName(String teamName) {
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
    public String toString() {
        return teamName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TeamName // instanceof handles nulls
                && teamName.equals(((TeamName) other).teamName)); // state check
    }

    @Override
    public int hashCode() {
        return teamName.hashCode();
    }

}

