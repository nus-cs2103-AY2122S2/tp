package seedu.address.logic.commands.exceptions;

/**
 * An error that occurs when getInfoPanelType is called when updateInfoPanel is false in {@code CommandResult}.
 * This should never be thrown unless there's a programming mistake.
 */
public class NoInfoPanelTypeException extends RuntimeException {
    public NoInfoPanelTypeException(String message) {
        super(message);
    }

    public NoInfoPanelTypeException() {
        super();
    }
}
