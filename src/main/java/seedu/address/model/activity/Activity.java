package seedu.address.model.activity;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Activity in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidActivityName(String)}
 */
public class Activity {

    public static final String MESSAGE_CONSTRAINTS = "Activity names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String activityName;

    /**
     * Constructs a {@code Activity}.
     *
     * @param activityName A valid activity name.
     */
    public Activity(String activityName) {
        requireNonNull(activityName);
        checkArgument(isValidActivityName(activityName), MESSAGE_CONSTRAINTS);
        this.activityName = activityName;
    }

    /**
     * Returns true if a given string is a valid activity name.
     */
    public static boolean isValidActivityName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Activity // instanceof handles nulls
                && activityName.equals(((Activity) other).activityName)); // state check
    }

    @Override
    public int hashCode() {
        return activityName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + activityName + ']';
    }

}
