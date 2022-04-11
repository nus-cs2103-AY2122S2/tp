package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.application.Application;

/**
 * Unmodifiable view of InternApply's memory
 */
public interface ReadOnlyInternApplyMemory {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate applications.
     */
    ObservableList<Application> getApplicationList();

}
