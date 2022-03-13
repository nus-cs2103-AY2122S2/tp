package seedu.address.model.person;

/**
 * Represents a Person's jersey number in the MyGM.
 * Currently hard coded - to be further developed.
 */
public class JerseyNumber {

    public static final String MESSAGE_CONSTRAINTS = "Jersey numbers should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "1. The local-part should only contain numeric characters.\n"
            + "2. Jersey number should be between 0 and 100 (inclusive).\n";

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

    /**
     * Checks if the given jersey number is valid.
     */
    public static boolean isValidJerseyNumber(String jerseyNumberString) {
        int jerseyNumber = -1;

        try {
            jerseyNumber = Integer.parseInt(jerseyNumberString);
        } catch (Exception e) {
            return false;
        }

        if (jerseyNumber < 0 || jerseyNumber > 400) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return this.jerseyNumber;
    }
}
