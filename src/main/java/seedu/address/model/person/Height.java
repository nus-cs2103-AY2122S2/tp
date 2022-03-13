package seedu.address.model.person;

/**
 * Represents a Person's height in the MyGM.
 * Currently hard coded - to be further developed.
 */
public class Height {

    public static final String MESSAGE_CONSTRAINTS = "Heights should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "1. The local-part should only contain numeric characters.\n"
            + "2. Height should be between 0 and 300 (inclusive).\n";

    private String height;

    /**
     * Constructs a {@code Height}.
     */
    public Height() {
        this.height = "180";
    }

    public Height(String height) {
        this.height = height;
    }

    /**
     * Checks if the given height is valid.
     *
     * @param heightString Input height.
     * @return True if the height is valid.
     */
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
