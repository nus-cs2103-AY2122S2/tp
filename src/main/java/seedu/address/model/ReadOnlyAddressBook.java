package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;
import seedu.address.model.person.Person;
import seedu.address.model.prescription.Prescription;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();
    ObservableList<Prescription> getPrescriptionList();

    /**
     * Returns an unmodifiable view of the contacts list.
     * This list will not contain any duplicate persons.
     * @return
     */
    ObservableList<Contact> getContactList();
}
