package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Clears the recipe book.
 */
public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_CONFIRMATION_REQUIRED = "Are you sure you want to clear the recipe book?\n\n"
            + "Enter 'yes' if you wish to clear \n'no' if you do not wish to clear.";
    public static final String MESSAGE_USAGE =
            "Example: " + COMMAND_WORD
            + "\nExample: " + COMMAND_WORD + " -f (Forced Clear)\n";


    @Override
    public boolean equals(Object o) {
        // instanceof handles nulls
        if (!(o instanceof ClearCommand)) {
            return false;
        }

        // short circuit if same object
        if (this == o) {
            return true;
        }

        ClearCommand other = (ClearCommand) o;
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
