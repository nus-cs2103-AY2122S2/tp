package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.format.TextStyle;
import java.util.Locale;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Represents the time in which a lesson takes place in the LessonBook.
 */
public class DateTimeSlot {
    public static final String MESSAGE_CONSTRAINTS = "Lessons can only be created with a valid date."
                    + "\n Hours and minutes must be non-negative integer."
                    + "\n Minutes cannot be more than 60.";

    public static final String INVALID_DURATION_MESSAGE = "Duration of lesson cannot be zero.";

    public static final String EXCESSIVE_DURATION_MESSAGE =
            "Duration of lesson cannot be more than or equals to 24 hours!";

    private static final DateTimeFormatter acceptedDateFormat =
            DateTimeFormatter.ofPattern("d-M-uuuu").withResolverStyle(ResolverStyle.STRICT);
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
     * @param startingDateTime Starting date and time of the lesson.
     * @param hours Duration of the lesson, hours.
     * @param minutes Duration of the lesson, minutes.
     *
     * @throws CommandException if the lesson takes place over more than one day (i.e. starts on Monday, ends on Friday)
     */
    public static DateTimeSlot makeDateTimeSlot(LocalDateTime startingDateTime, Integer hours, Integer minutes)
            throws CommandException {
        checkLessonDurationIsGreaterThanZeroMinutes(hours, minutes);
        checkLessonDurationIsLessThan24Hours(hours, minutes);

        return new DateTimeSlot(startingDateTime, hours, minutes);
    }

    private static void checkLessonDurationIsGreaterThanZeroMinutes(Integer hours, Integer minutes)
            throws CommandException {
        if (hours == 0 && minutes == 0) {
            throw new CommandException(INVALID_DURATION_MESSAGE);
        }
    }

    private static void checkLessonDurationIsLessThan24Hours(Integer hours, Integer minutes)
            throws CommandException {
        Integer totalDurationInMinutes = (hours * 60) + minutes;
        Integer twentyFourHoursInMinutes = 24 * 60;

        if (totalDurationInMinutes >= twentyFourHoursInMinutes) {
            throw new CommandException(EXCESSIVE_DURATION_MESSAGE);
        }
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
     * Returns a string representation of which day of the week the lesson falls on.
     * @return
     */
    public String getDayString() {
        return String.format("every %s",
                dateOfLesson.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()));
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

        if (durationMinutes < 0 || durationMinutes >= 60) {
            return false;
        }

        return true;
    }

    /**
     * Returns an instance of DateTimeSlot from information stored in JSON representation.
     *
     * @throws IllegalValueException
     */
    public static DateTimeSlot makeDateTimeSlotFromJson(LocalDateTime lessonDateTime, Integer hours, Integer minutes)
            throws IllegalValueException {
        DateTimeSlot dateTimeSlot;

        try {
            dateTimeSlot = makeDateTimeSlot(lessonDateTime, hours, minutes);
        } catch (CommandException e) {
            throw new IllegalValueException(e.getMessage());
        }

        return dateTimeSlot;
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
                && this.hours == (otherDateTimeSlot.hours)
                && this.minutes == otherDateTimeSlot.minutes;
    }

    @Override
    public int hashCode() {
        return dateOfLesson.hashCode();
    }
}
