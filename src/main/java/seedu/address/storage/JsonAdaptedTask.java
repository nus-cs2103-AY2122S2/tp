package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tasks.Deadline;
import seedu.address.model.tasks.Description;
import seedu.address.model.tasks.Task;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String description;
    private final String deadline;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("description") String description,
                           @JsonProperty("deadline") String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        this.description = source.getDescription().desc;
        this.deadline = source.getDeadline().deadline.toString();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {

        if (description == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        }
        if (!Description.isValidDesc(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDesc = new Description(description);

        if (deadline == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName()));
        }
        if (!Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        final Deadline modelDeadline = new Deadline(deadline);

        return new Task(modelDesc, modelDeadline);

    }

}
