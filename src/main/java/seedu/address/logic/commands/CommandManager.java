package seedu.address.logic.commands;
import java.util.Stack;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class CommandManager implements CommandManageable {
    private static final Logger logger = LogsCenter.getLogger(CommandManager.class);
    private int commandStackPointer = -1;
    private final Stack<Command> commandStack = new Stack<>();
    private final Model model;

    public CommandManager(Model modelManager) {
        this.model = modelManager;
    }

    public int getCommandStackPointer() {
        return commandStackPointer;
    }

    public Stack<Command> getCommandStack() {
        return commandStack;
    }

    public Model getModel() {
        return model;
    }

    @Override
    public CommandResult insertCommand(Command currentCommand) throws CommandException {
        CommandResult toReturn;
        try {
            //if undo
            if (currentCommand.isUndo()) {
                CommandResult undidCommand = undo();
                String undidMessage = undidCommand.getFeedbackToUser();
                boolean isShowHelp = undidCommand.isShowHelp();
                return new CommandResult("Undid command: " + undidMessage, isShowHelp, false);
            }
            //if redo
            if (currentCommand.isRedo()) {
                CommandResult redidCommand = redo();
                String redidMessage = redidCommand.getFeedbackToUser();
                boolean isShowHelp = redidCommand.isShowHelp();
                return new CommandResult("Redid command: " + redidMessage, isShowHelp, false);
            }
        } catch (Exception err) {
            //handle error
        }

        // else
        refreshFutureCommands(commandStackPointer);
        commandStack.push(currentCommand);
        toReturn = currentCommand.execute(model);
        commandStackPointer++;
        return toReturn;
    }

    @Override
    public void refreshFutureCommands(int undoRedoPointer) {
        logger.fine("Attempting to insert new command");
        if (commandStack.size() < 1) {
            return;
        }
        if (commandStack.size() > undoRedoPointer + 1) {
            commandStack.subList(undoRedoPointer + 1, commandStack.size()).clear();
        }
    }

    @Override
    public CommandResult undo() throws CommandException {
        logger.fine("Attempting to undo command");
        if (commandStackPointer < 0) {
            //i.e no past commands to execute
            return new CommandResult("There are no commands to undo!", false, false);
        }

        Command command = commandStack.get(commandStackPointer);
        commandStackPointer--;
        return command.unExecute(model);
    }

    @Override
    public CommandResult redo() throws CommandException {
        logger.fine("Attempting to redo command");
        if (commandStackPointer == commandStack.size() - 1) {
            //i.e no future commands to execute
            return new CommandResult("There are no commands to redo!", false, false);
        }

        commandStackPointer++;
        Command command = commandStack.get(commandStackPointer);
        System.out.println(command);
        return command.execute(model);
    }
}
