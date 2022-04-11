package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a datetime in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLastContacted(String)}
 */
public class LastContacted {

    public static final String MESSAGE_CONSTRAINTS = "Last contacted should be a valid datetime in dd-MM-yyy HH:mm "
            + "format.";
    public static final String MESSAGE_FUTURE_DATETIME = "Last contacted should not be in the future.";

    /*
     * Last contacted should be in dd-MM-yyyy HH:mm format.
     */
    public static final String VALIDATION_REGEX = "(((0[1-9]|[12]\\d|3[01])-(0[13578]|1[02])-((19|[2-9]\\d)\\d{2}))|((0"
            + "[1-9]|[12]\\d|30)-(0[13456789]|1[012])-((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])-02-((19|[2-9]\\d)\\d"
            + "{2}))|(29-02-((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))\\s+([0"
            + "-1]?[0-9]|2?[0-3]):([0-5]\\d)$";

    public final String value;

    public LastContacted() {
        value = "";
    }

    /**
     * Constructs a {@code LastContacted}.
     *
     * @param dateTime A valid datetime.
     */
    public LastContacted(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidLastContacted(dateTime), MESSAGE_CONSTRAINTS);
        checkArgument(isPastLastContacted(dateTime), MESSAGE_FUTURE_DATETIME);
        value = dateTime;
    }

    /**
     * Returns true if a given string is a valid datetime.
     */
    public static boolean isValidLastContacted(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a past datetime.
     */
    public static boolean isPastLastContacted(String test) {
        LocalDateTime testDateTime = LocalDateTime.parse(test, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        return !testDateTime.isAfter(LocalDateTime.now());
    }

    /**
     * Returns the datetime as a {@code LocalDateTime} object.
     */
    public LocalDateTime getDateTime() {
        if (value.equals("")) {
            return LocalDateTime.parse("01-01-0001 00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        }
        return LocalDateTime.parse(value, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LastContacted // instanceof handles nulls
                && value.equals(((LastContacted) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
