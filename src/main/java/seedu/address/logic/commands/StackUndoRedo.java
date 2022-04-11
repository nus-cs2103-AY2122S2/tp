package seedu.address.logic.commands;

import java.util.Stack;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

/**
 * @@author zhixianggg reused
 * Solution adapted from
 * https://github.com/AY1920S1-CS2103T-T09-4/main/pull/161/commits/094fbe26c9917afa2cf3e6a4e2fc5808166e4f79
 */

public class StackUndoRedo {
    private static final Logger logger = LogsCenter.getLogger(StackUndoRedo.class);
    private Stack<RedoableCommand> undoStack;
    private Stack<RedoableCommand> redoStack;

    /**
     * StackUndoRedo Class to enable storing of commands LIFO
     */
    public StackUndoRedo() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    /**
     * Pushes {@code command} onto the undo-stack if it is of type {@code UndoableCommand}. Clears the redo-stack
     * if {@code command} is not of type {@code UndoCommand} or {@code RedoCommand}.
     */
    public void push(Command command) {

        if (!(command instanceof UndoCommand) && !(command instanceof RedoCommand)) {
            redoStack.clear();
        }

        if (!(command instanceof RedoableCommand)) {
            return;
        }

        undoStack.add((RedoableCommand) command);
    }

    /**
     * Pops and returns the next {@code UndoableCommand} to be undone in the stack.
     */
    public RedoableCommand popUndo() {
        RedoableCommand toUndo = undoStack.pop();
        redoStack.push(toUndo);
        logger.info(this.toString());
        return toUndo;
    }

    /**
     * Pops and returns the next {@code UndoableCommand} to be redone in the stack.
     */
    public RedoableCommand popRedo() {
        RedoableCommand toRedo = redoStack.pop();
        undoStack.push(toRedo);
        logger.info(this.toString());
        return toRedo;
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

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Undo Stack: \n");
        for (RedoableCommand command : undoStack) {
            s.append(command.getClass().getSimpleName()).append(" ");
        }
        s.append("\nRedo Stack: \n");
        for (RedoableCommand command : redoStack) {
            s.append(command.getClass().getSimpleName()).append(" ");
        }
        return s.toString();
    }
}
