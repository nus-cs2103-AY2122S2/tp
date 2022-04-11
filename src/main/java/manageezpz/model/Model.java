package manageezpz.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import manageezpz.commons.core.GuiSettings;
import manageezpz.model.person.Person;
import manageezpz.model.task.Deadline;
import manageezpz.model.task.Event;
import manageezpz.model.task.Priority;
import manageezpz.model.task.Task;
import manageezpz.model.task.Todo;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true to show all tasks */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     * @param userPrefs the userPrefs to be replaced with.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Gets the user preference.
     * @return the user preference.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Gets the user prefs' GUI settings.
     * @return the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     * @param guiSettings the guiSettings to be set.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Gets the user prefs' address book file path.
     * @return the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     * @param addressBookFilePath the provided file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     * @param addressBook the addressBook to be replaced with.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Gets the AddressBook
     * @return the AddressBook.
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Checks if a person with the same identity as {@code person} exists in the address book.
     * @param person the person to be checked against.
     * @return true if a person has the same identity, false otherwise.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     * @param target the person to be deleted.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     * @param person the person to be added.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     * @param target the person to be replaced.
     * @param editedPerson the new person to replace the target.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Increments the number of task associated with the person.
     * @param person the person whose number of task is to be incremented.
     */
    void increaseNumOfTasks(Person person);

    /**
     * Decrements the number of task associated with the person.
     * @param person the person whose number of task is to be decremented.
     */
    void decreaseNumOfTasks(Person person);

    /**
     * Gets an unmodifiable view of the filtered person list
     * @return an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @param predicate the provided condition.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);


    //=========== ManageEZPZ (Tasks) ==================================================================================

    /**
     * Deletes the given task.
     * The task must exist in the task list.
     * @param task the task to be deleted.
     */
    void deleteTask(Task task);

    /**
     * Updates the task with the edited person.
     * The task must exist in the task list.
     * @param task the task to be updated.
     * @param assigneesIndex the index to the Persons List.
     * @param editedPerson the person to update with.
     */
    void updateTaskWithEditedPerson(Task task, int assigneesIndex, Person editedPerson);

    /**
     * Marks the given task.
     * The task must exist in the task list.
     * @param task the task to be marked.
     * @return the marked task.
     */
    Task markTask(Task task);

    /**
     * unMarks the given task.
     * The task must exist in the task list.
     * @param task the task to be unmarked.
     * @return the unmarked task.
     */
    Task unmarkTask(Task task);

    /**
     * Assigns a task to a priority.
     * @param task the task to be assigned.
     * @param priority the priority to be assigned to the task.
     * @return the assigned task with the provided priority.
     */
    Task tagPriorityToTask(Task task, Priority priority);


    /**
     * Tags the given task to the specified person.
     * The task must exist in the task list.
     * The person must exist in the address book.
     * @param task the task to be tagged.
     * @param person the person to be tagged to by the task.
     * @return the tagged task.
     */
    Task tagEmployeeToTask(Task task, Person person);


    /**
     * Untags the given task from the specified person.
     * The task must exist in the task list.
     * The person must exist in the address book.
     * @param task the task to be untagged.
     * @param person the person to be untagged from by the task.
     * @return the untagged task.
     */
    Task untagEmployeeFromTask(Task task, Person person);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the task list
     * @param task the task to be added.
     */
    void addTask(Task task);

    /**
     * Adds the given todo Task.
     * {@code Todo} must not already exist in the task list
     * @param todo the todo Task to be added.
     */
    void addTodo(Todo todo);

    /**
     * Adds the given event.
     * {@code event} must not already exist in the task list
     * @param event the event Task to be added.
     */
    void addEvent(Event event);

    /**
     * Adds the given deadline.
     * {@code deadline} must not already exist in the task list
     * @param deadline the deadline Task to be added.
     */
    void addDeadline(Deadline deadline);

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @param predicate the given condition.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    /**
     * Gets an unmodifiable view of the filtered task list.
     * @return an unmodifiable view of the filtered task list.
     */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Checks if the Task list has the specified task.
     * @param task the task to be checked.
     * @return true if a task with the same identity as {@code Task} exists in the task list, false otherwise.
     */
    boolean hasTask(Task task);

    /**
     * Checks if the Task list has the specified todo task.
     * @param todo the task to be checked.
     * @return true if a task with the same identity as {@code Todo} exists in the task list, false otherwise.
     */
    boolean hasTodo(Todo todo);

    /**
     * Checks if the Task list has the specified deadline task.
     * @param deadline the task to be checked.
     * @return true if a task with the same identity as {@code Deadline} exists in the task list, false otherwise.
     */
    boolean hasDeadline(Deadline deadline);

    /**
     * Checks if the Task list has the specified event task.
     * @param event the task to be checked.
     * @return true if a task with the same identity as {@code Event} exists in the task list, false otherwise.
     */
    boolean hasEvent(Event event);

    /**
     * Checks if a Person is tagged to the specified task.
     * @param task the task to be checked.
     * @param person the person to be checked.
     * @return true if a {@code Task} is tagged to the person {@code Person}, false otherwise.
     */
    boolean isEmployeeTaggedToTask(Task task, Person person);


    /**
     * Returns true if a {@code Task} is allocated with a priority.
     */
    boolean hasPriority(Task task);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the task list.
     * The task identity of {@code editedTask} must not be the same as another existing task in the task list.
     * @param target the task to be replaced.
     * @param editedTask the new task to replace the target.
     */
    void setTask(Task target, Task editedTask);
}
