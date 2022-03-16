package seedu.address.model.lineup.lineupExceptions;

public class DuplicateLineupException extends RuntimeException {
    DuplicateLineupException() {
        super("Operation would result in duplicate lineups");
    }
}

