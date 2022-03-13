package seedu.address.logic.commands.list;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

public abstract class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public abstract CommandResult execute(Model model);
}
