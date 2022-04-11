package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEADER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFORMATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.Type.TASK;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Type;
import seedu.address.model.Model;
import seedu.address.model.tasks.Task;

/**
 * Adds a task to the address book.
 */
public class AddTaskCommand extends AddCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " [t] : Adds a task to the list of tasks.\n"
            + "Parameters: "
            + PREFIX_HEADER + "HEADER "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME "
            + PREFIX_INFORMATION + "INFORMATION\n"
            + "Example: " + COMMAND_WORD + " [t] "
            + PREFIX_HEADER + "Update interview statuses "
            + PREFIX_DATE + "2021-05-06 "
            + PREFIX_TIME + "05:29 "
            + PREFIX_INFORMATION + "Update all interview statuses to ACCEPTED";

    public static final String MESSAGE_SUCCESS = "Added Task: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task list";

    public final Task toAdd;

    /**
     * Constructor for AddTaskCommand.
     * @param task Task to be added
     */
    public AddTaskCommand(Task task) {
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
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), getType());
    }

    @Override
    public Type getType() {
        return TASK;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && toAdd.equals(((AddTaskCommand) other).toAdd));
    }
}
