package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.candidate.Candidate;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.UniqueInterviewList;

public class InterviewSchedule implements ReadOnlyInterviewSchedule {
    private final UniqueInterviewList interviews;

    {
        interviews = new UniqueInterviewList();
    }

    public InterviewSchedule() {

    }

    /**
     * Creates an InterviewSchedule using the Interviews in the {@code toBeCopied}
     */
    public InterviewSchedule(ReadOnlyInterviewSchedule toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the interview list with {@code interviews}.
     * {@code interviews} must not contain interviews with duplicate candidates or conflicting time slots.
     */
    public void setInterviews(List<Interview> interviews) {
        this.interviews.setInterviews(interviews);
    }

    /**
     * Reorders the contents of the interview list with the earliest upcoming
     * interview first followed by later interviews, by creating a copy of the original interview list.
     * {@code candidates} must not contain duplicate interview objects.
     */
    public void sortInterviews() {
        List<Interview> interviewsCopy = new ArrayList<Interview>(this.getInterviewList());
        Comparator<Interview> comparatorDateTime =
                Comparator.comparing(l -> l.getInterviewDateTime());
        interviewsCopy.sort(comparatorDateTime);
        this.setInterviews(interviewsCopy);
    }

    /**
     * Resets the existing data of this {@code InterviewSchedule} with {@code newData}.
     */
    public void resetData(ReadOnlyInterviewSchedule newData) {
        requireNonNull(newData);
        setInterviews(newData.getInterviewList());
    }

    /**
     * Returns true if an interview with the same candidate as {@code interview} exists in the interview schedule.
     */
    public boolean hasCandidate(Interview interview) {
        requireNonNull(interview);
        return interviews.containsSameCandidate(interview);
    }

    /**
     * Returns true if an interview with the same date and time slot as {@code interview}
     * exists in the interview schedule.
     */
    public boolean hasConflictingInterview(Interview interview) {
        requireNonNull(interview);
        return interviews.containsConflictingInterview(interview);
    }

    /**
     * Adds an interview to the interview schedule.
     * The interview must not already exist in the interview schedule.
     */
    public void addInterview(Interview interview) {
        interviews.add(interview);
    }
    /**
     * Sets the target interview to the new editedInterview.
     */
    public void setInterview(Interview target, Interview editedInterview) throws CommandException {
        requireNonNull(editedInterview);

        interviews.setInterview(target, editedInterview);
    }

    /**
     * Deletes past interviews from the list if the scheduled interview time is 31 minutes ago.
     * @param currentDateTime Current date time.
     */
    public List<Interview> deletePastInterviews(LocalDateTime currentDateTime) {
        LocalDateTime currentDateTimeMinusThirty = currentDateTime.minusMinutes(30);

        List<Interview> list = new ArrayList<>();
        for (Interview i: getInterviewList()) {
            if (i.getInterviewDateTime().isBefore(currentDateTimeMinusThirty)) {
                list.add(i);
            }
        }
        for (Interview i: list) {
            removeInterview(i);
        }

        return list;
    }

    /**
     * Updates the candidate of the target interview without checking for conflicting time slots.
     */
    public void updateInterviewCandidate(Interview target, Interview editedInterview) {
        interviews.updateInterviewCandidate(target, editedInterview);
    }

    /**
     * Removes the interview from the list.
     */
    public void removeInterview(Interview key) {
        interviews.remove(key);
    }

    @Override
    public String toString() {
        return interviews.asUnmodifiableObservableList().size() + " interviews";
        // TODO: refine later
    }
    /**
     * Returns an ObservableList of interviews.
     */
    @Override
    public ObservableList<Interview> getInterviewList() {
        return interviews.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InterviewSchedule // instanceof handles nulls
                && interviews.equals(((InterviewSchedule) other).interviews));
    }

    @Override
    public int hashCode() {
        return interviews.hashCode();
    }

    /**
     * Returns a list of candidates whose interviews have expired.
     */
    public List<Candidate> getExpiredInterviewCandidates() {
        return interviews.getExpiredInterviewCandidates();
    }
}
