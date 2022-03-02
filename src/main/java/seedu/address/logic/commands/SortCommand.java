package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * A command class that deals with sorting command
 * Currently it can only sort according to names alphebetically.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.sortFilteredPersonList();
        return new CommandResult("Name sorted alphabetically");
    }
}
