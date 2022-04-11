package seedu.address.testutil;

import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {
    public static final String TASK_NAME = "Department Meeting";

    private TaskName taskName;

    /**
     * Creates a {@code GroupBuilder} with the default details.
     */
    public TaskBuilder() {
        taskName = new TaskName(TASK_NAME);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        taskName = taskToCopy.getTaskName();
    }

    /**
     * Sets the {@code TaskName} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskName(String taskName) {
        this.taskName = new TaskName(taskName);
        return this;
    }

    public Task build() {
        return new Task(taskName);
    }
}
