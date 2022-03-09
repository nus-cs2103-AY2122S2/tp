package seedu.contax.model;

import javafx.collections.ObservableList;
import seedu.contax.model.person.Person;
import seedu.contax.model.tag.Tag;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    ObservableList<Tag> getTagList();

}
