package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task's deadline in the task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline implements Comparable<Deadline> {

    public static final String MESSAGE_CONSTRAINTS =
            "Deadlines should be a valid date, and in the format YYYY-MM-DD";
    static final Deadline MIN_DEADLINE = new Deadline(LocalDate.MIN);
    static final Deadline MAX_DEADLINE = new Deadline(LocalDate.MAX);

    public final String value;
    private final LocalDate localDateValue;


    /**
     * Constructs a {@code Deadline}. Accepts date in the format of yyyy-mm-dd.
     *
     * @param deadline A valid deadline.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        value = deadline;
        localDateValue = LocalDate.parse(deadline);
    }

    /**
     * Constructs a {@code Deadline} using {@code LocalDate}.
     *
     * @param localDate a non-null {@code LocalDate}
     */
    private Deadline(LocalDate localDate) {
        requireNonNull(localDate);
        this.localDateValue = localDate;
        this.value = localDate.toString(); // Not used.
    }

    /**
     * Returns true if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String test) {

        if (test == null) {
            return false;
        }
        try {
            LocalDate.parse(test);
            return true;
        } catch (DateTimeParseException error) {
            return false;
        }
    }

    @Override
    public String toString() {
        return localDateValue.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && localDateValue.equals(((Deadline) other).localDateValue)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Deadline d) {
        return this.localDateValue.compareTo(d.localDateValue);
    }
}
