package manageezpz.model;

import static java.util.Objects.requireNonNull;
import static manageezpz.commons.util.CollectionUtil.requireAllNonNull;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_EVENT;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TODAY;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TODO;

import java.util.Iterator;
import java.util.List;

import javafx.collections.ObservableList;
import manageezpz.logic.parser.Prefix;
import manageezpz.model.person.Person;
import manageezpz.model.person.UniquePersonList;
import manageezpz.model.task.Date;
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
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Gets the list of all tasks.
     * @return A string representing all tasks
     */
    public String listTask() {
        Iterator<Task> taskIterators = tasks.iterator();
        String result = "";
        int index = 1;

        while (taskIterators.hasNext()) {
            Task task = taskIterators.next();
            String curIndex = String.join("", String.valueOf(index), ".");
            String curTask = String.join(" ", curIndex, task.toString());
            result = String.join("\n", result, curTask);
            index++;
        }

        return result;
    }

    /**
     * Gets the list of all tasks with the options
     * @param option Option provided to filter list
     * @return All tasks that satisfy the option given
     */
    public String listTask(Prefix option) {
        if (PREFIX_TODO.equals(option)) {
            return listTodo();
        } else if (PREFIX_DEADLINE.equals(option)) {
            return listDeadline();
        } else if (PREFIX_EVENT.equals(option)) {
            return listEvent();
        } else if (PREFIX_TODAY.equals(option)) {
            return listToday();
        }
        assert true : "Invalid option, should be checked in list command parser";
        return null;
    }

    /**
     * Returns the list of all todos.
     * @return List of all todos.
     */
    private String listTodo() {
        Iterator<Task> taskIterators = tasks.iterator();
        String result = "";
        int index = 1;

        while (taskIterators.hasNext()) {
            Task task = taskIterators.next();
            if (task instanceof Todo) {
                String curIndex = String.join("", String.valueOf(index), ".");
                String curTask = String.join(" ", curIndex, task.toString());
                result = String.join("\n", result, curTask);
                index++;
            }
        }

        return result;
    }

    private String listDeadline() {
        Iterator<Task> taskIterators = tasks.iterator();
        String result = "";
        int index = 1;

        while (taskIterators.hasNext()) {
            Task task = taskIterators.next();
            if (task instanceof Deadline) {
                String curIndex = String.join("", String.valueOf(index), ".");
                String curTask = String.join(" ", curIndex, task.toString());
                result = String.join("\n", result, curTask);
                index++;
            }
        }

        return result;
    }

    private String listEvent() {
        Iterator<Task> taskIterators = tasks.iterator();
        String result = "";
        int index = 1;

        while (taskIterators.hasNext()) {
            Task task = taskIterators.next();
            if (task instanceof Event) {
                String curIndex = String.join("", String.valueOf(index), ".");
                String curTask = String.join(" ", curIndex, task.toString());
                result = String.join("\n", result, curTask);
                index++;
            }
        }

        return result;
    }

    private String listToday() {
        Iterator<Task> taskIterators = tasks.iterator();
        Date todayDate = Date.getTodayDate();
        String result = "";
        int index = 1;

        while (taskIterators.hasNext()) {
            Task task = taskIterators.next();
            Date date = getDateFromTask(task);
            if (date != null && date.equals(todayDate)) {
                String curIndex = String.join("", String.valueOf(index), ".");
                String curTask = String.join(" ", curIndex, task.toString());
                result = String.join("\n", result, curTask);
                index++;
            }
        }

        return result;
    }

    private Date getDateFromTask(Task task) {
        if (task instanceof Deadline) {
            return ((Deadline) task).getDate();
        } else if (task instanceof Event) {
            return ((Event) task).getDate();
        } else {
            return null;
        }
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }


    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
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
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Replaces the contents of the task list with {@code task}.
     * {@code task} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> task) {
        this.tasks.setTasks(task);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
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
     * Adds a Event to the task list.
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
     */
    public Task markTask(Task task) {
        requireNonNull(task);
        return this.tasks.markTask(task);
    }

    /**
     * Unmarks the task in the task list.
     * @param task the task to be unmarked.
     */
    public Task unmarkTask(Task task) {
        requireNonNull(task);
        return this.tasks.unmarkTask(task);
    }

    /**
     * Tags a priority to the task.
     */
    public Task tagPriorityToTask(Task task, Priority priority) {
        requireAllNonNull(task, priority);
        return this.tasks.tagPriorityToTask(task, priority);
    }

    public void findTask(Task task) {
    }

    /**
     * Tags the task in the task list to a person in the address book.
     * @param task the task to be tagged.
     * @param person the person to be tagged to the task.
     */
    public Task tagEmployeeToTask(Task task, Person person) {
        requireAllNonNull(task, person);
        return this.tasks.tagEmployeeToTask(task, person);
    }

    /**
     * Remove the Person from the Task, also decreasing the person's task count.
     * @param task the task affected
     * @param person the person to be untagged from task
     */
    public Task untagEmployeeFromTask(Task task, Person person) {
        requireAllNonNull(task, person);
        return this.tasks.untagEmployeeFromTask(task, person);
    }

    /**
     * Checks if a given Person is tagged to the task.
     * Checks if a given Person is tagged to the task.
     * @param task the task to be checked.
     * @return true if the person is tagged to the task, false otherwise.
     */
    public boolean isEmployeeTaggedToTask(Task task, Person person) {
        requireAllNonNull(task, person);
        return task.getAssignees().contains(person);
    }

    /**
     * Checks if a given task has a priority tagged to it.
     * @param task the task to be checked.
     * @return true if the task is tagged with a prioirity, false otherwise.
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

    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);
        tasks.setTask(target, editedTask);
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        persons.setPerson(target, editedPerson);
    }

    /**
     * Increases the number of tasks by one.
     */
    public void increaseNumOfTasks(Person person) {
        requireNonNull(person);
        this.persons.increaseNumOfTasks(person);
    }

    /**
     * Decreases the number of tasks by one.
     */
    public void decreaseNumOfTasks(Person person) {
        requireNonNull(person);
        this.persons.decreaseNumOfTasks(person);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// util methods

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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons)
                && tasks.equals(((AddressBook) other).tasks));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}