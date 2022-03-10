package seedu.address.model.person;

/**
 * Represents a Person's weight in the MyGM.
 * Currently hard coded - to be further developed.
 */
public class Weight {
    int weight;

    /**
     * Constructs a {@code Weight}
     */
    public Weight() {
        this.weight = 80;
    }

    @Override
    public String toString() {
        return this.weight + "kg";
    }
}
