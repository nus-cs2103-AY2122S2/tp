package manageezpz.model;

import static java.util.Objects.requireNonNull;
import static manageezpz.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import manageezpz.model.person.Person;
import manageezpz.model.person.UniquePersonList;
import manageezpz.model.task.Deadline;
import manageezpz.model.task.Event;
import manageezpz.model.task.Priority;
import manageezpz.model.task.Task;
import manageezpz.model.task.Todo;
import manageezpz.model.task.UniqueTaskList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueTaskList tasks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        tasks = new UniqueTaskList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     * @param toBeCopied the provided addressBook.
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     * @param persons the list of persons to replace the old list.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     * @param newData the new addressBook to replace the old one.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
        setTasks(newData.getTaskList());
    }

    /// task-level operations

    /**
     * Returns true if a task with the same identity as {@code task} exists in the task list.
     * @param task the task to be checked.
     * @return true if same identity otherwise false.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Adds a Task to the task list.
     * The task must not already exist in the task list.
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Replaces the contents of the task list with {@code task}.
     * {@code task} must not contain duplicate tasks.
     * @param task the list of tasks to replace the old one.
     */
    public void setTasks(List<Task> task) {
        this.tasks.setTasks(task);
    }

    /**
     * Removes {@code task} from this {@code AddressBook}.
     * {@code task} must exist in the address book.
     * @param task the task to be removed.
     */
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    /**
     * Checks if a todo with the same identity as {@code todo} exists in the task list.
     * @param todo a valid Todo task.
     * @return true if a todo with the same identity as {@code todo} exists in the task list, false otherwise.
     */
    public boolean hasTodo(Todo todo) {
        requireNonNull(todo);
        return tasks.contains(todo);
    }

    /**
     * Adds a Todo to the task list.
     * @param todo a valid Todo task.
     */
    public void addTodo(Todo todo) {
        this.tasks.add(todo);
    }

    /**
     * Check if an event with the same identity as {@code event} exists in the task list.
     * @param event an event in the task list.
     * @return true if event has same identity as {@code event} exists in the task list, false otherwise.
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return tasks.contains(event);
    }

    /**
     * Adds an Event to the task list.
     * @param event a valid Event task.
     */
    public void addEvent(Event event) {
        this.tasks.add(event);
    }

    /**
     * Checks if a deadline with the same identity as {@code deadline} exists in the task list.
     * @param deadline a valid deadline in the task list.
     * @return true if deadline has same identity as {@code deadline} exists in the task list, false otherwise.
     */
    public boolean hasDeadline(Deadline deadline) {
        requireNonNull(deadline);
        return tasks.contains(deadline);
    }

    /**
     * Adds a Deadline to the task list.
     * @param deadline a valid deadline task.
     */
    public void addDeadline(Deadline deadline) {
        this.tasks.add(deadline);
    }

    /**
     * Updates the task with the edited person.
     * @param task the task to be updated.
     * @param assigneesIndex the index of the person in List of assignees.
     * @param editedPerson the edited person.
     */
    public void updateTaskWithEditedPerson(Task task, int assigneesIndex, Person editedPerson) {
        requireAllNonNull(task, assigneesIndex, editedPerson);
        this.tasks.updateTaskWithEditedPerson(task, assigneesIndex, editedPerson);
    }

    /**
     * Marks the task in the task list.
     * @param task the task to be marked.
     * @return the marked task.
     */
    public Task markTask(Task task) {
        requireNonNull(task);
        return this.tasks.markTask(task);
    }

    /**
     * unMarks the task in the task list.
     * @param task the task to be unmarked.
     * @return the unmarked task.
     */
    public Task unmarkTask(Task task) {
        requireNonNull(task);
        return this.tasks.unmarkTask(task);
    }

    /**
     * Tags a priority to the task.
     * @param task the task to be tagged.
     * @param priority the priority to be tagged to the task.
     * @return the tagged task.
     */
    public Task tagPriorityToTask(Task task, Priority priority) {
        requireAllNonNull(task, priority);
        return this.tasks.tagPriorityToTask(task, priority);
    }

    /**
     * Tags the task in the task list to a person in the address book.
     * @param task the task to be tagged.
     * @param person the person to be tagged to the task.
     * @return the tagged task.
     */
    public Task tagEmployeeToTask(Task task, Person person) {
        requireAllNonNull(task, person);
        return this.tasks.tagEmployeeToTask(task, person);
    }

    /**
     * Remove the Person from the Task, also decreasing the person's task count.
     * @param task the task affected.
     * @param person the person to be untagged from task
     * @return the untagged task.
     */
    public Task untagEmployeeFromTask(Task task, Person person) {
        requireAllNonNull(task, person);
        return this.tasks.untagEmployeeFromTask(task, person);
    }

    /**
     * Checks if a given Person is tagged to the task.
     * @param task the task to be checked.
     * @param person the person to be checked against.
     * @return true if the person is tagged to the task, false otherwise.
     */
    public boolean isEmployeeTaggedToTask(Task task, Person person) {
        requireAllNonNull(task, person);
        return task.getAssignees().contains(person);
    }

    /**
     * Checks if a given task has a priority tagged to it.
     * @param task the task to be checked.
     * @return true if the task is tagged with a priority, false otherwise.
     */
    public boolean hasPriority(Task task) {
        requireNonNull(task);
        boolean returnValue;
        if (task.getPriority() != null) {
            returnValue = true;
        } else {
            returnValue = false;
        }
        return returnValue;
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the task list.
     * The task identity of {@code editedTask} must not be the same as another existing task in the task list.
     * @param target the task to be replaced.
     * @param editedTask the new task to replace the target.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);
        tasks.setTask(target, editedTask);
    }

    //// person-level operations

    /**
     * Checks if a person with the same identity as {@code person} exists in the address book.
     * @param person the person to be checked.
     * @return true if a person has the same identity as the person specified, false otherwise.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     * @param person the person to be added.
     */
    public void addPerson(Person person) {
        persons.add(person);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     * @param target the person to be replaced.
     * @param editedPerson the new person to replace the target.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        persons.setPerson(target, editedPerson);
    }

    /**
     * Increments the number of task associated with the person.
     * @param person the person whose number of task is to be incremented.
     */
    public void increaseNumOfTasks(Person person) {
        requireNonNull(person);
        this.persons.increaseNumOfTasks(person);
    }

    /**
     * Decrements the number of task associated with the person.
     * @param person the person whose number of task is to be decremented.
     */
    public void decreaseNumOfTasks(Person person) {
        requireNonNull(person);
        this.persons.decreaseNumOfTasks(person);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     * @param key the person to be removed.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// util methods

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons)
                && tasks.equals(((AddressBook) other).tasks));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
