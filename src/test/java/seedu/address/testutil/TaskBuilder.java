package seedu.address.testutil;

import seedu.address.model.tasks.Deadline;
import seedu.address.model.tasks.Description;
import seedu.address.model.tasks.Task;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {
    public static final String DEFAULT_DESCRIPTION = "Do homework";
    public static final String DEFAULT_DEADLINE = "9234-05-06T07:08";

    private Description description;
    private Deadline deadline;

    /**
     * Creates a {@code TaskBuilder} with the default values
     */
    public TaskBuilder() {
        this.description = new Description(DEFAULT_DESCRIPTION);
        this.deadline = new Deadline(DEFAULT_DEADLINE);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        this.description = taskToCopy.getDescription();
        this.deadline = taskToCopy.getDeadline();
    }
    /**
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public TaskBuilder withDesc(String desc) {
        this.description = new Description(desc);
        return this;
    }
    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }
    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     * This uses individual year/month/day/hour/minute values
     */
    public TaskBuilder withDeadline(int year, int month, int day, int hour, int minute) {
        this.deadline = new Deadline(year, month, day, hour, minute);
        return this;
    }

    public Task build() {
        return new Task(description, deadline);
    }
}
