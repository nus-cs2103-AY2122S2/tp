package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Cancels the Reset command.
 */
public class CancelResetCommand extends ResetCommand {
    public static final String COMMAND_WORD = "no";
    public static final String COMMAND_NAME = "Cancel Reset";
    public static final String MESSAGE_SUCCESS = "Reset has been cancelled!";

    @Override
    public boolean equals(Object o) {
        // instanceof handles nulls
        if (!(o instanceof CancelResetCommand)) {
            return false;
        }

        // short circuit if same object
        if (this == o) {
            return true;
        }

        CancelResetCommand other = (CancelResetCommand) o;
        return this.toString().equals(other.toString()); // state check
    }

    @Override
    public String toString() {
        return COMMAND_NAME;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
