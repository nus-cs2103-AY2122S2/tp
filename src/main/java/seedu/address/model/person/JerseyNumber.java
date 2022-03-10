package seedu.address.model.person;

/**
 * Represents a Person's jersey number in the MyGM.
 * Currently hard coded - to be further developed.
 */
public class JerseyNumber {
    int jerseyNumber;

    /**
     * Constructs a {@code JerseyNumber}
     */
    public JerseyNumber() {
        this.jerseyNumber = 23;
    }

    @Override
    public String toString() {
        return String.valueOf(this.jerseyNumber);
    }
}
