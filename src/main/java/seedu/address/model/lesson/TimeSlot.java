package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Calendar;
import java.util.Date;

/**
 * Represents the time in which a lesson takes place in the LessonBook.
 */
public class TimeSlot {
    private static final String MESSAGE_CONSTRAINTS =
            "Lessons can only be created when there are no clashes in the timetable";

    private final Date dateOfLesson;
    private final int duration;

    /**
     * Constructs a {@code TimeSlot}.
     *
     * @param date Date of the lesson.
     * @param hours Duration of the lesson.
     */
    public TimeSlot(Date date, int hours) {
        requireNonNull(date);
        checkArgument(isDateAvailable(date), MESSAGE_CONSTRAINTS);
        dateOfLesson = date;
        duration = hours;
    }

    /**
     * Returns true if no timeslots occupied by lessons in the timetable clashes with this one.
     * TODO: replace this with actual logic to check for timetable clashes
     */
    private static boolean isDateAvailable(Date date) {
        return true;
    }

    /**
     * Returns true if both timeslots overlap.
     *
     * @param otherTimeSlot
     * @return
     */
    protected boolean isClashingWith(TimeSlot otherTimeSlot) {
        Date currentTimeSlotStart = this.dateOfLesson;
        Date currentTimeSlotEnd = this.getEndDate();
        Date otherTimeSlotStart = otherTimeSlot.dateOfLesson;
        Date otherTimeSlotEnd = otherTimeSlot.getEndDate();

        return (currentTimeSlotStart.before(otherTimeSlotEnd) || currentTimeSlotStart.equals(otherTimeSlotEnd))
                && (currentTimeSlotEnd.after(otherTimeSlotStart) || currentTimeSlotEnd.equals(otherTimeSlotStart));
    }

    private Date getEndDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateOfLesson);
        cal.add(Calendar.HOUR_OF_DAY, duration);
        return cal.getTime();
    }

    @Override
    public String toString() {
        return dateOfLesson.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TimeSlot)) {
            return false;
        }

        TimeSlot otherTimeSlot = (TimeSlot) other;
        return this.dateOfLesson.equals(otherTimeSlot.dateOfLesson);
    }

    @Override
    public int hashCode() {
        return dateOfLesson.hashCode();
    }
}
