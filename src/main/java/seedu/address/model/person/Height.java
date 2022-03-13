package seedu.address.model.person;

/**
 * Represents a Person's height in the MyGM.
 * Currently hard coded - to be further developed.
 */
public class Height {

    private String height;
    public static final String MESSAGE_CONSTRAINTS = "Heights should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "1. The local-part should only contain numeric characters.\n"
            + "2. Height should be between 0 and 300 (inclusive).\n";

    /**
     * Constructs a {@code Height}.
     */
    public Height() {
        this.height = "180";
    }

    public Height(String height) {
        this.height = height;
    }

    public static boolean isValidHeight(String heightString) {
        int height = -1;

        try {
            height = Integer.parseInt(heightString);
        } catch (Exception e) {
            return false;
        }

        if (height < 0 || height > 300) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return this.height + "cm";
    }
}
