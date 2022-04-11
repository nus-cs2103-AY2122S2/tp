package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.SimpleDateFormat;
import java.util.Date;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a Person's deadline in the address book.
 */
public class Deadline implements Comparable<Deadline> {
    public static final String MESSAGE_CONSTRAINTS = "Deadlines can only take dd/mm/yyyy and must have a description";
    public static final String NO_DEADLINE_PLACEHOLDER = "*No deadline specified*";
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
    public static final String STORAGE_PARSE_ERROR = "Deadline in storage could not be parsed properly";

    public final String description;
    public final String value;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param dateAsString a valid date.
     */
    public Deadline(String description, String dateAsString) {
        requireNonNull(dateAsString);
        checkArgument(isValidDeadline(description, dateAsString), MESSAGE_CONSTRAINTS);
        if (dateAsString.equals(NO_DEADLINE_PLACEHOLDER)) {
            new Deadline();
        }
        this.description = description;
        value = dateAsString;
    }

    /**
     * Constructs a {@code Deadline} object that is empty.
     */
    public Deadline() {
        description = "";
        value = NO_DEADLINE_PLACEHOLDER;
    }

    private Date getDate() throws ParseException {
        if (value.equals(NO_DEADLINE_PLACEHOLDER)) {
            return new Date(Long.MAX_VALUE);
        }

        FORMATTER.setLenient(false);
        Date date;

        try {
            date = FORMATTER.parse(value);
        } catch (java.text.ParseException e) {
            throw new ParseException(STORAGE_PARSE_ERROR);
        }

        return date;
    }

    @Override
    public String toString() {
        if (!description.equals("")) {
            return description + ": " + value;
        }
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && value.equals(((Deadline) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Deadline other) throws ClassCastException {
        try {
            return this.getDate().compareTo(other.getDate());
        } catch (ParseException e) {
            throw new ClassCastException(e.getMessage());
        }
    }

    /**
     * Check if given string is a valid date.
     *
     * @param dateAsString the given string.
     * @return true if given string is a valid date.
     */
    public static boolean isValidDeadline(String description, String dateAsString) {
        if (description.equals("") && dateAsString.equals(NO_DEADLINE_PLACEHOLDER)) {
            return true;
        }

        if (description.equals("")) {
            return false;
        }

        if (!description.matches(VALIDATION_REGEX)) {
            return false;
        }

        FORMATTER.setLenient(false);
        Date date;

        try {
            date = FORMATTER.parse(dateAsString);

            return date.before(FORMATTER.parse("01/01/10000"));
        } catch (java.text.ParseException e) {
            return false;
        }
    }
}
