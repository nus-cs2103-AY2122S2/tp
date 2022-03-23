package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import seedu.address.logic.parser.Prefix;

/**
 * Represents a Person's birthday in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValid(String)}
 */
public class TransactionDate extends TransactionField {

    public static final String FIELD_NAME = "Transaction Date";

    public static final Prefix PREFIX = new Prefix("td/", true);

    public static final String MESSAGE_CONSTRAINTS =
            "Due date should be in YYYY-MM-DD format and must exist";

    private final LocalDate value;

    /**
     * Constructs a {@code Birthday}.
     *
     * @param  transactionDate A valid due date.
     */
    public TransactionDate(String transactionDate) {
        super(PREFIX);
        requireNonNull(transactionDate);
        transactionDate = transactionDate.trim();
        checkArgument(DueDate.isValid(transactionDate), MESSAGE_CONSTRAINTS);
        value = LocalDate.parse(transactionDate);
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

    private String generateStringRep() {
        return FIELD_NAME + ": " + this.value;
    }

    @Override
    public String toString() {
        return generateStringRep();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TransactionDate)) {
            return false;
        }
        return ((TransactionDate) other).value.equals(value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
