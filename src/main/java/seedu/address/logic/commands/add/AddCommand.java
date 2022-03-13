package seedu.address.logic.commands.add;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds the specified data to the address book.
 */
public abstract class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
