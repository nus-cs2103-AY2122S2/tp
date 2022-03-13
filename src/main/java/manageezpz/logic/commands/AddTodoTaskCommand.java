package manageezpz.logic.commands;

import static java.util.Objects.requireNonNull;
import static manageezpz.logic.parser.CliSyntax.*;

import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.model.Model;
import manageezpz.model.task.Task;



public class AddTodoTaskCommand extends Command {
    public static final String COMMAND_WORD = "addTodo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Todo Task to the address book. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "\r\n"
            + "Example: " + COMMAND_WORD + " "
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
