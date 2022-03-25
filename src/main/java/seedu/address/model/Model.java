package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.candidate.Candidate;
import seedu.address.model.interview.Interview;


/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Candidate> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to false */
    Predicate<Candidate> PREDICATE_SHOW_EMPTY_LIST = unused -> false;

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
     * Returns the user prefs' interview list file path.
     */
    Path getInterviewScheduleFilePath();

    /**
     * Sets the user prefs' interview list file path.
     */
    void setInterviewScheduleFilePath(Path interviewListFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Replaces interview list data with the data in {@code interviewList}.
     */
    void setInterviewSchedule(ReadOnlyInterviewSchedule interviewList);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /** Returns the InterviewSchedule */
    ReadOnlyInterviewSchedule getInterviewSchedule();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Candidate candidate);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Candidate target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Candidate candidate);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Candidate target, Candidate editedCandidate);

    boolean hasInterviewCandidate(Interview interview);

    boolean hasConflictingInterview(Interview interview);

    void deleteInterview(Candidate target);

    void addInterview(Interview interview);

    //void setInterview(Interview target, Interview editedInterview);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Candidate> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Candidate> predicate);

    /**
     * Updates the sorting order of filtered person list to sort by the given {@code sortComparator}.
     * @throws NullPointerException if {@code sortKey} is null.
     */
    void updateSortedPersonList(Comparator<Candidate> sortComparator);
}
