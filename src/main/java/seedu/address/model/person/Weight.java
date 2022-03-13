package seedu.address.model.person;

/**
 * Represents a Person's weight in the MyGM.
 * Currently hard coded - to be further developed.
 */
public class Weight {

    private String weight;
    public static final String MESSAGE_CONSTRAINTS = "Weights should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "1. The local-part should only contain numeric characters.\n"
            + "2. Weight should be between 0 and 400 (inclusive).\n";

    /**
     * Constructs a {@code Weight}.
     */
    public Weight() {
        this.weight = "80";
    }

    public Weight(String weight) {
        this.weight = weight;
    }

    public static boolean isValidWeight(String weightString) {
        int weight = -1;

        try {
            weight = Integer.parseInt(weightString);
        } catch (Exception e) {
            return false;
        }

        if (weight < 0 || weight > 400) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return this.weight + "kg";
    }
}
