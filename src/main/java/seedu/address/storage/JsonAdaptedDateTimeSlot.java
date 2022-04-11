package seedu.address.storage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.lesson.DateTimeSlot;

/**
 * Jackson-friendly version of {@link DateTimeSlot}.
 */
class JsonAdaptedDateTimeSlot {

    public static final String MISSING_FIELD_MESSAGE = "DateTimeSlot has some missing/invalid fields!";

    private final String dateOfLesson;
    private final String startTime;
    private final String durationHours;
    private final String durationMinutes;

    /**
     * Constructs a {@code JsonAdaptedDateTimeSlot} with the given {@code dateTimeSlot}.
     */
    @JsonCreator
    public JsonAdaptedDateTimeSlot(@JsonProperty("dateOfLesson") String dateOfLesson,
                                   @JsonProperty("startingTime") String startingTime,
                                   @JsonProperty("durationHours") String durationHours,
                                   @JsonProperty("durationMinutes") String durationMinutes) {
        this.dateOfLesson = dateOfLesson;
        this.startTime = startingTime;
        this.durationHours = durationHours;
        this.durationMinutes = durationMinutes;
    }

    /**
     * Converts a given {@code DateTimeSlot} into this class for Jackson use.
     */
    public JsonAdaptedDateTimeSlot(DateTimeSlot source) {
        this.dateOfLesson = source.getJsonDate();
        this.startTime = source.getJsonStartTime();
        this.durationHours = source.getJsonDurationHours();
        this.durationMinutes = source.getJsonDurationMinutes();
    }

    /**
     * Converts this Jackson-friendly adapted date-time slot object into the model's {@code DateTimeSlot} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted date timeslot.
     */
    public DateTimeSlot toModelType() throws IllegalValueException {
        checkFieldsArePresent(dateOfLesson, startTime, durationHours, durationMinutes);
        checkDateIsValid(dateOfLesson);
        checkStartTimeIsValid(startTime);
        checkDurationHoursIsValid(durationHours);
        checkDurationMinutesIsValid(durationMinutes);

        LocalDate modelDateOfLesson = DateTimeSlot.parseLessonDate(dateOfLesson);
        LocalTime modelStartTime = ParserUtil.parseStartTime(startTime);
        LocalDateTime modelStartingDateTime = modelDateOfLesson.atTime(modelStartTime);
        Integer modelDurationHours = DateTimeSlot.parseLessonDurationHours(durationHours);
        Integer modelDurationMinutes = DateTimeSlot.parseLessonDurationMinutes(durationMinutes);

        return getDateTimeSlot(modelStartingDateTime, modelDurationHours, modelDurationMinutes);
    }

    private static void checkFieldsArePresent(Object... toCheck) throws IllegalValueException {
        for (Object o : toCheck) {
            if (o == null) {
                throw new IllegalValueException(MISSING_FIELD_MESSAGE);
            }
        }
    }

    private static void checkDateIsValid(String date) throws IllegalValueException {
        if (!DateTimeSlot.isValidDate(date)) {
            throw new IllegalValueException(DateTimeSlot.MESSAGE_CONSTRAINTS);
        }
    }

    private static void checkStartTimeIsValid(String startTime) throws IllegalValueException {
        if (!DateTimeSlot.isValidStartTime(startTime)) {
            throw new IllegalValueException(DateTimeSlot.MESSAGE_CONSTRAINTS);
        }
    }

    private static void checkDurationHoursIsValid(String durationHours) throws IllegalValueException {
        if (!DateTimeSlot.isValidDurationHours(durationHours)) {
            throw new IllegalValueException(DateTimeSlot.MESSAGE_CONSTRAINTS);
        }
    }

    private static void checkDurationMinutesIsValid(String durationMinutes) throws IllegalValueException {
        if (!DateTimeSlot.isValidDurationMinutes(durationMinutes)) {
            throw new IllegalValueException(DateTimeSlot.MESSAGE_CONSTRAINTS);
        }
    }

    private static DateTimeSlot getDateTimeSlot(LocalDateTime modelDateTime,
                                                Integer modelDurationHours, Integer modelDurationMinutes)
            throws IllegalValueException {
        return DateTimeSlot.makeDateTimeSlotFromJson(modelDateTime, modelDurationHours, modelDurationMinutes);
    }
}
