package manageezpz.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import manageezpz.commons.exceptions.IllegalValueException;
import manageezpz.model.task.Deadline;
import manageezpz.model.task.Description;
import manageezpz.model.task.Event;
import manageezpz.model.task.Task;
import manageezpz.model.task.Todo;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String description;
    private final String type;
    private final String date;
    private final String time;
    private final String startTime;
    private final String endTime;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("type") String type, @JsonProperty("description") String description,
    @JsonProperty("date") String date, @JsonProperty("time") String time,
                           @JsonProperty("startTime") String startTime, @JsonProperty("endTime") String endTime) {
        this.description = new Description(description).toString();
        this.type = type;
        this.date = date;
        this.time = time;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    // Note to self: Do I need to convert String to LocalTime, so that toModelType can be able to create the task?
    public JsonAdaptedTask(Task source) {
        // For future reference, this.marked = source.getMark()?
        description = source.getDescription().toString(); // Generally for all tasks
        type = source.getType(); // Generally for all tasks
        this.date = "Placeholder for source.getDate()"; // For Deadline
        this.time = "Placeholder for source.getTime()"; // For Deadline
        this.startTime = "Placeholder for source.getEndTime()"; // For Event
        this.endTime = "Placeholder for source.getEndTime()"; // For Event
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Task toModelType() throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Task.class.getSimpleName()));
        }
        Description desc = new Description(description);
        if(type.equals("todo")) {
            return new Todo(desc);
        } else if(type.equals("deadline")) {
 //           return new Deadline(desc, datetime,datetime);
        } else if(type.equals("event")) {
//                return new Event(desc,startTime, endTime);
        }
        return new Todo(desc);
    }
}
