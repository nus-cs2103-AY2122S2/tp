package seedu.address.model.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.schedule.ScheduleCommand.MESSAGE_CONFLICTING_INTERVIEW;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.candidate.Candidate;
import seedu.address.model.interview.exceptions.ConflictingInterviewException;
import seedu.address.model.interview.exceptions.DuplicateCandidateException;
import seedu.address.model.interview.exceptions.InterviewNotFoundException;

//@@author lzan98
public class UniqueInterviewList implements Iterable<Interview> {
    private final ObservableList<Interview> internalList = FXCollections.observableArrayList();
    private final ObservableList<Interview> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent candidate as the given argument.
     */
    public boolean containsSameCandidate(Interview toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameInterviewCandidate);
    }

    /**
     * Returns true if the list contains an equivalent candidate as the given argument.
     */
    public boolean containsConflictingInterview(Interview toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isConflictingInterview);
    }
    /**
     * Adds an interview to the list.
     * The candidate must not already exist in the list.
     */
    public void add(Interview toAdd) {
        requireNonNull(toAdd);
        if (containsSameCandidate(toAdd)) {
            throw new DuplicateCandidateException();
        }
        if (containsConflictingInterview(toAdd)) {
            throw new ConflictingInterviewException();
        }
        internalList.add(toAdd);
    }
    /**
     * Replaces the interview {@code target} in the list with {@code editedInterview}.
     * {@code target} must exist in the list.
     * The interview {@code editedInterview} must not be in conflict with another interview for a different candidate.
     */
    public void setInterview(Interview target, Interview editedInterview) throws CommandException {
        requireAllNonNull(target, editedInterview);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new InterviewNotFoundException();
        }

        ObservableList<Interview> internalListCopy = FXCollections.observableArrayList(internalList);
        // Credits to teammate @tiewweijian for initial suggestion of deletion/removal of interviews
        // to solve the issue of rescheduling the same candidate
        internalListCopy.set(index, editedInterview);

        if (!interviewsDateTimeAreNonConflicting(internalListCopy)) {
            throw new CommandException(MESSAGE_CONFLICTING_INTERVIEW);
        }

        internalList.set(index, editedInterview);
    }

    /**
     * Replaces the interview {@code target} in the list with {@code editedInterview}, without checking for
     * any conflicting interview as only the candidate field in the {@code editedInterview} is modified.
     * {@code target} must exist in the schedule.
     */
    public void updateInterviewCandidate (Interview target, Interview editedInterview) {
        requireAllNonNull(target, editedInterview);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new InterviewNotFoundException();
        }

        internalList.set(index, editedInterview);
    }

    /**
     * Remove an interview from the list.
     */
    public void remove(Interview toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new InterviewNotFoundException();
        }
    }

    public void setInterviews(UniqueInterviewList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code candidates}.
     * {@code candidates} must not contain duplicate candidates.
     */
    public void setInterviews(List<Interview> interviews) {
        requireAllNonNull(interviews);
        if (!interviewsCandidatesAreUnique(interviews)) {
            throw new DuplicateCandidateException();
        }
        if (!interviewsDateTimeAreNonConflicting(interviews)) {
            throw new ConflictingInterviewException();
        }

        internalList.setAll(interviews);
    }

    /**
     * Checks if the interview list contains an interview with the same candidate in {@code toCheck}.
     * @param toCheck
     * @return
     */
    public boolean containsCandidate(Interview toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameInterviewCandidate);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Interview> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueInterviewList // instanceof handles nulls
                && internalList.equals(((UniqueInterviewList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public Iterator<Interview> iterator() {
        return internalList.iterator();
    }

    /**
     * Returns true if {@code candidates} contains only unique candidates.
     */
    private boolean interviewsCandidatesAreUnique(List<Interview> interviews) {
        for (int i = 0; i < interviews.size() - 1; i++) {
            for (int j = i + 1; j < interviews.size(); j++) {
                if (interviews.get(i).isSameInterviewCandidate(interviews.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Returns true if {@code interviews} contains only non-conflicting interviews.
     */
    private boolean interviewsDateTimeAreNonConflicting(List<Interview> interviews) {
        for (int i = 0; i < interviews.size() - 1; i++) {
            for (int j = i + 1; j < interviews.size(); j++) {
                if (interviews.get(i).isConflictingInterview(interviews.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Candidate> getExpiredInterviewCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        LocalDateTime currentDateTime = LocalDateTime.now();
        for (Interview i : internalList) {
            if (i.getInterviewEndDateTime().isBefore(currentDateTime)) {
                candidates.add(i.getCandidate());
            }
        }
        return candidates;
    }
}
