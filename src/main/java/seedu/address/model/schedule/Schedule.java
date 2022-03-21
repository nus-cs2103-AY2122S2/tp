package seedu.address.model.schedule;

import java.util.Objects;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Schedule {
    private final ScheduleName scheduleName;
    private final ScheduleDateTime scheduleDateTime;

    public Schedule(ScheduleName scheduleName, ScheduleDateTime scheduleDateTime) {
        requireAllNonNull(scheduleName, scheduleDateTime);
        this.scheduleName = scheduleName;
        this.scheduleDateTime = scheduleDateTime;
    }

    public ScheduleName getScheduleName() {
        return scheduleName;
    }

    public ScheduleDateTime getScheduleDateTime() {
        return scheduleDateTime;
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
                && otherSchedule.getScheduleDateTime().equals(getScheduleDateTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(scheduleName, scheduleDateTime);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getScheduleName())
                .append("\nDate and Time: ")
                .append(getScheduleDateTime());
        return builder.toString();
    }
}
