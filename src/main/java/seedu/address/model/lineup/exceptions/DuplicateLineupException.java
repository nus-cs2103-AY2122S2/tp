package seedu.address.model.lineup.exceptions;

/**
 * Represents an error when same Lineup is created more than once
 */
public class DuplicateLineupException extends RuntimeException {
    DuplicateLineupException() {
        super("Operation would result in duplicate lineups");
    }
}

