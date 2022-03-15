package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

import seedu.address.logic.parser.Prefix;

/**
 * Represents a Person's birthday in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValid(String)}
 */
public class DueDate extends TransactionField {
    public static final Prefix PREFIX = new Prefix("dd/", true);
    public static final String MESSAGE_CONSTRAINTS =
            "Due date should be in YYYY-MM-DD format and must exist";

    private final LocalDate value;

    /**
     * Constructs a {@code Birthday}.
     *
     * @param dueDate A valid due date.
     */
    public DueDate(String dueDate) {
        super(PREFIX);
        requireNonNull(dueDate);
        dueDate = dueDate.trim(); // Wow, look at that, we don't need a whole fucking parser to do one line.
        checkArgument(DueDate.isValid(dueDate), MESSAGE_CONSTRAINTS);
        value = LocalDate.parse(dueDate);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValid(String str) {
        try {
            LocalDate.parse(str);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public String getValue() {
        return value.toString();
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof DueDate)) {
            return false;
        }
        return ((DueDate) other).value.equals(value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
