package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Reminder {

    public static final String MESSAGE_CONSTRAINTS = "Reminder must have the format";
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
    public String toString() {
        return String.format("Reminder: %s", value);
    }
}
