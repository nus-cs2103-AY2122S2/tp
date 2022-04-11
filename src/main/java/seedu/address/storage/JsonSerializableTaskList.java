package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.TaskList;
import seedu.address.model.task.Task;

/**
 * An Immutable TaskList that is serializable to JSON format.
 */
@JsonRootName(value = "tasklist")
public class JsonSerializableTaskList {

    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    @JsonCreator
    public JsonSerializableTaskList(@JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.tasks.addAll(tasks);
    }

    public JsonSerializableTaskList(ReadOnlyTaskList source) {
        tasks.addAll(source.getObservableTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this task list into the model's {@code TaskList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TaskList toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        TaskList taskList = new TaskList();
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (!addressBook.validTask(task)) {
                throw new IllegalValueException("Invalid task!");
            }
            taskList.addTask(task);
        }
        return taskList;
    }
}
