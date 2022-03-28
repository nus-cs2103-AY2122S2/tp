package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.format.DateTimeFormatter;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleDateTime;
import seedu.address.model.schedule.ScheduleDescription;
import seedu.address.model.schedule.ScheduleName;

public class JsonAdaptedSchedule {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Schedule's %s field is missing!";

    private final String name;
    private final String description;
    private final String dateTime;

    /**
     * Constructs a {@code JsonAdaptedSchedule} with the given schedule details.
     */
    @JsonCreator
    public JsonAdaptedSchedule(@JsonProperty("name") String name,
                               @JsonProperty("description") String description,
                               @JsonProperty("dateTime") String dateTime) {
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Converts a given {@code Schedule} into this class for Jackson use.
     */
    public JsonAdaptedSchedule(Schedule source) {
        this.name = source.getScheduleName().scheduleName;
        this.description = source.getScheduleDescription().description;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hhmm");
        this.dateTime = source.getScheduleDateTime().getScheduleDateTime().format(formatter);
    }

    /**
     * Converts this Jackson-friendly adapted schedule object into the model's {@code Schedule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted schedule.
     */
    public Schedule toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ScheduleName.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(ScheduleName.MESSAGE_CONSTRAINTS);
        }
        final ScheduleName modelName = new ScheduleName(name);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ScheduleDescription.class.getSimpleName()));
        }
        if (!ScheduleDescription.isValidScheduleDescription(description)) {
            throw new IllegalValueException(ScheduleDescription.MESSAGE_CONSTRAINTS);
        }
        final ScheduleDescription modelScheduleDescription = new ScheduleDescription(description);

        if (dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ScheduleDateTime.class.getSimpleName()));
        }
        if (!ScheduleDateTime.isValidScheduleDateTime(dateTime)) {
            throw new IllegalValueException(ScheduleDateTime.MESSAGE_CONSTRAINTS);
        }
        final ScheduleDateTime modelScheduleDateTime = new ScheduleDateTime(dateTime);

        return new Schedule(modelName, modelScheduleDescription, modelScheduleDateTime);
    }
}
