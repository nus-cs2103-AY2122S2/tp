package seedu.address.logic.commands.job;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

public class TabJob extends Command {
    public static final String COMMAND_WORD = "tabjob";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches to Job Tab.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_TABJOB_MESSAGE = "Switched to Job Tab.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_TABJOB_MESSAGE, false, true, true);
    }
}
