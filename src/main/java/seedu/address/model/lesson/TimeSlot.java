package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Represents the time in which a lesson takes place in the LessonBook.
 */
public class TimeSlot {
    private static final String MESSAGE_CONSTRAINTS =
            "Lessons can only be created when there are no clashes in the timetable";
    private static final DateFormat displayedDateFormat = new SimpleDateFormat("EEEE [d MMMM yyyy]");
    private static final DateFormat displayedTimeFormat = new SimpleDateFormat("h:mm a");

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
     */
    protected boolean isConflictingWith(TimeSlot otherTimeSlot) {
        Date currentTimeSlotStart = this.dateOfLesson;
        Date currentTimeSlotEnd = this.getEndingDateTime();
        Date otherTimeSlotStart = otherTimeSlot.dateOfLesson;
        Date otherTimeSlotEnd = otherTimeSlot.getEndingDateTime();

        return (currentTimeSlotStart.before(otherTimeSlotEnd) || currentTimeSlotStart.equals(otherTimeSlotEnd))
                && (currentTimeSlotEnd.after(otherTimeSlotStart) || currentTimeSlotEnd.equals(otherTimeSlotStart));
    }

    /**
     * Get the date and time at which the lesson ends.
     */
    private Date getEndingDateTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateOfLesson);
        cal.add(Calendar.HOUR_OF_DAY, duration);
        return cal.getTime();
    }

    /**
     * Returns a string representation of the date on which the lesson would take place.
     */
    public String getDateString() {
        return displayedDateFormat.format(this.dateOfLesson);
    }

    /**
     * Returns a string representation of the starting and ending time of the lesson.
     */
    public String getTimeString() {
        return String.format("%s - %s",
                displayedTimeFormat.format(this.dateOfLesson),
                displayedTimeFormat.format(this.getEndingDateTime()));
    }

    @Override
    public String toString() {
        return String.format("%s [%s]", this.getDateString(), this.getTimeString());
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
        return this.dateOfLesson.equals(otherTimeSlot.dateOfLesson)
                && this.duration == (otherTimeSlot.duration);
    }

    @Override
    public int hashCode() {
        return dateOfLesson.hashCode();
    }
}
