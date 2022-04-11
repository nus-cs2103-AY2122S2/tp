package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;

public interface CommandManageable {
    CommandResult insertCommand(Command currentCommand) throws CommandException;
    void refreshFutureCommands(int undoRedoPointer);
    CommandResult undo() throws CommandException;
    CommandResult redo() throws CommandException;
}
