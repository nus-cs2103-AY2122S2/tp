package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.logic.parser.Prefix;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a Person's Membership in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Membership {
    public static final Prefix PREFIX = new Prefix("m/", true);
    public static final Prefix DATE_PREFIX = new Prefix("d/", false);
    public static final String MESSAGE_CONSTRAINTS =
            "Membership names should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String MESSAGE_DATE_CONSTRAINTS =
            "Date is in an invalid format";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private final String value;
    private final LocalDate date;

    /**
     * Constructs a {@code Membership}.
     *
     * @param name A valid membership.
     */
    public Membership(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        value = name;
        date = null;
    }

    /**
     * Overloaded Constructs a {@code Membership}.
     *
     * @param name A valid membership name.
     * @param date A valid date.
     */
    public Membership(String name, LocalDate date) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        value = name;
        this.date = date;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given date is a valid date.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test);
        }
        catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public String getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        String date_postFix = "";
        if (date != null) {
            date_postFix = " since " + date.toString();
        }
        return value + date_postFix;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Membership // instanceof handles nulls
                && value.equals(((Membership) other).value) && date.equals(((Membership) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
