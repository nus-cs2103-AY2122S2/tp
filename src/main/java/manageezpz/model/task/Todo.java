package manageezpz.model.task;

public class Todo extends Task {

    protected String type;
    /**
     * Constructor for the Task class.
     *
     * @param taskDescription information about the task.
     */
    public Todo(Description taskDescription) {
        super(taskDescription);
        this.type = "todo";
    }

    @Override
    public String getType() {
        return this.type;
    }

    /**
     * Returns the string representation of a todo.
     * @return a string representation of the todo, consisting of its description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
