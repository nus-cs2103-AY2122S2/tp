package seedu.address.model.lineup.lineupExceptions;

public class LineupNotFoundException extends RuntimeException {
    public LineupNotFoundException() {
        super("Could not find the lineup");
    }
}
