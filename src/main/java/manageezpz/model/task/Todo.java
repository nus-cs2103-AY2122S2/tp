package manageezpz.model.task;

public class Todo extends Task {

    protected String type;
    protected Description description;
    /**
     * Constructor for the Task class.
     *
     * @param taskDescription information about the task.
     */
    public Todo(Description taskDescription) {
        this.type = "todo";
        this.description = taskDescription;
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

    /**
     * Returns the string representation of a todo.
     * @return a string representation of the todo, consisting of its description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + getDescription();
    }
}
