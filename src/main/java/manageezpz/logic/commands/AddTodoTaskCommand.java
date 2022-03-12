package manageezpz.logic.commands;


import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.model.task.Task;
import manageezpz.model.Model;

import static java.util.Objects.requireNonNull;
import static manageezpz.logic.parser.CliSyntax.*;

public class AddTodoTaskCommand extends Command {
    public static final String COMMAND_WORD = "addTask";
    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Add Task command not implemented yet";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an Task to the address book. "
            + "Parameters: "
            + PREFIX_TODO + " "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TODO + " "
            + PREFIX_DESCRIPTION + "Play Genshin Impact.";

    public static final String MESSAGE_SUCCESS = "New Task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This Task already exists in the address book";

    private final Task toAdd;

    /**
     * Creates an AddEmployeeCommand to add the specified {@code Person}
     */
    public AddTodoTaskCommand(Task task) {
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
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
