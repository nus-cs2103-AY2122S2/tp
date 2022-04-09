package seedu.unite.testutil;

import seedu.unite.model.Unite;
import seedu.unite.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code Unite ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private Unite unite;

    public AddressBookBuilder() {
        unite = new Unite();
    }

    public AddressBookBuilder(Unite unite) {
        this.unite = unite;
    }

    /**
     * Adds a new {@code Person} to the {@code Unite} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        unite.addPerson(person);
        return this;
    }

    public Unite build() {
        return unite;
    }
}
