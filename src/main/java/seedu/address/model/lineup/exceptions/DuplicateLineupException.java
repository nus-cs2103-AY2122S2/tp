package seedu.address.model.lineup.exceptions;

public class DuplicateLineupException extends RuntimeException {
    DuplicateLineupException() {
        super("Operation would result in duplicate lineups");
    }
}

