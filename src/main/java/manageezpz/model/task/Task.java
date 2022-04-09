package manageezpz.model.task;

import java.util.ArrayList;
import java.util.List;

import manageezpz.model.person.Person;

/**
 * An abstract class that represents the tasks a user could create.
 * A <code> Task </code> object would correspond to a task inputted
 * by a user, either a Todo, Deadline or Event.
 */
public abstract class Task {
    protected boolean isDone = false;
    protected Priority priority = Priority.NONE;

    // Data fields
    protected List<Person> assignees = new ArrayList<>();

    /**
     * Constructor to initialize an instance of Task class.
     *
     * {@code Date taskDate} has a default value that will be changed if the
     * object inheriting the Task object is a Deadline or Event object.
     *
     */
    public Task() {
    }

    /**
     * Checks if the task is done or not.
     * @return true if task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    public abstract String getType();

    public abstract Description getDescription();

    public abstract String getDateTime();

    public String getStatusIcon() {
        return this.isDone() ? "X" : " ";
    }

    public Priority getPriority() {
        return this.priority;
    }

    public List<Person> getAssignees() {
        return this.assignees;
    }

    public void setTaskDone() {
        this.isDone = true;
    }

    public void setTaskNotDone() {
        this.isDone = false;
    }

    public void setPriority(String priority) {
        this.priority = Priority.valueOf(priority);
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Adds a Person to the Task List.
     * @param person the person to be added.
     */
    public void addAssignees(Person person) {
        this.assignees.add(person);
    }

    /**
     * Checks if both Task have the same Description.
     * @param otherTask the task to be checked against.
     * @return true if both task are the same, false otherwise.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getDescription().equals(getDescription());
    }

    /**
     * Used to assign a person to this Task.
     * @param person the person to be assigned.
     */
    public void assignedTo(Person person) {
        assignees.add(person);
    }

    /**
     * Used to assign a new person to the specific index in the assignee list.
     * @param index the index specified to the assignee list.
     * @param newPerson the new person to be placed into the assignee list.
     */
    public void assignedTo(int index, Person newPerson) {
        assignees.set(index, newPerson);
    }

    /**
     * Used to deallocate a person from this Task.
     * @param person The person to be deallocated.
     */
    public void removeAssigned(Person person) {
        assignees.remove(person);
    }

    /**
     * Checks whether the assignee is assigned to the task.
     * @param assignee The assignee to be searched
     * @return true if the assignee is assigned, false otherwise
     */
    public boolean haveAssignees(String assignee) {
        return assignees.stream()
                .anyMatch(person -> person.getName().fullName.equals(assignee));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] ";
    }

    /**
     * {@inheritDoc}
     */
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

}
