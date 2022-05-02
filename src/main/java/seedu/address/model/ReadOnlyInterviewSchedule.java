package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.interview.Interview;

//@@author lzan98
/**
 * Unmodifiable view of an interview list
 */
public interface ReadOnlyInterviewSchedule {
    /**
     * Returns an unmodifiable view of the interview list.
     * This list will not contain any duplicate interviews.
     */
    ObservableList<Interview> getInterviewList();
}
