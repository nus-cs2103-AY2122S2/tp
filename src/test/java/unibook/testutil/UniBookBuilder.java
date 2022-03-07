package unibook.testutil;

import unibook.model.UniBook;
import unibook.model.person.Person;

/**
 * A utility class to help with building UniBook objects.
 * Example usage: <br>
 * {@code UniBook ab = new UniBookBuilder().withPerson("John", "Doe").build();}
 */
public class UniBookBuilder {

    private UniBook uniBook;

    public UniBookBuilder() {
        uniBook = new UniBook();
    }

    public UniBookBuilder(UniBook uniBook) {
        this.uniBook = uniBook;
    }

    /**
     * Adds a new {@code Person} to the {@code UniBook} that we are building.
     */
    public UniBookBuilder withPerson(Person person) {
        uniBook.addPerson(person);
        return this;
    }

    public UniBook build() {
        return uniBook;
    }
}
