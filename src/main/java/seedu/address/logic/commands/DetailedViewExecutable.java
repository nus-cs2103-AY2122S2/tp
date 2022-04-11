package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents the ability to execute on Model when a Person is in DetailedContactView
 */
public interface DetailedViewExecutable {
    /**
     * Executes the command in the detailed view context and returns the result. Commands that
     * execute with this method should operate on the Person in detailed view and return
     * a {@code CommandResult} that has the {@code SpecialCommandResult} of {@code DETAILED_VIEW},
     * unless it is a command that changes the view.
     *
     * @param model {@code} Model that the command should operate on.
     * @return feedback message of the operation result to display.
     * @throws CommandException If an error occurs during command execution.
     */
    CommandResult executeInDetailedView(Model model) throws CommandException;
}
