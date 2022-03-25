package seedu.address.testutil;

import seedu.address.model.person.Task;

public class TaskBuilder {

    public static final String DEFAULT_TASK_NAME = "Assignment 1";

    public static final Boolean DEFAULT_IS_COMPLETE = false;

    private String taskName;
    private boolean isComplete;

    /**
     * Initializes a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        taskName = DEFAULT_TASK_NAME;
        isComplete = DEFAULT_IS_COMPLETE;
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        taskName = taskToCopy.getTaskName();
        isComplete = taskToCopy.isTaskComplete();
    }

    /**
     * Sets the {@code Name} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String taskName) {
        this.taskName = taskName;
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Task} that we are building.
     */
    public TaskBuilder withStatus(Boolean isComplete) {
        this.isComplete = isComplete;
        return this;
    }

    /**
     * Build a Task based on the fields specified.
     * @return The Task object built.
     */
    public Task build() {
        return new Task(taskName);
    }
}
