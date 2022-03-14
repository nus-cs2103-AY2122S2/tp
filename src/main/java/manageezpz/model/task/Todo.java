package manageezpz.model.task;

public class Todo extends Task {
    /**
     * Constructor for the Task class.
     *
     * @param taskDescription information about the task.
     */
    public Todo(Description taskDescription) {
        super(taskDescription);
        super.setType("todo");
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
