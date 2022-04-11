package seedu.unite.model;

import javafx.collections.ObservableList;
import seedu.unite.model.person.Person;
import seedu.unite.model.tag.Tag;

/**
 * Unmodifiable view of an unite
 */
public interface ReadOnlyUnite {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the tags list.
     * This list will not contain any duplicate tags.
     */
    ObservableList<Tag> getTagList();
}
