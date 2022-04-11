package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Date;
import seedu.address.model.Time;
import seedu.address.model.tasks.Header;
import seedu.address.model.tasks.Information;
import seedu.address.model.tasks.Task;


/**
 * JSON-friendly version of {@link Task}
 */
public class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String header;
    private final String date;
    private final String time;
    private final String information;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("header") String header, @JsonProperty("date") String date,
                           @JsonProperty("time") String time, @JsonProperty("information") String information) {
        this.header = header;
        this.date = date;
        this.time = time;
        this.information = information;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        header = source.getHeader().fullHeader;
        date = source.getDate().value;
        time = source.getTime().value;
        information = source.getInformation().fullInformation;
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {

        if (header == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Header.class.getSimpleName()));
        }

        if (!Header.isValidHeader(header)) {
            throw new IllegalValueException(Information.MESSAGE_CONSTRAINTS);
        }
        final Header modelHeader = new Header(header);

        if (information == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Information.class.getSimpleName()));
        }

        if (!Information.isValidInformation(information)) {
            throw new IllegalValueException(Information.MESSAGE_CONSTRAINTS);
        }
        final Information modelInformation = new Information(information);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        if (time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName()));
        }
        if (!Time.isValidTime(time)) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }
        final Time modelTime = new Time(time);

        return new Task(modelHeader, modelDate, modelTime, modelInformation);
    }
}
