package seedu.contax.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.contax.model.ScheduleItem;
import seedu.contax.model.person.Person;
import seedu.contax.model.util.TemporalComparable;
import seedu.contax.model.util.TimeRange;

/**
 * Represents an empty slot in the schedule.
 */
public class AppointmentSlot extends ScheduleItem implements TemporalComparable {

    private final TimeRange slotRange;

    public AppointmentSlot(TimeRange slotRange) {
        this.slotRange = slotRange;
    }

    public LocalDateTime getStartDateTime() {
        return slotRange.getStartDateTime();
    }

    public LocalDateTime getEndDateTime() {
        return slotRange.getEndDateTime();
    }

    /**
     * Returns true if both appointments have the same name and data fields.
     * This defines a stronger notion of equality between two appointments.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AppointmentSlot)) {
            return false;
        }

        AppointmentSlot otherPerson = (AppointmentSlot) other;
        return otherPerson.slotRange.equals(this.slotRange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slotRange);
    }

    @Override
    public String toString() {
        return "Start Date Time: "
                + getStartDateTime()
                + "; End Date Time: "
                + getEndDateTime();
    }

    @Override
    public LocalDateTime getComparableDateTime() {
        return this.getStartDateTime();
    }

    @Override
    public int compareTo(TemporalComparable other) {
        return this.getStartDateTime().compareTo(other.getComparableDateTime());
    }
}
