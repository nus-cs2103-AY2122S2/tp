package seedu.contax.model.appointment;

import seedu.contax.model.chrono.ScheduleItem;
import seedu.contax.model.chrono.TimeRange;

/**
 * Represents an empty slot in the Schedule. Time related functionality is handled in the superclass
 * {@link ScheduleItem}.
 */
public class AppointmentSlot extends ScheduleItem {

    /**
     * Constructs an {@code AppointmentSlot} object.
     *
     * @param slotRange The time period of the AppointmentSlot.
     */
    public AppointmentSlot(TimeRange slotRange) {
        super(slotRange);
    }

    @Override
    public String toString() {
        return "Start Date Time: "
                + getStartDateTime()
                + "; End Date Time: "
                + getEndDateTime();
    }

    /**
     * Returns true if {@code other} is an {@code AppointmentSlot} and spans the same {@code TimeRange}.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AppointmentSlot)) {
            return false;
        }

        return super.equals(other);
    }
}
