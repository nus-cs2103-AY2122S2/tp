package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS_WITH_LINEUP = person -> !person.getLineupNames().isEmpty();

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
     * Returns true if the name is taken by some player.
     */
    boolean hasPersonName(Name targetName);

    boolean hasLineupName(LineupName targetName);

    /**
     * Add a lineup to MyGM.
     */
    void addLineup(Lineup toAdd);

    void deleteLineup(Lineup lineup);

    void putPersonIntoLineup(Person player, Lineup lineup);

    void setLineup(Lineup target, Lineup editedLineup);

    /**
     * Delete a player from the lineup.
     */
    void deletePersonFromLineup(Person player, Lineup lineup);

    /**
     * Refreshes the model.
     */
    void refresh();

    /**
     * Returns true if the person is inside the lineup.
     */
    boolean isPersonInLineup(Person person, Lineup lineup);

    /**
     * Returns true if the Jersey number specified by {@code person} is already taken.
     */
    boolean hasJerseyNumber(Person person);

    /**
     * Returns a String representation of available jersey numbers.
     */
    String getAvailableJerseyNumber();

    /**
     * Returns true if number of players has reached maximum capacity.
     */
    boolean isFull();

    /**
     * Returns the person with the given name.
     */
    Person getPerson(Name targetName);

    Lineup getLineup(LineupName targetName);

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

}
