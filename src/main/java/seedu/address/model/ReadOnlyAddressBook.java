package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.person.Person;
import seedu.address.model.tamodule.TaModule;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the modules list.
     * This list will not contain any duplicate modules.
     */
    ObservableList<TaModule> getModuleList();

    /**
     * Returns an unmodifiable view of the classGroups list.
     * This list will not contain any duplicate classGroups.
     */
    ObservableList<ClassGroup> getClassGroupList();

}
