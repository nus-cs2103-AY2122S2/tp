package seedu.address.model.person;

/**
 * Represents a Person's jersey number in the MyGM.
 * Currently hard coded - to be further developed.
 */
public class JerseyNumber {

    private String jerseyNumber;

    /**
     * Constructs a {@code JerseyNumber}.
     */
    public JerseyNumber() {
        this.jerseyNumber = "23";
    }

    public JerseyNumber(String jerseryNumber) {
        this.jerseyNumber = jerseryNumber;
    }

    @Override
    public String toString() {
        return this.jerseyNumber;
    }
}
