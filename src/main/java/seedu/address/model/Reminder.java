package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Reminder {

    public static final String MESSAGE_CONSTRAINTS = "Reminder must be a non-empty String";
    public static final String VALIDATION_REGEX = "^(?=\\s*\\S).*$";

    public final String value;

    /**
     * Constructs a {@code Reminder}.
     *
     * @param reminder A valid reminder.
     */
    public Reminder(String reminder) {
        requireNonNull(reminder);
        checkArgument(isValidReminder(reminder), MESSAGE_CONSTRAINTS);
        value = reminder;
    }

    public static boolean isValidReminder(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Reminder // instanceof handles nulls
                && value.equals(((Reminder) other).value)); // state check
    }

    @Override
    public String toString() {
        return String.format("Reminder: %s", value);
    }
}
