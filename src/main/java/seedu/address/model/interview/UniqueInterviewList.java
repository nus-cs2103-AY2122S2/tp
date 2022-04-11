package seedu.address.model.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.interview.exceptions.DuplicateInterviewException;
import seedu.address.model.interview.exceptions.InterviewNotFoundException;

/**
 * A list of interviews that enforces uniqueness between its elements and does not allow nulls.
 * A interview is considered unique by comparing using {@code Interview#isSameInterview(Interview)}.
 * As such, adding and updating of Interviews uses Interview#isSameInterview(Interview)
 * for equality so as to ensure that the interview being added or updated is unique in terms of
 * identity in the UniqueInterviewList. However, the removal of a interview uses Interview#equals(Object)
 * so as to ensure that the interview with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Interview#isSameInterview(Interview)
 */
public class UniqueInterviewList implements Iterable<Interview> {

    private final ObservableList<Interview> internalList = FXCollections.observableArrayList();
    private final ObservableList<Interview> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent interview as the given argument.
     */
    public boolean contains(Interview toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameInterview);
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
        sortInterviews();
    }

    /**
     * Replaces the interview {@code target} in the list with {@code editedInterview}.
     * {@code target} must exist in the list.
     * The interview identity of {@code editedInterview} must not be the same as another existing interview in the list.
     */
    public void setInterview(Interview target, Interview editedInterview) {
        requireAllNonNull(target, editedInterview);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new InterviewNotFoundException();
        }

        if (!target.isSameInterview(editedInterview) && contains(editedInterview)) {
            throw new DuplicateInterviewException();
        }
        internalList.set(index, editedInterview);
        sortInterviews();
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
        sortInterviews();
    }

    public void sortInterviews() {
        internalList.sort(new InterviewDateTimeComparator());
    }

    public void clear() {
        internalList.clear();
    }

    /**
     * Replaces the contents of this list with {@code interviews}.
     * {@code interviews} must not contain duplicate interviews.
     */
    public void setInterviews(List<Interview> interviews) {
        requireAllNonNull(interviews);
        if (!interviewsAreUnique(interviews)) {
            throw new DuplicateInterviewException();
        }
        internalList.setAll(interviews);
        sortInterviews();
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
                || (other instanceof seedu.address.model.interview.UniqueInterviewList // instanceof handles nulls
                && internalList.equals(((seedu.address.model.interview.UniqueInterviewList) other)
                .internalList));
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
                if (interviews.get(i).isSameInterview(interviews.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

