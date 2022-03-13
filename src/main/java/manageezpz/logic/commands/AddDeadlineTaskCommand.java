package manageezpz.logic.commands;

import static java.util.Objects.requireNonNull;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DATETIME;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.model.Model;
import manageezpz.model.task.Deadline;

public class AddDeadlineTaskCommand extends Command {
    public static final String COMMAND_WORD = "addDeadline";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Deadline Task to the address book. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_DATETIME + "DATETIME "
            + "\r\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "Do Daily Commissions "
            + PREFIX_DATETIME + "2022-03-15 04:00";
    public static final String MESSAGE_SUCCESS = "New Deadline task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This Task already exists in the address book";

    private Deadline toAdd;

    public AddDeadlineTaskCommand(Deadline deadline) {
        toAdd = deadline;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addDeadline(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
