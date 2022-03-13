package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lesson.DateTimeSlot;

/**
 * Jackson-friendly version of {@link DateTimeSlot}.
 */
class JsonAdaptedDateTimeSlot {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "DateTimeSlot's %s field is missing!";

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
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public DateTimeSlot toModelType() throws IllegalValueException {
        // TODO: make specific error-messages here for each null or incorrect format field

        if (dateOfLesson == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, DateTimeSlot.class.getSimpleName())
            );
        }
        if (!DateTimeSlot.isValidDate(dateOfLesson)) {
            throw new IllegalValueException(DateTimeSlot.MESSAGE_CONSTRAINTS);
        }
        LocalDate modelDateOfLesson = DateTimeSlot.parseLessonDate(dateOfLesson);

        if (startTime == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, DateTimeSlot.class.getSimpleName())
            );
        }
        if (!DateTimeSlot.isValidStartTime(startTime)) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, DateTimeSlot.class.getSimpleName())
            );
        }
        String modelStartTime = startTime;

        if (durationHours == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, DateTimeSlot.class.getSimpleName())
            );
        }
        if (!DateTimeSlot.isValidDurationHours(durationHours)) {
            throw new IllegalValueException(DateTimeSlot.MESSAGE_CONSTRAINTS);
        }
        Integer modelDurationHours = DateTimeSlot.parseLessonDurationHours(durationHours);

        if (durationMinutes == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, DateTimeSlot.class.getSimpleName())
            );
        }
        if (!DateTimeSlot.isValidDurationMinutes(durationMinutes)) {
            throw new IllegalValueException(DateTimeSlot.MESSAGE_CONSTRAINTS);
        }
        Integer modelDurationMinutes = DateTimeSlot.parseLessonDurationMinutes(durationMinutes);

        return new DateTimeSlot(modelDateOfLesson, modelStartTime, modelDurationHours, modelDurationMinutes);
    }
}
