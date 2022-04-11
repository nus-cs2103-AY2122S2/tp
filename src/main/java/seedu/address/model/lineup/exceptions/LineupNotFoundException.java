package seedu.address.model.lineup.exceptions;

/**
 * Represents an error when the target lineup could not be found
 */
public class LineupNotFoundException extends RuntimeException {
    public LineupNotFoundException() {
        super("Could not find the lineup");
    }
}
