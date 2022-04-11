package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.module.Module;

/**
 * Unmodifiable view of LinkyTime data
 */
public interface ReadOnlyLinkyTime {

    /**
     * Returns an unmodifiable view of the meeting list.
     * This list will not contain any duplicate meetings.
     */
    ObservableList<Meeting> getMeetingList();

    /**
     * Returns an unmodifiable view of the module list.
     * This list will not contain any duplicate modules.
     */
    ObservableList<Module> getModuleList();
}
