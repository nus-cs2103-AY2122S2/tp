package manageezpz.testutil;

import manageezpz.model.task.Description;
import manageezpz.model.task.Todo;

public class TodoBuilder {

    public static final String TASK_DESCRIPTION = "read book";
    private Description description;
    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TodoBuilder() {
        description = new Description(TASK_DESCRIPTION);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TodoBuilder(Todo taskToCopy) {
        description = taskToCopy.getDescription();
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public TodoBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    public Todo build() {
        return new Todo(description);
    }
}
