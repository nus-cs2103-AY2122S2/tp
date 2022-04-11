package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_SUCCESS = "Command redone";

    private final Logger logger = LogsCenter.getLogger(RedoCommand.class);

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.redoCommand();
        logger.info("AddressBook successfully redone");
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
