package seedu.address.model;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniqueModuleList;
import seedu.address.model.tasks.ReadOnlyTaskList;
import seedu.address.model.tasks.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' contact list file path.
     */
    Path getContactListFilePath();

    /**
     * Sets the user prefs' contact list file path.
     */
    void setContactListFilePath(Path contactListFilePath);

    /**
     * Replaces contact list data with the data in {@code contactList}.
     */
    void setContactList(ReadOnlyContactList contactList);

    /** Returns the ContactList */
    ReadOnlyContactList getContactList();

    /**
     * Returns the user prefs' task list file path.
     */
    Path getTaskListFilePath();

    /**
     * Sets the user prefs' task list file path.
     */
    void setTaskListFilePath(Path taskListFilePath);

    /**
     * Replaces task list data with the data in {@code taskList}.
     */
    void setTaskList(ReadOnlyTaskList taskList);

    /** Returns the task list */
    ReadOnlyTaskList getTaskList();

    /** Returns an unmodifiable view of the task list */
    ObservableList<Task> getUnmodifiableTaskList();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the contact list.
     */
    boolean hasPerson(Person person);

    boolean hasPersonIgnoreTags(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the contact list.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the contact list.
     */
    void addPerson(Person person);

    void addMod(Mod mod);

    Optional<Mod> getMod(Mod mod);

    String getDefaultGroupOfMod(Mod mod);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the contact list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the contact list.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Checks if the specified mod already has a default value set.
     * @param mod the module object meant to be checked
     * @return true
     */
    boolean isDefaultGroupOfModPresent(Mod mod);

    boolean doesModExistInList(Mod mod);

    /**
     * Retrieves the current default group value for a particular module
     * @param mod the module object whose default value is to be retrieved.
     * @return String the default group title.
     */
    String retrievePrevDefault(Mod mod);

    void setDefaultGroup(Mod mod, String value);

    UniqueModuleList getModuleList();

    /**
     * Reverts the Contents to a previous version since application initialisation.
     */
    void undoContents();

    /**
     * Reverts the Contents to a version after the current one since application initialisation.
     */
    void redoContents();

    /**
     * Commits the new content state to the history
     */
    void commitContent();

    /**
     * Checks if the model has a content state before the current one
     */
    boolean canUndo();

    /**
     * Checks if the model has a content state after the current one
     */
    boolean canRedo();

    /**
     * Returns the VersionedContents of the model
     * @return versioned contents of the model
     */
    VersionedContents getVersionedContents();
    /**
     * Returns true if a task with the same identity as {@code task} exists in the task manager.
     */
    boolean hasTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in the task manager.
     */
    void deleteTask(Task target);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the task manager.
     */
    void addTask(Task task);

    void setModuleList(UniqueModuleList moduleList);
}
