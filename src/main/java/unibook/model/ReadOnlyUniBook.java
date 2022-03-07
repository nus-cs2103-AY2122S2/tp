package unibook.model;

import javafx.collections.ObservableList;
import unibook.model.module.Module;
import unibook.model.person.Person;

/**
 * Unmodifiable view of an UniBook
 */
public interface ReadOnlyUniBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the modules list.
     * This list will not contain any duplicate modules.
     */
    ObservableList<Module> getModuleList();


}
