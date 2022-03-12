package seedu.address.logic.commands.delete;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes the specified data from the address book.
 */
public abstract class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
