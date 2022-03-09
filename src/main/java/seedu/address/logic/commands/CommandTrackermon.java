package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelTrackermon;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class CommandTrackermon {

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code ModelTrackermon} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResultTrackermon execute(ModelTrackermon model) throws CommandException;

}
