package manageezpz.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import manageezpz.commons.exceptions.IllegalValueException;
import manageezpz.model.task.Date;
import manageezpz.model.task.Deadline;
import manageezpz.model.task.Description;
import manageezpz.model.task.Event;
import manageezpz.model.task.Task;
import manageezpz.model.task.Time;
import manageezpz.model.task.Todo;



/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String description;
    private final String type;
    private final String date;
    private String deadlineTime;
    private String eventStartTime;
    private String eventEndTime;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("type") String type, @JsonProperty("description") String description,
                           @JsonProperty("date") String date, @JsonProperty("deadlineTime") String deadlineTime,
                           @JsonProperty("eventStartTime") String eventStartTime,
                           @JsonProperty("eventEndTime") String eventEndTime) {
        this.description = new Description(description).toString();
        this.type = type;
        this.date = date;
        this.deadlineTime = deadlineTime;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    // Note to self: Do I need to convert String to LocalTime, so that toModelType can be able to create the task?
    public JsonAdaptedTask(Task source) {
        // For future reference, this.marked = source.getMark()?
        description = source.getDescription().toString(); // Generally for all tasks
        type = source.getType(); // Generally for all tasks

        if (source instanceof Deadline) {
            this.date = ((Deadline) source).getDate().getDate(); // For Deadline
            this.deadlineTime = ((Deadline) source).getTime().getTime(); // For Deadline
        } else if ((source instanceof Event)) {
            this.date = ((Event) source).getDate().getDate(); // For Event
            this.eventStartTime = ((Event) source).getStartTime().getTime(); // For Event
            this.eventEndTime = ((Event) source).getEndTime().getTime(); // For Event
        } else {
            this.date = "";
            this.deadlineTime = "";
            this.eventStartTime = "";
            this.eventEndTime = "";
        }
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
        if (type == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Task.class.getSimpleName()));
        }
        Description desc = new Description(description);
        if (type.equals("todo")) {
            return new Todo(desc);
        } else if (type.equals("deadline")) {
            Date currDeadlineDate = new Date(date);
            Time currDeadlineTime = new Time(deadlineTime);
            return new Deadline(desc, currDeadlineDate, currDeadlineTime);
        } else if (type.equals("event")) {
            Date currEventDate = new Date(date);
            Time currEventStartTime = new Time(eventStartTime);
            Time currEventEndTime = new Time(eventEndTime);
            return new Event(desc, currEventDate, currEventStartTime, currEventEndTime);
        }
        return new Todo(desc);
    }
}
