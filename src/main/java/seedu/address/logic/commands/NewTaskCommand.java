package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tasks.Task;

/**
 * Adds a task to the task list.
 */
public class NewTaskCommand extends Command {

    public static final String COMMAND_WORD = "newtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the task manager.\n"
            + "Parameters: DESCRIPTION (must be non-empty) "
            + PREFIX_DEADLINE + "DEADLINE \n"
            + "Example: " + COMMAND_WORD + " Submit CS2100 Assignment 1 "
            + PREFIX_DEADLINE + "2022-03-25T23:59";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task manager";

    private final Task toAdd;

    /**
     * Creates a NewTaskCommand to add the specified {@code task}
     */
    public NewTaskCommand(Task task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(toAdd);
        model.commitContent();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NewTaskCommand // instanceof handles nulls
                && toAdd.equals(((NewTaskCommand) other).toAdd));
    }
}
