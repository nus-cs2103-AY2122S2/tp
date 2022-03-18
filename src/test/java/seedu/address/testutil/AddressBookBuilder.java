package seedu.address.testutil;

import seedu.address.model.HustleBook;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code HustleBook ab = new HustleBookBuilder().withPerson("John", "Doe").build();}
 */
public class HustleBookBuilder {

    private HustleBook addressBook;

    public HustleBookBuilder() {
        addressBook = new HustleBook();
    }

    public HustleBookBuilder(HustleBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code HustleBook} that we are building.
     */
    public HustleBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    public HustleBook build() {
        return addressBook;
    }
}
