package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Resets the recipe book.
 */
public class ResetCommand extends Command {
    public static final String COMMAND_WORD = "reset";
    public static final String MESSAGE_CONFIRMATION_REQUIRED = "Are you sure you want to reset the recipe book back "
            + "to the default preloaded recipes? \n\n"
            + "Enter 'yes' if you wish to reset\n'no' if you do not wish to reset";
    public static final String MESSAGE_USAGE =
            "Example: " + COMMAND_WORD
             + "\nExample: " + COMMAND_WORD + " -f (Forced Reset)\n";


    @Override
    public boolean equals(Object o) {
        // instanceof handles nulls
        if (!(o instanceof ResetCommand)) {
            return false;
        }

        // short circuit if same object
        if (this == o) {
            return true;
        }

        ResetCommand other = (ResetCommand) o;
        return this.toString().equals(other.toString()); // state check
    }

    @Override
    public String toString() {
        return COMMAND_WORD;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_CONFIRMATION_REQUIRED);
    }
}
