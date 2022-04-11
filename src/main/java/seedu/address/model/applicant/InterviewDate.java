package seedu.address.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class InterviewDate implements Comparable<InterviewDate> {
    public static final String MESSAGE_CONSTRAINTS =
            "Date should be of the format yyyy-mm-dd "
                    + "and adhere to the following constraints:\n"
                    + "1. The date should not be blank\n"
                    + "2. The date should be within a range of 1-31\n"
                    + "3. The month should be within a range of 1-12"
                    + "Please also ensure that the date exists E.g 2022-02-30 does not exist.";
    /*
     * The first character of the date must not be a whitespace,
     * and the date has to be valid and in the format of yyyy-mm-dd with leading zeros.
     */
    public static final String VALIDATION_REGEX = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";
    public final LocalDate date;
    public final boolean isInit;

    /**
     * Constructs an Interview Date
     *
     * @param interviewDate A valid interviewDate.
     */
    public InterviewDate(String interviewDate) {
        requireNonNull(interviewDate);
        checkArgument(isValidInterviewDate(interviewDate), MESSAGE_CONSTRAINTS);
        if (interviewDate.equals("PENDING")) {
            isInit = false;
            DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu-MM-dd");
            date = LocalDate.parse("1999-01-19", format);
        } else {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu-MM-dd");
            isInit = true;
            date = LocalDate.parse(interviewDate, format);
        }
    }

    /**
     * Constructs an Interview Date with state not initialised
     */
    public InterviewDate() {
        isInit = false;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        date = LocalDate.parse("1999-01-19", format);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidInterviewDate(String test) {
        requireNonNull(test);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
        if (test.equals("PENDING")) {
            return true;
        }
        try {
            LocalDate parsedDate = LocalDate.parse(test, format);
            return test.matches(VALIDATION_REGEX);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public String toString() {
        if (isInit) {
            return date.toString();
        } else {
            return "PENDING";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.applicant.InterviewDate // instanceof handles nulls
                && date.equals(((seedu.address.model.applicant.InterviewDate) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    @Override
    public int compareTo(InterviewDate o) {
        return !isInit && o.isInit
                ? 1
                : isInit && !o.isInit
                    ? -1
                    : date.compareTo(o.date);
    }
}
