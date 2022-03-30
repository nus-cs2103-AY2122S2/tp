package seedu.address.model.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.interview.exceptions.DuplicateInterviewException;
import seedu.address.model.interview.exceptions.InterviewNotFoundException;
import seedu.address.model.position.Position;

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
     * Returns true if the interview is passable based off the number of currently
     * extended offers and position openings.
     */
    public boolean isPassableInterview(Interview toPass) {
        requireNonNull(toPass);
        return toPass.isPassableInterview();
    }

    /**
     * Returns true if the interview is passable based off the current status of
     * the interview.
     */
    public boolean isAcceptableInterview(Interview toAccept) {
        requireNonNull(toAccept);
        return toAccept.isAcceptableInterview();
    }

    /**
     * Returns true if the interview can be rejected based off the current status of
     * the interview.
     */
    public boolean isRejectableInterview(Interview toReject) {
        requireNonNull(toReject);
        return toReject.isRejectableInterview();
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

    //    /**
    //     * Passes an interview in the list. The interview must be passable based on the numbers of offers
    //     * currently extended.
    //     */
    //    public void pass(Interview toPass) {
    //        requireNonNull(toPass);
    //        if (!toPass.isPassableInterview()) {
    //            throw new NonPassableInterviewException();
    //        }
    //
    //        toPass.markAsPassed();
    //        int index = internalList.indexOf(toPass);
    //        if (index == -1) {
    //            throw new InterviewNotFoundException();
    //        }
    //
    //        //        if (!toPass.equals(toPass) && contains(toPass)) {
    //        //            throw new DuplicateInterviewException();
    //        //        }
    //
    //        internalList.set(index, toPass);
    //    }
    //
    //    /**
    //     * Fails an interview in the list.
    //     */
    //    public void fail(Interview toFail) {
    //        requireNonNull(toFail);
    //
    //        toFail.markAsFailed();
    //        int index = internalList.indexOf(toFail);
    //
    //        if (index == -1) {
    //            throw new InterviewNotFoundException();
    //        }
    //        //        if (!toFail.equals(toFail) && contains(toFail)) {
    //        //            throw new DuplicateInterviewException();
    //        //        }
    //
    //        internalList.set(index, toFail);
    //    }
    //
    //    /**
    //     * Accepts an interview in the list.
    //     */
    //    public void accept(Interview toAccept) {
    //        requireNonNull(toAccept);
    //
    //        toAccept.markAsAccepted();
    //        int index = internalList.indexOf(toAccept);
    //
    //        if (index == -1) {
    //            throw new InterviewNotFoundException();
    //        }
    //
    //        //        if (!toFail.equals(toFail) && contains(toFail)) {
    //        //            throw new DuplicateInterviewException();
    //        //        }
    //
    //        internalList.set(index, toAccept);
    //    }
    //
    //    /**
    //     * Rejects an interview in the list.
    //     */
    //    public void reject(Interview toReject) {
    //        requireNonNull(toReject);
    //
    //        toReject.markAsRejected();
    //        int index = internalList.indexOf(toReject);
    //
    //        if (index == -1) {
    //            throw new InterviewNotFoundException();
    //        }
    //
    //        //        if (!toFail.equals(toFail) && contains(toFail)) {
    //        //            throw new DuplicateInterviewException();
    //        //        }
    //
    //        internalList.set(index, toReject);
    //    }

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

    /**
     * Returns interview(s) which are for the specified applicant.
     */
    public ArrayList<Interview> getApplicantsInterviews(Applicant applicant) {
        ArrayList<Interview> interviews = new ArrayList<>();

        for (Interview i : internalList) {
            if (i.isInterviewForApplicant(applicant)) {
                interviews.add(i);
            }
        }

        return interviews;
    }

    /**
     * Returns interview(s) which are for the specified position.
     */
    public ArrayList<Interview> getPositionsInterview(Position position) {
        ArrayList<Interview> interviews = new ArrayList<>();

        for (Interview i : internalList) {
            if (i.isInterviewForPosition(position)) {
                interviews.add(i);
            }
        }

        return interviews;
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
     * Updates all interview containing instance of {@code positionToBeUpdated} to {@code newPosition}.
     * Effects of editing a Position cascades to update all instances of the old position to the edited position.
     */
    public void updatePositions(Position positionToBeUpdated, Position newPosition) {
        requireAllNonNull(positionToBeUpdated, newPosition);
        for (int i = 0; i < internalList.size(); i++) {
            Interview curr = internalList.get(i);
            if (curr.getPosition().equals(positionToBeUpdated)) {
                internalList.set(i, new Interview(curr.getApplicant(), curr.getDate(), newPosition));
            }
        }
    }

    /**
     * Updates all interview containing instance of {@code applicantToBeUpdated} to {@code newApplicant}.
     * Effects of editing an Applicant cascades to update all instances of the old applicant to the edited applicant.
     */
    public void updateApplicants(Applicant applicantToBeUpdated, Applicant newApplicant) {
        requireAllNonNull(applicantToBeUpdated, newApplicant);
        for (int i = 0; i < internalList.size(); i++) {
            Interview curr = internalList.get(i);
            if (curr.getApplicant().equals(applicantToBeUpdated)) {
                internalList.set(i, new Interview(newApplicant, curr.getDate(), curr.getPosition()));
            }
        }
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
