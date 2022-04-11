package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Meeting's recurrence status in the meeting list.
 * Guarantees: immutable; is valid as declared in {@link #isValidRecurringStatus(String)}
 */
public class IsRecurring {

    public static final String MESSAGE_CONSTRAINTS =
            "Meeting recurrence status can only be a single letter `Y` or `N`";

    public static final String VALIDATION_REGEX = "[YN]";
    public static final String RECURRING = "Y";
    public static final String NON_RECURRING = "N";

    public final boolean isRecurring;

    /**
     * Constructs a {@code IsRecurring}.
     *
     * @param recurStatus A valid recurrence status.
     */
    public IsRecurring(String recurStatus) {
        requireNonNull(recurStatus);
        recurStatus = recurStatus.toUpperCase();
        checkArgument(isValidRecurringStatus(recurStatus), MESSAGE_CONSTRAINTS);
        this.isRecurring = recurStatus.equals(RECURRING);
    }

    /**
     * Returns true if a given string is a valid recurrence status.
     *
     * @param test The {@code String} to test.
     * @return True, if the {@code String} is a valid recurrence status.
     */
    public static boolean isValidRecurringStatus(String test) {
        test = test.toUpperCase();
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return isRecurring ? RECURRING : NON_RECURRING;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsRecurring // instanceof handles nulls
                && isRecurring == ((IsRecurring) other).isRecurring); // state check
    }
}
