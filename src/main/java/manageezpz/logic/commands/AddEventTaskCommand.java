package manageezpz.logic.commands;

import static java.util.Objects.requireNonNull;
import static manageezpz.logic.parser.CliSyntax.*;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TIME;

import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.model.Model;
import manageezpz.model.task.Event;

public class AddEventTaskCommand extends Command {
    public static final String COMMAND_WORD = "addEvent";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an Event Task to the address book. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_TIME + "[DATE] [START_TIME] [END_TIME] "
            + "\r\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "Finish 160 Resins "
            + PREFIX_TIME + "2022-03-15 18:00 20:00";
    public static final String MESSAGE_SUCCESS = "New Event task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This Task already exists in the address book";

    private Event toAdd;

    public AddEventTaskCommand(Event event) {
        toAdd = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addEvent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
