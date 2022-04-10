package seedu.trackermon.logic.commands.exceptions;

import seedu.trackermon.logic.commands.Command;

/**
 * Represents an error which occurs during execution of a {@link Command}.
 */
public class CommandException extends Exception {
    /**
     * Constructs a new {@code CommandException} with the specified detail {@code message}.
     * @param message the error message to display.
     */
    public CommandException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code CommandException} with the specified detail {@code message} and {@code cause}.
     * @param message the error message to display.
     * @param cause the cause of main exception.
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
