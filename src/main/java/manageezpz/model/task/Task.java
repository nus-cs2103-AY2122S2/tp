package manageezpz.model.task;

import static manageezpz.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import manageezpz.model.person.Person;

/**
 * Represents the Tasks a user could create. A <code> Task </code> object would correspond to a task
 * inputted by a user either a Todo, Deadline or Event.
 */
public class Task implements Comparable<Task> {
    protected boolean isDone;
    protected String type;
    protected Priority priority;

    public enum Priority {
        HIGH, LOW, MEDIUM, NONE;
    }

    // Identity fields
    private final Description taskDescription;

    // Data fields
    private List<Person> assignees; //List of Strings as of now, V1.3 will incorporate Persons (assign tasks to Persons)
    //private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructor for the Task class.
     * {@code Date taskDate} has a default value that will be changed if the object inheriting the Task object
     * is a Deadline or Event object. If object is a Todo object, this field will be ignored.
     * @param taskDescription information about the task.
     */
    public Task(Description taskDescription) {
        requireAllNonNull(taskDescription);
        this.taskDescription = taskDescription;
        this.isDone = false;
        this.type = "";
        this.assignees = new ArrayList<>();
        this.priority = Priority.NONE;
    }

    /**
     * Returns X if the task is done, otherwise blank.
     * @return the string representation of the status of the task.
     */
    public String getStatusIcon() {
        if (this.isDone()) {
            return "X";
        } else {
            return " ";
        }
    }

    public List<Person> getAssignees() {
        return this.assignees;
    }

    public void addAssignees(Person person) {
        this.assignees.add(person);
    }

    public void setTaskDone() {
        this.isDone = true;
    }

    public void setTaskNotDone() {
        this.isDone = false;
    }

    public Description getDescription() {
        return this.taskDescription;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDateTime() {
        return "";
    }

    public void setPriority(String priority) {
        this.priority = Priority.valueOf(priority);
    }

    public Priority getPriority() {
        return this.priority;
    }


    int getAssignedValue(String s) {
        switch (s) {
        case "NONE":
            return 3;
        case "LOW":
            return 2;
        case "MEDIUM":
            return 1;
        case "HIGH":
            return 0;
        default:
            return Integer.MAX_VALUE;
        }
    }

    @Override
    public int compareTo(Task o) {
        String first = this.getPriority().name();
        String second = o.getPriority().name();
        return Integer.compare(getAssignedValue(first), getAssignedValue(second));
    }

    /**
     * Returns the string representation of the task.
     * @return a string representation of the task, consisting of its description and whether its done or not.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Returns true if both Task have the same name.
     * This defines a weaker notion of equality between two Task.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getDescription().equals(getDescription());
    }

    public void assignedTo(Person person) {
        assignees.add(person);
    }

    public void removeAssigned(Person person) {
        assignees.remove(person);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getDescription().equals(getDescription())
                && otherTask.getStatusIcon().equals(getStatusIcon());
    }

    /**
     * Checks whether the assignee is assigned to the task.
     * @param assignee The assignee to be searched
     * @return True if the assignee is assigned, false otherwise
     */
    public boolean haveAssignees(String assignee) {
        return assignees.stream()
                .anyMatch(person -> person.getName().fullName.equals(assignee));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(taskDescription);
    }
}
