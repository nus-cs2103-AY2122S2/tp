package seedu.address.model.person;

/**
 * Represents a Person's age in the MyGM.
 * Currently hard coded - to be further developed.
 */
public class Age {
    String age;

    /**
     * Constructs a {@code Age}.
     * Hardcoded, to be further developed.
     */
    public Age() {
        this.age = "23";
    }

    public Age(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return this.age + "y/o";
    }
}
