package seedu.contax.testutil;

import seedu.contax.model.AddressBook;
import seedu.contax.model.person.Person;
import seedu.contax.model.tag.Tag;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
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
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    /**
     * Adds a new {@code Tag} to the {@code AddressBook} that we are building.
     * @param tag The specified tag to add to the address book.
     * @return The updated AddressBookBuilder with the specified tag.
     */
    public AddressBookBuilder withTag(Tag tag) {
        addressBook.addTag(tag);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
