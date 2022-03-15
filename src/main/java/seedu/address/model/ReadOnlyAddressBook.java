package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.entry.Company;
import seedu.address.model.entry.Event;
import seedu.address.model.entry.Person;

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
     * Returns an unmodifiable view of the companies list.
     * This list will not contain any duplicate companies.
     */
    ObservableList<Company> getCompanyList();

    ObservableList<Event> getEventList();

}
