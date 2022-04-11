package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ViewTaskCommand extends Command {

    public static final String COMMAND_WORD = "viewt";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";

    public static final String MESSAGE_NO_TASK = "No tasks found!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getTaskList().size() == 0) {
            // No tasks
            throw new CommandException(MESSAGE_NO_TASK);
        }

        String message = model.viewTask();
        return new CommandResult(MESSAGE_SUCCESS + "\n" + message);
    }
}
