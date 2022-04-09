package seedu.unite.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.unite.model.Model;
import seedu.unite.model.Unite;

/**
 * Clears the UNite.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "UNite has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setUnite(new Unite());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
