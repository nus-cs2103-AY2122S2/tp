package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that evaluate to true only when
     * teams field of person object is not-empty
     * */
    Predicate<Person> PREDICATE_SHOW_ALL_TEAMMATES = person -> !person.getTeams().isEmpty();

    /**
     * {@code Predicate} that evalutes to true if and only if the
     * person is a potential teammate.
     */
    Predicate<Person> PREDICATE_SHOW_POTENTIAL_TEAMMATES = Person::isPotentialTeammate;

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
     * Returns duplicate field that given {@code person} have as another {@code person} inside the address book.
     */
    String getDuplicateField(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Deletes the given person by comparing person::equals
     * Returns true if successfully removed person, else return false
     */
    boolean safeDeletePerson(Person target);

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

    /** Returns an unmodifiable view of the filtered and sorted person list */
    ObservableList<Person> getDisplayPersonList();

    /**
     * Updates the filter of the display person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateDisplayPersonList(Predicate<Person> predicate);

    /**
     * Updates the sort criteria of the display person list to sort by the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updateDisplayPersonList(Comparator<Person> comparator);

    /**
     * Updates both the filter and the sort criteria of the
     * display person list to sort by the given {@code predicate and @code comparator}.
     * @throws NullPointerException if {@code comparator or @code predicate} is null.
     */
    void updateDisplayPersonList(Predicate<Person> predicate, Comparator<Person> comparator);

    /** Returns whether the AddressBook has commands that can be undone. */
    boolean canUndoAddressBook();

    /** Un-do the last command that modified the AddressBook, provided that the AddressBook supports it. */
    void undoAddressBook();

    /** Returns whether the AddressBook has commands that can be redone. */
    boolean canRedoAddressBook();

    /** Re-do the last command that modified the AddressBook, provided that the AddressBook supports it. */
    void redoAddressBook();

    /** Commits a version of the current AddressBook into its history, provided that the AddressBook supports it. */
    void commitAddressBook();
}
