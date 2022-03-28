package seedu.address.model.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.interview.exceptions.DuplicateInterviewException;
import seedu.address.model.interview.exceptions.InterviewNotFoundException;

public class UniqueInterviewList implements Iterable<Interview> {
    private final ObservableList<Interview> internalList = FXCollections.observableArrayList();
    private final ObservableList<Interview> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    /**
     * Returns true if the list contains an equivalent interview as the given argument.
     */
    public boolean contains(Interview toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Returns true if the list contains an interview which has a conflict in timing as the given argument.
     */
    public boolean containsConflict(Interview toCheck) {
        requireNonNull(toCheck);
        for (Interview i : internalList) {
            if (i.isConflictingInterview(toCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an interview to the list.
     * The interview must not already exist in the list.
     */
    public void add(Interview toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateInterviewException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the interview {@code target} in the list with {@code editedInterivew}.
     * {@code target} must exist in the list.
     * The interview {@code editedInterview} must not be the same as another existing interview in the list.
     */
    public void setInterview(Interview target, Interview editedInterview) {
        requireAllNonNull(target, editedInterview);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new InterviewNotFoundException();
        }

        if (!target.equals(editedInterview) && contains(editedInterview)) {
            throw new DuplicateInterviewException();
        }

        internalList.set(index, editedInterview);
    }

    /**
     * Removes the equivalent interview from the list.
     * The interview must exist in the list.
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
     * Replaces the contents of this list with {@code interview}.
     * {@code interview} must not contain duplicate interview.
     */
    public void setInterviews(List<Interview> interview) {
        requireAllNonNull(interview);
        if (!interviewsAreUnique(interview)) {
            throw new DuplicateInterviewException();
        }

        internalList.setAll(interview);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Interview> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Interview> iterator() {
        return internalList.iterator();
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

    /**
     * Returns true if {@code interviews} contains only unique interviews.
     */
    private boolean interviewsAreUnique(List<Interview> interviews) {
        for (int i = 0; i < interviews.size() - 1; i++) {
            for (int j = i + 1; j < interviews.size(); j++) {
                if (interviews.get(i).equals(interviews.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
