package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.Task;

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
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Assigns {@code Task} to {@code person} with {@code studentId}.
     * A person with {@code studentId} must exist in the address book.
     * The {@code task} should be unique and not a duplicate of already assigned task.
     */
    void assignTaskToPerson(StudentId studentId, Task task);

    /**
     * Assigns {@code Task} to 1 or more {@code person} with taking the module with the {@code moduleCode}.
     * 1 or more persons with {@code moduleCode} must exist in the address book.
     * The {@code task} should be unique and not a duplicate of already assigned task.
     */
    void assignTaskToAllInModule(ModuleCode moduleCode, Task task);

    /**
     * Marks {@code Task} to {@code person} with {@code studentId}.
     * A person with {@code studentId} must exist in the address book.
     * The {@code task} should be an existing unmarked assigned task.
     */
    void markTaskOfPerson(StudentId studentId, Index index);
    /**
     * Unmarks {@code Task} to {@code person} with {@code studentId}.
     * A person with {@code studentId} must exist in the address book.
     * The {@code task} should be an existing marked assigned task.
     */
    void unmarkTaskOfPerson(StudentId studentId, Index index);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /** Sorts the list of persons in alphabetical order by their names */
    void sortFilteredPersonList();
}
