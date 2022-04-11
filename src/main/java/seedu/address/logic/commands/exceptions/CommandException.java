package seedu.address.logic.commands.exceptions;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.misc.ViewTab;

/**
 * Represents an error which occurs during execution of a {@link Command}.
 */
public class CommandException extends Exception {
    /** The application should switch tab to selected tab. **/
    private final ViewTab viewTab;

    /**
     * Constructs a {@code CommandException} with unspecified fields set to their default values.
     */
    public CommandException(String message) {
        super(message);
        this.viewTab = ViewTab.NONE;
    }

    /**
     * Constructs a {@code CommandException} with the specified {@code viewTab}
     */
    public CommandException(String message, ViewTab viewTab) {
        super(message);
        requireNonNull(viewTab);
        this.viewTab = viewTab;
    }

    /**
     * Constructs a new {@code CommandException} with the specified detail {@code message} and {@code cause}.
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
        this.viewTab = ViewTab.NONE;
    }

    public ViewTab toggleTo() {
        return viewTab;
    }
}
