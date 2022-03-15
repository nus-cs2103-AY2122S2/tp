package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Command undone";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.undoCommand();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
