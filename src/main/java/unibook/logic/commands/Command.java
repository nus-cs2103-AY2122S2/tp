package unibook.logic.commands;

import unibook.logic.commands.exceptions.CommandException;
import unibook.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param model               {@code Model} which the command should operate on.
     * @param isPersonListShowing boolean value of whether person list is showing.
     * @param isModuleListShowing boolean value of whether module list is showing.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */

    // execute will delete the specified index of person or module depending on what is currently showing
    public abstract CommandResult execute(Model model,
                                          Boolean isPersonListShowing,
                                          Boolean isModuleListShowing,
                                          Boolean isGroupListShowing) throws CommandException;
}
