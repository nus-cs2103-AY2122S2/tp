package seedu.address.logic.commands;

import java.util.Stack;

/**
 * @@author zhixianggg reused
 * Solution adapted from
 * https://github.com/AY1920S1-CS2103T-T09-4/main/pull/161/commits/094fbe26c9917afa2cf3e6a4e2fc5808166e4f79
 */

public class StackUndoRedo {
    private Stack<RedoableCommand> undoStack;
    private Stack<String> commandUndoStack;

    private Stack<RedoableCommand> redoStack;
    private Stack<String> commandRedoStack;

    /**
     * StackUndoRedo Class to enable storing of commands FIFO
     */
    public StackUndoRedo() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();

        commandRedoStack = new Stack<>();
        commandUndoStack = new Stack<>();
    }

    /**
     * Pushes {@code command} onto the undo-stack if it is of type {@code UndoableCommand}. Clears the redo-stack
     * if {@code command} is not of type {@code UndoCommand} or {@code RedoCommand}.
     */
    public void push(Command command, String commandName) {
        if (!(command instanceof UndoCommand) && !(command instanceof RedoCommand)) {
            redoStack.clear();
            commandRedoStack.clear();
        }

        if (!(command instanceof RedoableCommand)) {
            return;
        }
        commandUndoStack.add(commandName);
        undoStack.add((RedoableCommand) command);
    }

    /**
     * Pops and returns the next {@code UndoableCommand} to be undone in the stack.
     */
    public modelStack popUndo() {
        RedoableCommand toUndo = undoStack.pop();
        String commandText = commandUndoStack.pop();

        redoStack.push(toUndo);
        commandRedoStack.push(commandText);
        return new modelStack(commandText, toUndo);
    }

    /**
     * Pops and returns the next {@code UndoableCommand} to be redone in the stack.
     */
    public modelStack popRedo() {
        RedoableCommand toRedo = redoStack.pop();
        String commandText = commandRedoStack.pop();

        undoStack.push(toRedo);
        commandUndoStack.push(commandText);
        return new modelStack(commandText, toRedo);
    }

    /**
     * Returns true if there are more commands that can be undone.
     */
    public boolean canUndo() {
        return !undoStack.empty();
    }

    /**
     * Returns true if there are more commands that can be redone.
     */
    public boolean canRedo() {
        return !redoStack.empty();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StackUndoRedo)) {
            return false;
        }

        StackUndoRedo stack = (StackUndoRedo) other;

        // state check
        return undoStack.equals(stack.undoStack)
                && redoStack.equals(stack.redoStack);
    }

    final class modelStack {
        private final String commandText;
        private final RedoableCommand redoableCommand;

        public modelStack(String commandText, RedoableCommand redoableCommand) {
            this.commandText = commandText;
            this.redoableCommand = redoableCommand;
        }

        public String getCommandText() {
            return commandText;
        }

        public RedoableCommand getRedoableCommand() {
            return redoableCommand;
        }
    }
}
