package seedu.address.logic.commands;

import seedu.address.model.Model;

public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_UNDO_ACKNOWLEDGEMENT = "Successfully undone";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_UNDO_ACKNOWLEDGEMENT, false, false, true);
    }
}
