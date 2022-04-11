package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.TAssist;

/**
 * Clears the TAssist.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "TAssist has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTAssist(new TAssist());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
