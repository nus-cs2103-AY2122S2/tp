package seedu.address.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents the interview date and time of an Application in InternApply.
 */

public class InterviewSlot {

    public static final String FORMAT_DATETIME_INPUT = "dd-MM-uuuu HH:mm";
    public static final String FORMAT_DATETIME_DISPLAY = "d MMM yyyy HH:mm";
    public static final String MESSAGE_CONSTRAINTS = "Interview date should be of the format "
            + FORMAT_DATETIME_INPUT
            + ", containing only numbers and respective separators.";
    public static final String MESSAGE_NOT_SET = "Interview date is not set.";

    public final LocalDateTime value;

    /**
     * Constructs a {@code InterviewDateTime}.
     *
     * @param interviewDateTime A valid interview date and time.
     */
    public InterviewSlot(String interviewDateTime) {
        requireNonNull(interviewDateTime);
        checkArgument(isValidDateTime(interviewDateTime), MESSAGE_CONSTRAINTS);
        value = toLocalDateTime(interviewDateTime);
    }

    /**
     * Constructs a {@code InterviewDateTime}.
     */
    public InterviewSlot() {
        value = LocalDateTime.MAX;
    }

    /**
     * Returns true if a given string is a valid date and time.
     */
    public static boolean isValidDateTime(String input) {
        try {
            toLocalDateTime(input);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if a given string is a placeholder for an empty date and time.
     */
    public static boolean isNotSet(String test) {
        return test.equals(LocalDateTime.MAX.format(DateTimeFormatter
                .ofPattern(InterviewSlot.FORMAT_DATETIME_INPUT)));
    }

    public LocalDateTime getValue() {
        return this.value;
    }

    /**
     * Returns true if the interview slot is within a week of the current local date of the local machine.
     */
    public boolean isUpcoming() {
        // The current date of the local machine
        LocalDateTime today = LocalDateTime.now();

        // Calculate the date a week from the current date of the local machine
        LocalDateTime nextWeek = today.plusDays(7);

        return this.value.isAfter(today) && this.value.isBefore(nextWeek);
    }

    /**
     * Returns the value of interview slot in the input String format as per FORMAT_DATETIME_INPUT.
     */
    public String toInputString() {
        return this.value.format(DateTimeFormatter.ofPattern(InterviewSlot.FORMAT_DATETIME_INPUT));
    }

    /**
     * Returns the value of interview slot in the form of {@code LocalDateTime}.
     */
    public LocalDateTime getInterviewSlot() {
        return value;
    }

    @Override
    public String toString() {
        return !isNotSet(toInputString())
                ? value.format(DateTimeFormatter.ofPattern(FORMAT_DATETIME_DISPLAY))
                : MESSAGE_NOT_SET;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InterviewSlot // instanceof handles nulls
                && this.toInputString().equals(((InterviewSlot) other).toInputString())); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Solution below adapted from https://stackoverflow.com/questions/65325084/datetimeformatter-parse-corrects-
     * date-information-instead-of-throwing-exceptio
     */
    private static LocalDateTime toLocalDateTime(String input) {
        return LocalDateTime.parse(input, DateTimeFormatter.ofPattern(FORMAT_DATETIME_INPUT)
                .withResolverStyle(ResolverStyle.STRICT));
    }
}
