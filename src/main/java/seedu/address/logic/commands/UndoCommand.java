package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Undoes the previous command that commits to contact list.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Reverts the person list or task list to a previous state, before an "
            + "add/delete/edit person or newtask/deltask command was executed. \n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_EARLIEST_VERSION = "There are no versions before this";
    public static final String MESSAGE_SUCCESS = "Contents have been changed to previous version";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canUndo()) {
            throw new CommandException(MESSAGE_EARLIEST_VERSION);
        }

        model.undoContents();
        return new CommandResult(MESSAGE_SUCCESS);

    }

    @Override
    public boolean equals(Object other) {
        return other instanceof UndoCommand;
    }
}
