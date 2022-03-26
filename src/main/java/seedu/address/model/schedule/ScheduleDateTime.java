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

    private final LocalDateTime scheduleDateTime;

    /**
     * Creates a date time for schedule.
     */
    public ScheduleDateTime(String scheduleDateTime) {
        requireNonNull(scheduleDateTime);
        checkArgument(isValidScheduleDateTime(scheduleDateTime), MESSAGE_CONSTRAINTS);
        this.scheduleDateTime = LocalDateTime.parse(scheduleDateTime);
    }

    public LocalDateTime getScheduleDateTime() {
        return scheduleDateTime;
    }

    public static boolean isValidScheduleDateTime(String scheduleDateTime) {
        try {
            FORMATTER.parse(scheduleDateTime);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return scheduleDateTime.format(FORMATTER);
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
