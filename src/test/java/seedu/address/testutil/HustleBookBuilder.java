package seedu.address.testutil;

import seedu.address.model.HustleBook;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building HustleBook objects.
 * Example usage: <br>
 *     {@code HustleBook ab = new HustleBookBuilder().withPerson("John", "Doe").build();}
 */
public class HustleBookBuilder {

    private HustleBook hustleBook;

    public HustleBookBuilder() {
        hustleBook = new HustleBook();
    }

    public HustleBookBuilder(HustleBook hustleBook) {
        this.hustleBook = hustleBook;
    }

    /**
     * Adds a new {@code Person} to the {@code HustleBook} that we are building.
     */
    public HustleBookBuilder withPerson(Person person) {
        hustleBook.addPerson(person);
        return this;
    }

    public HustleBook build() {
        return hustleBook;
    }
}
