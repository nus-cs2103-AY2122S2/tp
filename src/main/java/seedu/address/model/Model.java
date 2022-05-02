package seedu.address.model;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.candidate.Candidate;
import seedu.address.model.interview.Interview;


/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Candidate> PREDICATE_SHOW_ALL_CANDIDATES = unused -> true;

    /** {@code Predicate} that always evaluate to false */
    Predicate<Candidate> PREDICATE_SHOW_EMPTY_LIST = unused -> false;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Interview> PREDICATE_SHOW_ALL_INTERVIEWS = unused -> true;

    /** {@code Predicate} that always evaluate to false */
    Predicate<Interview> PREDICATE_SHOW_EMPTY_INTERVIEW_SCHEDULE = unused -> false;

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
     * Returns true if a candidate with the same identity as {@code candidate} exists in the address book.
     */
    boolean hasCandidate(Candidate candidate);

    /**
     * Deletes the given candidate.
     * The candidate must exist in the address book.
     */
    void deleteCandidate(Candidate target);

    /**
     * Adds the given candidate.
     * {@code candidate} must not already exist in the address book.
     */
    void addCandidate(Candidate candidate);

    /**
     * Replaces the given candidate {@code target} with {@code editedCandidate}.
     * {@code target} must exist in the address book.
     * The candidate identity of {@code editedCandidate} must not be the same as another existing candidate
     * in the address book.
     */
    void setCandidate(Candidate target, Candidate editedCandidate) throws CommandException;

    /** Returns an unmodifiable view of the filtered candidate list */
    ObservableList<Candidate> getFilteredCandidateList();

    /**
     * Updates the filter of the filtered candidate list to filter by the given {@code predicate}.
     */
    void updateFilteredCandidateList(Predicate<Candidate> predicate);

    /**
     * Updates the sorting order of filtered candidate list to sort by the given {@code sortComparator}.
     */
    void updateSortedCandidateList(Comparator<Candidate> sortComparator);

    /**
     * Checks if {@code editedCandidate} already has an interview when editing {@code availability}
     */
    boolean hasInterview(Candidate editedCandidate);
    /**
     * Returns true if the candidate to be interviewed already has an interview scheduled.
     */
    boolean hasInterviewCandidate(Interview interview);
    /**
     * Returns true if the interview has a conflicting time slot with the interviews in the list.
     */
    boolean hasConflictingInterview(Interview interview);
    /**
     * Deletes the interview for the specified candidate.
     */
    void deleteInterviewForCandidate(Candidate target);
    /**
     * Deletes the interview.
     */
    void deleteInterview(Interview interviewToDelete);
    /**
     * Adds the interview.
     */
    void addInterview(Interview interview);
    /**
     * Sets the target interview to the editedInterview.
     */
    void setInterview(Interview target, Interview editedInterview) throws CommandException;

    /**
     * Gets candidate's scheduled interview if present
     */
    Interview getInterview(Candidate target);

    /** Returns an unmodifiable view of the filtered interview list */
    ObservableList<Interview> getFilteredInterviewSchedule();

    /**
     * Updates the interview list stored in system to filter by the given {@code predicate}.
     */
    void updateFilteredInterviewSchedule(Predicate<Interview> predicate);

    void updateInterviewCandidate(Interview target, Interview editedInterview);

    void deletePastInterviewsForInterviewList(LocalDateTime localDateTime) throws CommandException;

    void resetAllScheduledStatus() throws CommandException;

    List<Candidate> getExpiredInterviewCandidates();
}
