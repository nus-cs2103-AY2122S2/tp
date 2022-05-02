package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.candidate.Candidate;

//@@author
/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withCandidate("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Candidate} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withCandidate(Candidate candidate) {
        addressBook.addCandidate(candidate);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
