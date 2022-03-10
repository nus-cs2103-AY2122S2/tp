package seedu.address.model.person;

/**
 * Represents a Person's height in the MyGM.
 * Currently hard coded - to be further developed.
 */
public class Height {

    int height;

    /**
     * Constructs a {@code Height}
     */
    public Height() {
        this.height = 180;
    }

    @Override
    public String toString() {
        return this.height + "cm";
    }
}
