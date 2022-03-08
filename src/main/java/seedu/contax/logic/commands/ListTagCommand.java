package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.model.Model;
import seedu.contax.ui.ListContentType;

/**
 * Lists all tags in the address book to the user.
 */
public class ListTagCommand extends Command {
    public static final String COMMAND_WORD = "listtags";

    public static final String MESSAGE_SUCCESS = "Listed all tags";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, ListContentType.TAG);
    }
}
