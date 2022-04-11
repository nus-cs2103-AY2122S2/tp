package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public abstract class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " [p] to clear all applicants"
            + " or [i] to clear all interviews" + " or [t] to clear all tasks";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

}
