package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the date and time of a schedule
 */
public class ScheduleDateTime {
    public static final String MESSAGE_CONSTRAINTS =
            "Please check the format of schedule date and time, and it should not be blank";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // solution below adapted from
    // https://stackoverflow.com/questions/
    // 15491894/regex-to-validate-date-formats-dd-mm-yyyy-dd-mm-yyyy-dd-mm-yyyy-dd-mmm-yyyy
    public static final String VALIDATION_REGEX = "^(?:(?:31(\\/)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/)"
            + "(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})"
            + "$|^(?:29(\\/)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|"
            + "(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/)(?:(?:0?[1-9])"
            + "|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2}) ([01]?[0-9]|2[0-3])[0-5][0-9]$";
    private static final String DATE_TIME_FORMATTER = "[dd/MM/yyyy HHmm]";
    private final LocalDateTime scheduleDateTime;

    /**
     * Creates a date time for schedule.
     */
    public ScheduleDateTime(String scheduleDateTime) {
        requireNonNull(scheduleDateTime);
        checkArgument(isValidScheduleDateTime(scheduleDateTime), MESSAGE_CONSTRAINTS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER);
        this.scheduleDateTime = LocalDateTime.parse(scheduleDateTime, formatter);
    }

    public LocalDateTime getScheduleDateTime() {
        return scheduleDateTime;
    }


    public static boolean isValidScheduleDateTime(String test) {
        return test.matches(VALIDATION_REGEX);
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
