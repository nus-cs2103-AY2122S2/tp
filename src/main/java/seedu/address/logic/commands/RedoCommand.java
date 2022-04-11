package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Reverts the person list or task list to a later "
            + "state, which exists after an undo command was executed.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_LATEST_VERSION = "This is the latest version";
    public static final String MESSAGE_SUCCESS = "Contents have been changed to the next version";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canRedo()) {
            throw new CommandException(MESSAGE_LATEST_VERSION);
        }

        model.redoContents();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof UndoCommand;
    }
}
