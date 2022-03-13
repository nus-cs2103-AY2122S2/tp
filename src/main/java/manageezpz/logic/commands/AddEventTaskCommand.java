package manageezpz.logic.commands;

import static java.util.Objects.requireNonNull;

import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.model.Model;
import manageezpz.model.task.Event;

public class AddEventTaskCommand extends Command {
    public static final String COMMAND_WORD = "addEvent";
    public static final String MESSAGE_USAGE = "add message usage for addEvent here";
    public static final String MESSAGE_SUCCESS = "New Event task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This Task already exists in the address book";

    private final Event toAdd;

    public AddEventTaskCommand(Event event) {
        toAdd = event;
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
