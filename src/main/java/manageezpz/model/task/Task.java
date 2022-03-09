package manageezpz.model.task;

import manageezpz.model.person.Name;
import manageezpz.model.person.Person;

import java.util.List;

import static manageezpz.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents the Tasks a user could create. A <code> Task </code> object would correspond to a task
 * inputted by a user either a Todo, Deadline or Event.
 */
public class Task {

    // Identity fields
    private final Description taskDescription;

    // Data fields
    protected boolean isDone;
    private List<String> assignees; //List of Strings as of now, V1.3 will incorporate Persons (assign tasks to Persons)

    /**
     * Constructor for the Task class.
     * @param taskDescription information about the task.
     */
    public Task(Description taskDescription) {
        requireAllNonNull(taskDescription);
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    /**
     * Returns X if the task is done, otherwise blank.
     * @return the string representation of the status of the task.
     */
    public String getStatusIcon() {
        if (this.isDone) {
            return "X";
        } else {
            return " ";
        }
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

    /**
     * Returns the string representation of the task.
     * @return a string representation of the task, consisting of its description and whether its done or not.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }
}
