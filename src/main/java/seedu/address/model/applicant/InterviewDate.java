package seedu.address.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InterviewDate {
    public static final String MESSAGE_CONSTRAINTS =
            "Interview date should be in yyyy-mm-dd format, and it should not be blank";

    /*
     * The first character of the date must not be a whitespace,
     * and the date has to be valid and in the format of yyyy-mm-dd with leading zeros.
     */
    public static final String VALIDATION_REGEX = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";
    public final LocalDate date;

    /**
     * Constructs an Interview Date
     *
     * @param interviewDate A valid interviewDate.
     */
    public InterviewDate(String interviewDate) {
        requireNonNull(interviewDate);
        checkArgument(isValidDate(interviewDate), MESSAGE_CONSTRAINTS);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        date = LocalDate.parse(interviewDate, format);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return date.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InterviewDate // instanceof handles nulls
                && date.equals(((InterviewDate) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
