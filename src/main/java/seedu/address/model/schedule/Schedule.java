package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.person.Name;

/**
 * Represents a schedule in MyGM
 */
public class Schedule {
    private final ScheduleName scheduleName;
    private final ScheduleDescription scheduleDescription;
    private final ScheduleDateTime scheduleDateTime;

    /**
     * Constructs a schedule.
     */
    public Schedule(ScheduleName scheduleName, ScheduleDescription scheduleDescription,
                    ScheduleDateTime scheduleDateTime) {
        requireAllNonNull(scheduleName, scheduleDescription, scheduleDateTime);
        this.scheduleName = scheduleName;
        this.scheduleDescription = scheduleDescription;
        this.scheduleDateTime = scheduleDateTime;
    }

    public ScheduleName getScheduleName() {
        return scheduleName;
    }

    public ScheduleDescription getScheduleDescription() {
        return scheduleDescription;
    }

    public ScheduleDateTime getScheduleDateTime() {
        return scheduleDateTime;
    }

    /**
     * Returns true if some schedule's name is {@code targetName}.
     */
    public boolean isMatchName(ScheduleName targetName) {
        requireNonNull(targetName);
        return getScheduleName().equals(targetName);
    }

    /**
     * Returns true if both schedules have the same description and date/time.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Schedule)) {
            return false;
        }

        Schedule otherSchedule = (Schedule) other;
        return otherSchedule.getScheduleName().equals(getScheduleName())
                && otherSchedule.getScheduleDescription().equals(getScheduleDescription())
                && otherSchedule.getScheduleDateTime().equals(getScheduleDateTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(scheduleName, scheduleDescription, scheduleDateTime);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getScheduleName())
                .append("\nDescription: ")
                .append(getScheduleDescription())
                .append("\nDate and Time: ")
                .append(getScheduleDateTime());
        return builder.toString();
    }
}
