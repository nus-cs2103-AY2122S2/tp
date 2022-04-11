package manageezpz.model.task;

/**
 * A class that represents a Todo task.
 */
public class Todo extends Task {
    protected String type;
    protected Description description;

    /**
     * Constructor to initialize an instance of Todo class with task description.
     *
     * @param taskDescription Description of the task.
     */
    public Todo(Description taskDescription) {
        this.type = "todo";
        this.description = taskDescription;
    }

    /**
     * Constructor to initialize an instance of Todo class with an existing Todo object.
     *
     * @param todo Todo task.
     */
    public Todo(Todo todo) {
        this.type = todo.getType();
        this.description = todo.getDescription();
        this.isDone = todo.isDone();
        this.priority = todo.getPriority();
        this.assignees = todo.getAssignees();
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public Description getDescription() {
        return this.description;
    }

    @Override
    public String getDateTime() {
        return null;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    /**
     * Returns the string representation of a Todo task.
     *
     * @return The string representation of the Todo task, consisting of its
     * description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + getDescription();
    }
}
