package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.UniqueInterviewList;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class InterviewSchedule implements ReadOnlyInterviewSchedule {
    private final UniqueInterviewList interviews;

    public InterviewSchedule() {
        interviews = new UniqueInterviewList();
    }

    public InterviewSchedule(UniqueInterviewList interviews) {
        this.interviews = interviews;
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public InterviewSchedule(ReadOnlyInterviewSchedule toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setInterviews(List<Interview> interviews) {
        this.interviews.setInterviews(interviews);
    }

    /**
     * Resets the existing data of this {@code InterviewSchedule} with {@code newData}.
     */
    public void resetData(ReadOnlyInterviewSchedule newData) {
        requireNonNull(newData);

        setInterviews(newData.getInterviewList());
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasInterview(Interview interview) {
        requireNonNull(interview);
        return interviews.contains(interview);
    }

    public boolean hasConflictingInterview(Interview interview) {
        requireNonNull(interview);
        return interviews.containsConflictingInterview(interview);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addInterview(Interview interview) {
        interviews.add(interview);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setInterview(Interview target, Interview editedInterview) {
        requireNonNull(editedInterview);

        interviews.setInterview(target, editedInterview);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeInterview(Interview key) {
        interviews.remove(key);
    }

    @Override
    public String toString() {
        return interviews.asUnmodifiableObservableList().size() + " interviews";
        // TODO: refine later
    }

    @Override
    public ObservableList<Interview> getInterviewList() {
        return interviews.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Interview // instanceof handles nulls
                && interviews.equals(((InterviewSchedule) other).interviews));
    }

    @Override
    public int hashCode() {
        return interviews.hashCode();
    }
}
