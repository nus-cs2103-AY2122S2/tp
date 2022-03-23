package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a Due date of the transaction.
 * Optional to have.
 * Guarantees: immutable; is valid as declared in {@link #isValid(String)}
 */
public class DueDate extends TransactionField {

    public static final String FIELD_NAME = "Due Date";

    public static final Prefix PREFIX = new Prefix("dd/", false);

    public static final String MESSAGE_CONSTRAINTS =
            "Due date (if specified) should be in YYYY-MM-DD format and must exist";

    public static final String EMPTY_DUE_DATE = "";

    private final LocalDate value;

    /**
     * Constructs a {@code Birthday}.
     *
     * @param dueDate A valid due date.
     */
    public DueDate(String dueDate) {
        super(PREFIX);
        requireNonNull(dueDate);
        dueDate = dueDate.trim();

        checkArgument(DueDate.isValid(dueDate), MESSAGE_CONSTRAINTS);

        value = LocalDate.parse(dueDate);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValid(String str) {
        requireNonNull(str);

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
        return generateStringRep();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof DueDate)) {
            return false;
        }
        return ((DueDate) other).value.equals(value);
    }

    private String generateStringRep() {
        return FIELD_NAME + ": " + this.value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
