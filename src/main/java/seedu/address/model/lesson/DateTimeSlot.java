package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;

/**
 * Represents the time in which a lesson takes place in the LessonBook.
 */
public class DateTimeSlot {
    private static final String MESSAGE_CONSTRAINTS =
            "Lessons can only be created with a valid date.\nHours and minutes must be non-negative integers.";
    private static final DateTimeFormatter acceptedDateFormat = DateTimeFormatter.ofPattern("d-M-y");
    private static final DateTimeFormatter acceptedStartTimeFormat = DateTimeFormatter.ofPattern("HH:mm");

    private static final DateTimeFormatter displayedDateFormat = DateTimeFormatter.ofPattern("EEEE '['d MMMM yyyy']'");
    private static final DateTimeFormatter displayedTimeFormat = DateTimeFormatter.ofPattern("h:mm a");

    private final LocalDateTime dateOfLesson;
    private final int hours;
    private final int minutes;

    public static DateTimeFormatter getAcceptedDateFormat() {
        return acceptedDateFormat;
    }

    /**
     * Constructs a {@code DateTimeSlot}.
     *
     * @param date Date of the lesson.
     * @param hours Duration of the lesson.
     */
    public DateTimeSlot(LocalDateTime date, int hours) {
        this(date, hours, 0);
    }

    /**
     * Constructs a {@code DateTimeSlot}.
     *
     * @param date Date of the lesson.
     * @param hours Duration of the lesson, hours.
     * @param minutes Duration of the lesson, minutes.
     */
    public DateTimeSlot(LocalDateTime date, int hours, int minutes) {
        requireNonNull(date);
        checkArgument((hours > 0 && minutes >= 0) || (hours == 0 && minutes > 0),
                MESSAGE_CONSTRAINTS);

        dateOfLesson = date;
        this.hours = hours;
        this.minutes = minutes;
    }

    /**
     * Returns true if both timeslots overlap.
     */
    protected boolean isConflictingWith(DateTimeSlot otherDateTimeSlot) {
        LocalDateTime currentTimeSlotStart = this.dateOfLesson;
        LocalDateTime currentTimeSlotEnd = this.getEndingDateTime();
        LocalDateTime otherTimeSlotStart = otherDateTimeSlot.dateOfLesson;
        LocalDateTime otherTimeSlotEnd = otherDateTimeSlot.getEndingDateTime();

        return (currentTimeSlotStart.isBefore(otherTimeSlotEnd) || currentTimeSlotStart.equals(otherTimeSlotEnd))
                && (currentTimeSlotEnd.isAfter(otherTimeSlotStart) || currentTimeSlotEnd.equals(otherTimeSlotStart));
    }

    /**
     * Get the date and time at which the lesson ends.
     */
    private LocalDateTime getEndingDateTime() {
        return dateOfLesson.plusHours(hours).plusMinutes(minutes);
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

    /**
     * Returns true if specified string is an accepted date format
     */
    public static boolean isValidDate(String dateString) {
        try {
            acceptedDateFormat.parse(dateString);
        } catch (DateTimeParseException exception) {
            return false;
        }

        return true;
    }

    /**
     * Returns true if specified string is an accepted starting time format
     */
    public static boolean isValidStartTime(String startTimeString) {
        try {
            acceptedStartTimeFormat.parse(startTimeString);
        } catch (DateTimeParseException exception) {
            return false;
        }

        return true;
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

        if (!(other instanceof DateTimeSlot)) {
            return false;
        }

        DateTimeSlot otherDateTimeSlot = (DateTimeSlot) other;
        return this.dateOfLesson.equals(otherDateTimeSlot.dateOfLesson)
                && this.hours == (otherDateTimeSlot.hours);
    }

    @Override
    public int hashCode() {
        return dateOfLesson.hashCode();
    }
}
