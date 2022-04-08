package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents the date and time of a schedule
 */
public class ScheduleDateTime {
    public static final String MESSAGE_CONSTRAINTS =
            "Please check the format of schedule date and time, and it should not be blank";
    public static final String MESSAGE_DATE_CONSTRAINTS =
            "The format of date should be dd/MM/uuuu";

    private static final String DATE_TIME_FORMATTER = "[dd/MM/uuuu HHmm]";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter
            .ofPattern(DATE_TIME_FORMATTER)
            .withResolverStyle(ResolverStyle.STRICT);
    private final LocalDateTime scheduleDateTime;

    /**
     * Creates a date time for schedule.
     */
    public ScheduleDateTime(String scheduleDateTime) {
        requireNonNull(scheduleDateTime);
        System.out.println(scheduleDateTime);
        checkArgument(isValidScheduleDateTime(scheduleDateTime), MESSAGE_CONSTRAINTS);
        this.scheduleDateTime = LocalDateTime.parse(scheduleDateTime, FORMATTER);
    }

    public LocalDateTime getScheduleDateTime() {
        return scheduleDateTime;
    }

    /**
     * Checks if a string is valid date time.
     */
    public static boolean isValidScheduleDateTime(String test) {
        try {
            LocalDateTime.parse(test, FORMATTER);
            System.out.println("valid");
            return true;
        } catch (DateTimeParseException pe) {
            System.out.println("Invalid");
            return false;
        }
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd MMM yyyy, hh:mma]");
        return scheduleDateTime.format(formatter);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScheduleDateTime // instanceof handles nulls
                && scheduleDateTime.equals(((ScheduleDateTime) other).scheduleDateTime)); // state check
    }

    @Override
    public int hashCode() {
        return scheduleDateTime.hashCode();
    }
}
