package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleDateTime;
import seedu.address.model.schedule.ScheduleDescription;
import seedu.address.model.schedule.ScheduleName;

/**
 * Jackson-friendly version of {@link Schedule}.
 */
public class JsonAdaptedSchedule {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Schedule's %s field is missing!";

    private final String scheduleName;
    private final String scheduleDescription;
    private final String scheduleDateTime;

    /**
     * Constructs a {@code JsonAdaptedSchedule} with the given schedule details.
     */
    @JsonCreator
    public JsonAdaptedSchedule(@JsonProperty("scheduleName") String scheduleName,
                               @JsonProperty("scheduleDescription") String scheduleDescription,
                               @JsonProperty("scheduleDateTime") String scheduleDateTime) {
        this.scheduleName = scheduleName;
        this.scheduleDescription = scheduleDescription;
        this.scheduleDateTime = scheduleDateTime;
    }

    /**
     * Converts a given {@code Schedule} into this class for Jackson use.
     */
    public JsonAdaptedSchedule(Schedule source) {
        this.scheduleName = source.getScheduleName().getScheduleName();
        this.scheduleDescription = source.getScheduleDescription().description;
        this.scheduleDateTime = source.getScheduleDateTime().getScheduleDateTime().format(ScheduleDateTime.FORMATTER);
    }

    /**
     * Converts this Jackson-friendly adapted lineup object into the model's {@code Lineup} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Schedule toModelType() throws IllegalValueException {

        if (scheduleName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ScheduleName.class.getSimpleName()));
        }
        if (scheduleDescription == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ScheduleDescription.class.getSimpleName()));
        }
        if (scheduleDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ScheduleDateTime.class.getSimpleName()));
        }

        final ScheduleName modelScheduleName = new ScheduleName(scheduleName);
        final ScheduleDescription modelScheduleDescription = new ScheduleDescription(scheduleDescription);
        final ScheduleDateTime modelScheduleDateTime = new ScheduleDateTime(scheduleDateTime);

        return new Schedule(modelScheduleName, modelScheduleDescription, modelScheduleDateTime);
    }
}
