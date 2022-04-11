package seedu.address.storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tasks.PriorityTaskList;
import seedu.address.model.tasks.ReadOnlyTaskList;
import seedu.address.model.tasks.Task;

/**
 * An Immutable TaskList that is serializable to JSON format.
 */
@JsonRootName(value = "tasklist")
class JsonSerializableTaskList {

    public static final String MESSAGE_DUPLICATE_TASK = "Task list contains duplicate task(s).";

    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTaskList} with the given tasks.
     */
    @JsonCreator
    public JsonSerializableTaskList(@JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Converts a given {@code ReadOnlyContactList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableContactList}.
     */
    public JsonSerializableTaskList(ReadOnlyTaskList source) {
        Collection<Task> srcCollection = source.getInternalList();
        tasks.addAll(srcCollection.stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this task list into the model's {@code PriorityTaskList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PriorityTaskList toModelType() throws IllegalValueException {
        PriorityTaskList priorityTaskList = new PriorityTaskList();
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (priorityTaskList.contains(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            priorityTaskList.add(task);
        }
        return priorityTaskList;
    }
}
