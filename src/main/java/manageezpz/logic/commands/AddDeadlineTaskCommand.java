package manageezpz.logic.commands;

import static java.util.Objects.requireNonNull;

import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.model.Model;
import manageezpz.model.task.Deadline;

public class AddDeadlineTaskCommand extends Command {
    public static final String COMMAND_WORD = "addDeadline";
    public static final String MESSAGE_USAGE = "add message usage for addDeadline here";
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
