package seedu.address.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Orders's deliveryDateTime in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeliveryDateTime(String)}
 */
public class DeliveryDateTime {

    public static final String MESSAGE_CONSTRAINTS = "DeliveryDateTime should be in the format dd-MM-yyyy HH:mm "
            + "or a natural date (e.g. Mon 22:30, Wednesday 10:20) ";

    public static final String MESSAGE_CONSTRAINTS_LEAP_YEAR = "Your current input represents a leap day but the "
            + "year is not a leap year! Please check the date again!";

    /**
     * The first character of the deliveryDateTime must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[0-9]{2}-[0-9]{2}-[0-9]{4} [0-9]{2}:[0-9]{2}";
    public static final DateTimeFormatter PARSER_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public static final DateTimeFormatter STRING_FORMATTER = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy, hh:mm a");

    public final LocalDateTime value;

    /**
     * Constructs an {@code DeliveryDateTime}.
     *
     * @param deliveryDateTime A valid deliveryDateTime.
     */
    public DeliveryDateTime(String deliveryDateTime) {
        requireNonNull(deliveryDateTime);
        checkArgument(isValidDeliveryDateTime(deliveryDateTime), MESSAGE_CONSTRAINTS);
        checkArgument(isValidLeapYearDeliveryDateTimeValue(deliveryDateTime), MESSAGE_CONSTRAINTS_LEAP_YEAR);
        value = LocalDateTime.parse(deliveryDateTime, PARSER_FORMATTER);
    }

    public static boolean isValidDeliveryDateTimeFormat(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidDeliveryDateTimeValue(String test) {
        try {
            LocalDateTime.parse(test, PARSER_FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidLeapYearDeliveryDateTimeValue(String test) {
        String[] date = test.split(" ");
        String[] dateSplit = date[0].split("-");
        int day = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int year = Integer.parseInt(dateSplit[2]);
        boolean onLeapDay = (month == 2) && (day == 29);
        if (onLeapDay) {
            if (isLeapYear(year)) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isLeapYear(int year) {
        //@@author {Geralddtan} - reused
        //Reused from https://www.geeksforgeeks.org/java-program-to-find-if-a-given-year-is-a-leap-year/
        return ((year % 400 == 0)
                || ((year % 4 == 0) && (year % 100 != 0)));
        //@@author
    }

    /**
     * Returns true if a given string is a valid datetime.
     */
    public static boolean isValidDeliveryDateTime(String test) {
        return isValidDeliveryDateTimeFormat(test) && isValidDeliveryDateTimeValue(test);
    }

    public String toJsonSavedString() {
        return value.format(PARSER_FORMATTER);
    }

    @Override
    public String toString() {
        return value.format(STRING_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeliveryDateTime // instanceof handles nulls
                && value.equals(((DeliveryDateTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public LocalDateTime getValue() {
        return this.value;
    }

}
