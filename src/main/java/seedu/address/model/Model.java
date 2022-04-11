package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;

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

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the current {@code personOnDisplay} to {@code person}.
     *
     * @param person The {@code Person} object to update {@code personOnDisplay} with.
     */
    void updatePersonOnDisplay(Person person);

    /**
     * Returns a read only copy of the Person object on display.
     *
     * @return a read only copy of the Person object on display.
     */
    ReadOnlyProperty<Person> getPersonOnDisplay();

    /**
     * Adds a listener for when the personOnDisplay changes.
     *
     * @param listener ChangeListener that reacts to the Person object changing.
     */
    void addPersonOnDisplayListener(ChangeListener<? super Person> listener);

    /**
     * Restores address book to its previous state.
     */
    void undoAddressBook();

    /**
     * Restores address book to its previous undid state.
     */
    void redoAddressBook();

    /**
     * Returns true if address book is undoable; otherwise returns false.
     *
     * @return true if undoable; false otherwise.
     */
    boolean canUndoAddressBook();

    /**
     * Returns true if address book is redoable; otherwise returns false.
     *
     * @return true if redoable; false otherwise.
     */
    boolean canRedoAddressBook();

    /**
     * Saves the current address book state.
     */
    void saveAddressBookState();

    /**
     * Deletes the list of person from model.
     *
     * @param persons List of person to delete.
     */
    void deleteAllPerson(ObservableList<Person> persons);
}
