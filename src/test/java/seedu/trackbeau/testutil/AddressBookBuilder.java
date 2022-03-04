package seedu.trackbeau.testutil;

import seedu.trackbeau.model.TrackBeau;
import seedu.trackbeau.model.customer.Customer;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code TrackBeau ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private TrackBeau addressBook;

    public AddressBookBuilder() {
        addressBook = new TrackBeau();
    }

    public AddressBookBuilder(TrackBeau addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code TrackBeau} that we are building.
     */
    public AddressBookBuilder withPerson(Customer customer) {
        addressBook.addPerson(customer);
        return this;
    }

    public TrackBeau build() {
        return addressBook;
    }
}
