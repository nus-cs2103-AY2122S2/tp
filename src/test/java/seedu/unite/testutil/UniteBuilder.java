package seedu.unite.testutil;

import seedu.unite.model.Unite;
import seedu.unite.model.person.Person;

/**
 * A utility class to help with building Unite objects.
 * Example usage: <br>
 *     {@code Unite ab = new Unite().withPerson("John", "Doe").build();}
 */
public class UniteBuilder {

    private final Unite unite;

    public UniteBuilder() {
        unite = new Unite();
    }

    public UniteBuilder(Unite unite) {
        this.unite = unite;
    }

    /**
     * Adds a new {@code Person} to the {@code Unite} that we are building.
     */
    public UniteBuilder withPerson(Person person) {
        unite.addPerson(person);
        return this;
    }

    public Unite build() {
        return unite;
    }
}
