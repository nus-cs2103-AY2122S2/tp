package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public abstract boolean equals(Object other);

}
