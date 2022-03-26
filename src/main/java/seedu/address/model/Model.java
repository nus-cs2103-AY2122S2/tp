package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.Schedule;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Schedule> PREDICATE_SHOW_ALL_SCHEDULES = unused -> true;

    /** {@code Predicate} that evaluates to true when a person has lineup name*/
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS_WITH_LINEUP = person -> !person.getLineupNames().isEmpty();

    //=========== MyGM (Start) =====================================================================
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

    //=========== MyGM (End) ==============================================================================

    //=========== MyGM Player (Start) =====================================================================

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

    // Below are added to fit MyGM needs (Start)
    /**
     * Returns true if the name is taken by some player.
     */
    boolean hasPersonName(Name targetName);

    /**
     * Returns true if the lineup name is taken by some lineup.
     */
    boolean hasLineupName(LineupName targetName);

    /**
     * Add a lineup to MyGM.
     */
    void addLineup(Lineup toAdd);

    /**
     * TO BE FILLED
     */
    void deleteLineup(Lineup lineup);

    /**
     * TO BE FILLED
     */
    void putPersonIntoLineup(Person player, Lineup lineup);

    /**
     * TO BE FILLED
     */
    void setLineup(Lineup target, Lineup editedLineup);

    /**
     * Delete a player from the lineup.
     */
    void deletePersonFromLineup(Person player, Lineup lineup);

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

    /**
     * TO BE FILLED
     */
    Lineup getLineup(LineupName targetName);

    /**
     * Refreshes the model.
     */
    void refresh();
    // Below are added to fit MyGM needs (End)

    //=========== MyGM Player (End) =========================================================================

    //=========== MyGM Schedule (Start) =====================================================================

    /**
     * Returns true if a schedule with the same identity as {@code schedule} exists in MyGM.
     */
    boolean hasSchedule(Schedule schedule);

    /**
     * Deletes the given schedule.
     * The schedule must exist in MyGM.
     */
    void deleteSchedule(Schedule target);

    /**
     * Adds the given schedule.
     * {@code schedule} must not already exist in MyGM.
     */
    void addSchedule(Schedule schedule);

    /**
     * Replaces the given schedule {@code target} with {@code editedPerson}.
     * {@code target} must exist in MyGM.
     * The schedule identity of {@code editedPerson} must not be the same as another existing schedule in MyGM.
     */
    void setSchedule(Schedule target, Schedule editedSchedule);

    //=========== MyGM Schedule (End) =======================================================================

    //=========== MyGM PlayerList (Start) ===================================================================

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    //=========== MyGM PlayerList (End) ======================================================================

    //=========== MyGM ScheduleList (Start) ==================================================================

    /** Returns an unmodifiable view of the filtered schedule list */
    ObservableList<Schedule> getFilteredScheduleList();

    /**
     * Updates the filter of the filtered schedule list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredScheduleList(Predicate<Schedule> predicate);

    //=========== MyGM ScheduleList (End) ==================================================================
}
