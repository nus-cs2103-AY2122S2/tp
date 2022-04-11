package unibook.ui.exceptions;

/**
 * Exception that is thrown when a button action has a exception that should not happen at all.
 */
public class ButtonActionException extends RuntimeException {
    public ButtonActionException() {
        super("An exception has occured with a button press");
    }
}
