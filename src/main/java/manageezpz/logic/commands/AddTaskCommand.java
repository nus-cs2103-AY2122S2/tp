package manageezpz.logic.commands;

import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.model.Model;

public class AddTaskCommand extends Command {
    public static final String COMMAND_WORD = "addTask";
    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Add Task command not implemented yet";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_NOT_IMPLEMENTED_YET);
    }
}
