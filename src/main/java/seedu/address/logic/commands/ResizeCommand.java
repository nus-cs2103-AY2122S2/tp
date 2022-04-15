package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Resizes the result display window.
 */
public class ResizeCommand extends Command {

    public static final String COMMAND_WORD = "resize";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Resizes the result display window.\n"
            + "Parameters: SIZE (must be 1, 2 or 3)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_RESIZE = "Result display window resized.";
    public static final int RESIZE_WINDOW_MULTIPLIER = 100;
    private static double resultWindowDisplaySize;

    public ResizeCommand(double displaySize) {
        this.resultWindowDisplaySize = displaySize;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_RESIZE, false, false, false, false, true);
    }

    public static double getResultWindowDisplaySize() {
        return resultWindowDisplaySize;
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ResizeCommand // instanceof handles nulls
                && resultWindowDisplaySize == (((ResizeCommand) other).resultWindowDisplaySize)); // state check
    }
}
