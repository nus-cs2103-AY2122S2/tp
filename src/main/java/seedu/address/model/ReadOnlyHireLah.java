package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.interview.Interview;
import seedu.address.model.position.Position;

/**
 * Unmodifiable view of an HireLah
 */
public interface ReadOnlyHireLah {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Applicant> getApplicantList();

    /**
     * Returns an unmodifiable view of the interview list.
     * This list will not contain any duplicate interviews.
     */
    ObservableList<Interview> getInterviewList();


    /**
     * Returns an unmodifiable view of the position list.
     * This list will not contain any duplicate positions.
     */
    ObservableList<Position> getPositionList();
}
