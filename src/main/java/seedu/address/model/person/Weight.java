package seedu.address.model.person;

/**
 * Represents a Person's weight in the MyGM.
 * Currently hard coded - to be further developed.
 */
public class Weight {

    private String weight;

    /**
     * Constructs a {@code Weight}.
     */
    public Weight() {
        this.weight = "80";
    }

    public Weight(String weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return this.weight + "kg";
    }
}
