package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the time in which a lesson takes place in the LessonBook.
 */
public class DateTimeSlot {
    public static final String MESSAGE_CONSTRAINTS = "Lessons can only be created with a valid date."
                    + "\n Hours and minutes must be non-negative integer."
                    + "\n Minutes cannot be more than 60.";

    private static final DateTimeFormatter acceptedDateFormat = DateTimeFormatter.ofPattern("d-M-y");
    private static final DateTimeFormatter acceptedStartTimeFormat = DateTimeFormatter.ofPattern("HH:mm");

    private static final DateTimeFormatter displayedDateFormat = DateTimeFormatter.ofPattern("EEEE '['d MMMM yyyy']'");
    private static final DateTimeFormatter displayedTimeFormat = DateTimeFormatter.ofPattern("h:mm a");

    private final LocalDateTime dateOfLesson;
    private final int hours;
    private final int minutes;

    /**
     * Constructs a {@code DateTimeSlot}.
     *
     * @param date Date and starting time of the lesson.
     * @param hours Duration of the lesson, hours.
     * @param minutes Duration of the lesson, minutes.
     */
    public DateTimeSlot(LocalDateTime date, int hours, int minutes) {
        requireNonNull(date);

        dateOfLesson = date;
        this.hours = hours;
        this.minutes = minutes;
    }

    /**
     * Constructs a {@code DateTimeSlot}.
     *
     * @param date Date and starting time of the lesson.
     * @param hours Duration of the lesson.
     */
    public DateTimeSlot(LocalDateTime date, int hours) {
        this(date, hours, 0);
    }

    /**
     * Constructs a {@code DateTimeSlot}.
     *
     * @param date Date of the lesson.
     * @param startTime Starting time of the lesson.
     * @param hours Duration of the lesson, hours.
     * @param minutes Duration of the lesson, minutes.
     *
     * TODO: REMOVE THIS CONSTRUCTOR
     */
    public DateTimeSlot(LocalDate date, String startTime, int hours, int minutes) {
        requireNonNull(date);
        checkArgument((hours > 0 && minutes >= 0 && minutes <= 60)
                        || (hours == 0 && minutes > 0 && minutes <= 60),
                MESSAGE_CONSTRAINTS);

        String[] hourAndMinuteOfStartTime = startTime.split(":");
        Integer hour;
        Integer minute;
        LocalDateTime lessonDateTime;

        hour = Integer.parseInt(hourAndMinuteOfStartTime[0]);
        minute = Integer.parseInt(hourAndMinuteOfStartTime[1]);
        lessonDateTime = date.atTime(hour, minute);

        dateOfLesson = lessonDateTime;
        this.hours = hours;
        this.minutes = minutes;
    }

    public static DateTimeFormatter getAcceptedDateFormat() {
        return acceptedDateFormat;
    }

    /**
     * Returns true if both timeslots overlap.
     */
    protected boolean isConflictingWith(DateTimeSlot otherDateTimeSlot) {
        LocalDateTime currentTimeSlotStart = this.dateOfLesson;
        LocalDateTime currentTimeSlotEnd = this.getEndingDateTime();
        LocalDateTime otherTimeSlotStart = otherDateTimeSlot.dateOfLesson;
        LocalDateTime otherTimeSlotEnd = otherDateTimeSlot.getEndingDateTime();

        return (currentTimeSlotStart.isBefore(otherTimeSlotEnd) && currentTimeSlotEnd.isAfter(otherTimeSlotStart));
    }

    public LocalDateTime getDateOfLesson() {
        return dateOfLesson;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
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

    /**
     * Returns true if hours field of duration is valid
     */
    public static boolean isValidDurationHours(String durationHoursString) {
        Integer durationHours;

        try {
            durationHours = Integer.parseInt(durationHoursString);
        } catch (NumberFormatException exception) {
            return false;
        }

        if (durationHours < 0) {
            return false;
        }

        return true;
    }

    /**
     * Returns true if minutes field of duration is valid
     */
    public static boolean isValidDurationMinutes(String durationMinutesString) {
        Integer durationMinutes;

        try {
            durationMinutes = Integer.parseInt(durationMinutesString);
        } catch (NumberFormatException exception) {
            return false;
        }

        if (durationMinutes < 0) {
            return false;
        }

        return true;
    }

    /**
     * Parses a date String into a LocalDate.
     */
    public static LocalDate parseLessonDate(String lessonDateString) throws DateTimeParseException {
        return LocalDate.parse(lessonDateString, acceptedDateFormat);
    }

    /**
     * Parses a duration hour String into an Integer
     */
    public static Integer parseLessonDurationHours(String lessonDurationHoursString) throws NumberFormatException {
        return Integer.parseInt(lessonDurationHoursString);
    }

    /**
     * Parses a duration minute String into an Integer
     */
    public static Integer parseLessonDurationMinutes(String lessonDurationMinutesString) throws NumberFormatException {
        return Integer.parseInt(lessonDurationMinutesString);
    }

    /**
     * Returns JSON-serializable version of the Date field
     */
    public String getJsonDate() {
        return acceptedDateFormat.format(this.dateOfLesson);
    }

    /**
     * Returns JSON-serializable version of the starting time field
     */
    public String getJsonStartTime() {
        return acceptedStartTimeFormat.format(this.dateOfLesson);
    }

    /**
     * Returns JSON-serializable version of the duration hours field
     */
    public String getJsonDurationHours() {
        return Integer.toString(this.hours);
    }

    /**
     * Returns JSON-serializable version of the duration minutes field
     */
    public String getJsonDurationMinutes() {
        return Integer.toString(this.minutes);
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
