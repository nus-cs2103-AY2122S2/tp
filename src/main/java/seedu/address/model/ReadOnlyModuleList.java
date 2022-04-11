package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Mod;

/**
 * Unmodifiable view of a module list
 */
public interface ReadOnlyModuleList {

    /**
     * Returns an unmodifiable view of the module list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Mod> getModList();

}

