package seedu.unite.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.unite.model.Model;
import seedu.unite.model.Unite;

/**
 * Clears the UNite.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears all data in UNite (including profiles and tags). "
            + "To clear UNite, enter 'clear' without any spaces or characters before and behind.\n"
            + "To clear empty tags, enter 'clear_emptytag'.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setUnite(new Unite());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        return (other instanceof ClearCommand);
    }
}
