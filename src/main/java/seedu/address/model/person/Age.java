package seedu.address.model.person;

/**
 * Represents a Person's age in the MyGM.
 * Currently hard coded - to be further developed.
 */
public class Age {
    int age;

    /**
     * Constructs a {@code Age}
     */
    public Age() {
        this.age = 23;
    }

    @Override
    public String toString() {
        return this.age + "y/o";
    }
}
