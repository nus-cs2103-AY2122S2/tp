package seedu.address.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.contact.Contact;
import seedu.address.model.meeting.Meeting;



/**
 * The API of the Model component.
 */
public interface Model {

    /** {@code Predicates} that always evaluate to true */
    Predicate<Contact> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that evaluates to true if meeting's archive status is false. */
    Predicate<Meeting> PREDICATE_SHOW_ALL_MEETINGS = meeting ->
            !(meeting.getArchiveStatus().isArchive);

    /** {@code Predicate} that evaluates to true if meeting's archive status is true. */
    Predicate<Meeting> PREDICATE_SHOW_ALL_ARCHIVED_MEETINGS = meeting -> (
            meeting.getArchiveStatus().isArchive);


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

    void commitAddressBook();

    void undoAddressBook();

    void redoAddressBook();

    boolean canUndoAddressBook();

    boolean canRedoAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Contact person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Contact target);

    void sortContact();

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Contact person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Contact target, Contact editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Contact> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */

    void updateFilteredPersonList(Predicate<Contact> predicate);





    /**
     * Returns true if a meeting with the same identity as {@code meeting} exists in the address book.
     */
    boolean hasMeeting(Meeting meeting);

    /**
     * Deletes the given meeting.
     * The meeting must exist in the address book.
     */
    void deleteMeeting(Meeting target);

    /**
     * Adds the given meeting.
     * {@code meeting} must not already exist in the address book.
     */
    void addMeeting(Meeting meeting);

    /**
     * Replaces the given meeting {@code target} with {@code editedMeeting}.
     * {@code target} must exist in the address book.
     * The meeting identity of {@code editedMeeting} must not be the
     * same as another existing meeting in the address book.
     */
    void setMeeting(Meeting target, Meeting editedMeeting);

    void sortMeeting();

    /** Returns an unmodifiable view of the filtered Meeting list */
    ObservableList<Meeting> getFilteredMeetingList();

    /**
     * Updates the filter of the filtered meeting list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMeetingList(Predicate<Meeting> predicate);

    /**
     * Checks for any clashes in Meeting timings.
     *
     * @param toAdd Meeting to check against current list of meetings.
     * @return List of meetings that clash with meeting to be added.
     */
    ArrayList<Meeting> checkMeetingClash(Meeting toAdd);
}
