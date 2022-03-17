package seedu.address.model.lineup.exceptions;

public class LineupNotFoundException extends RuntimeException {
    public LineupNotFoundException() {
        super("Could not find the lineup");
    }
}
