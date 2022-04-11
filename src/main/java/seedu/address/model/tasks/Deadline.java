package seedu.address.model.tasks;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.Month;

/**
 * Represents a Task's Deadline in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline}
 */
public class Deadline implements Comparable<Deadline> {

    public static final String MESSAGE_CONSTRAINTS =
            "Deadlines should be in the format YYYY-MM-DDTHH:mm. Do not replace the \"T\".\n"
                    + "Additionally, the input date and time must be valid!\n"
                    + "Example: 2022-03-21T19:38";

    /*
     * Deadlines should be in the format YYYY-MM-DD HH:mm exactly.
     * YYYY can be any 4-digit number
     * MM can only be 00 to 19
     * DD can only be 00 to 39
     * HH can only be 00 to 29
     * mm can only be 00 to 59
     * The rest of the spots must be dashes (-) or colons (:) or the character T
     * Further validation is needed and done for the numerical ranges.
     */
    public static final String VALIDATION_REGEX = "[0-9]{4}-[0-1][0-9]-[0-3][0-9][T][0-2][0-9]:[0-5][0-9]";

    public final LocalDateTime deadline;

    /**
     * Constructs a {@code Deadline}
     *
     * @param year a valid year
     * @param month a valid month
     * @param day a valid day
     * @param hour a valid hour in 24 hour clock
     * @param minute a valid minute
     */
    public Deadline(int year, int month, int day, int hour, int minute) {
        checkArgument(isValidDeadline(year, month, day, hour, minute), MESSAGE_CONSTRAINTS);
        this.deadline = LocalDateTime.of(year, month, day, hour, minute);
    }

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline the deadline used.
     */
    public Deadline(String deadline) {
        assert(isValidDeadline(deadline));
        int[] parsed = splitDeadline(deadline);
        this.deadline = LocalDateTime.of(parsed[0], parsed[1], parsed[2], parsed[3], parsed[4]);
    }

    /**
     * Returns true if a given input is a valid deadline.
     *
     * @param year a year
     * @param month a month
     * @param day a day
     * @param hour an hour in 24 hour clock
     * @param minute a minute
     */
    public static boolean isValidDeadline(int year, int month, int day, int hour, int minute) {
        boolean isValidYear = year > 0;
        if (!isValidYear) {
            return false; // these are scattered throughout to short circuit
        }

        // if is end of century, check if it is divisible by 400. else, check if divisible by 4
        boolean isLeap = year % 100 == 0
                ? year % 400 == 0
                : year % 4 == 0;
        boolean isValidMonth = month > 0 && month < 13; // months 1 to 12 only
        if (!isValidMonth) {
            return false;
        }

        boolean isValidDay = day > 0 && (isLeap
                ? day <= Month.of(month).maxLength()
                : day <= Month.of(month).minLength());
        if (!isValidDay) {
            return false;
        }

        boolean isValidHour = hour > -1 && hour < 24; // 0 to 23
        if (!isValidHour) {
            return false;
        }

        boolean isValidMinute = minute > -1 && minute < 60; //0 to 59
        if (!isValidMinute) {
            return false;
        }

        return true;
    }

    /**
     * Checks if a given {@code String deadline} is valid.
     *
     * @param deadline the deadline to check
     * @return true if the deadline is valid
     */
    public static boolean isValidDeadline(String deadline) {
        if (!isCorrectFormat(deadline)) {
            return false;
        }
        int[] parsed = splitDeadline(deadline);
        return isValidDeadline(parsed[0], parsed[1], parsed[2], parsed[3], parsed[4]);
    }

    /**
     * Splits a given {@code String deadline} into the individual year, month, days, hours and minutes.
     *
     * @param deadline a correctly-formatted deadline.
     * @return an array consisting of the year, month, days, hours and minutes.
     */
    private static int[] splitDeadline(String deadline) {
        requireNonNull(deadline);
        assert(isCorrectFormat(deadline));
        String[] split = deadline.split("T"); //0 is date, 1 is time
        String[] splitDate = split[0].split("-");
        String[] splitTime = split[1].split(":");
        return new int[] {
                Integer.parseInt(splitDate[0]),
                Integer.parseInt(splitDate[1]),
                Integer.parseInt(splitDate[2]),
                Integer.parseInt(splitTime[0]),
                Integer.parseInt(splitTime[1]),
        };
    }

    /**
     * Checks if the given {@code String deadline} is in the correct format of
     * {@code YYYY-MM-DDTHH:mm}, Where Y = year, M = month, D = days, H = hour as in 24hour,
     * m = minute. All these should be digits.
     * T should remain as a constant and should not be changed or replaced.
     *
     * The dashes (-), spaces and colon should also be in the correct locations.
     *
     * @param deadline the given deadline to check
     * @return true if the deadline is in the correct format.
     */
    private static boolean isCorrectFormat(String deadline) {
        requireNonNull(deadline);
        return deadline.matches(VALIDATION_REGEX);
    }

    public String toStringNiceFormat() {
        return String.format("by: %s, %s", deadline.toLocalDate(), deadline.toLocalTime());
    }


    @Override
    public String toString() {
        return deadline.toString();
    }

    @Override
    public int compareTo(Deadline other) {
        return deadline.compareTo(other.deadline);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && deadline.equals(((Deadline) other).deadline)); // state check
    }

    @Override
    public int hashCode() {
        return deadline.hashCode();
    }

}
