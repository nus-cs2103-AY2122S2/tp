package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_TASK_DESCRIPTION;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Add a task to the system.
 */
public class AddTaskCommand extends Command {
    public static final String COMMAND_WORD = "addt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add a task with description and/or deadline.\n"
            + "Format: addt d/DESCRIPTION [t/DEADLINE (dd/mm/yyyy)]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_ADD_TASK_DESCRIPTION
            + "Swimming lesson " + PREFIX_ADD_TASK_DEADLINE + "03/05/2022";

    public static final String MESSAGE_ARGUMENTS = "Task added.\n" + "Description: %1$s\n" + "Deadline: %2$s";

    public static final String MESSAGE_NO_DESCRIPTION = "Task description not found!";

    private final String description;
    private final String deadline;

    /**
     * Initializes a AddTaskCommand with the given description. The deadline is not set.
     */
    public AddTaskCommand(String description) {
        requireAllNonNull(description);

        this.description = description;
        this.deadline = "No deadline set";
    }

    /**
     * Initializes a AddTaskCommand with the given description and deadline.
     */
    public AddTaskCommand(String description, String deadline) {
        requireAllNonNull(description, deadline);

        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);

        if (this.description.equals("")) {
            // missing description, prompt the user with the format
            throw new CommandException(MESSAGE_NO_DESCRIPTION + "\n" + MESSAGE_USAGE);
        }

        model.addTask(new Task(description, deadline));

        // Printing out the current items in task list to make sure the method work, should be removed later on
        System.out.println(model.getTaskList().getTaskList().toString());

        return new CommandResult(String.format(MESSAGE_ARGUMENTS, description, deadline));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTaskCommand)) {
            return false;
        }

        // state check
        AddTaskCommand e = (AddTaskCommand) other;
        return description.equals(e.description);
    }
}
