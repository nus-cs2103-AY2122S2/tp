package seedu.address.logic.commands;

import java.util.Stack;

/**
 * @@author zhixianggg reused
 * Solution adapted from
 * https://github.com/AY1920S1-CS2103T-T09-4/main/pull/161/commits/094fbe26c9917afa2cf3e6a4e2fc5808166e4f79
 */

public class StackUndoRedo {
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

        if (isNotRedoableCommand(command)) {
            return;
        }

        if (isNotUndoRedoCommand(command)) {
            redoStack.clear();
        }

        undoStack.add((RedoableCommand) command);
    }

    /**
     * Returns true if the command is not redoable or undoable.
     */
    public boolean isNotRedoableCommand(Command command) {
        if (!(command instanceof RedoableCommand)) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the command is either an undo command or a redo command.
     */
    public boolean isNotUndoRedoCommand(Command command) {
        if (!(command instanceof UndoCommand) && !(command instanceof RedoCommand)) {
            return true;
        }
        return false;
    }

    /**
     * Pops and returns the next {@code UndoableCommand} to be undone in the stack.
     */
    public RedoableCommand popUndo() {
        RedoableCommand toUndo = undoStack.pop();
        redoStack.push(toUndo);
        return toUndo;
    }

    /**
     * Pops and returns the next {@code RedoableCommand} to be redone in the stack.
     */
    public RedoableCommand popRedo() {
        RedoableCommand toRedo = redoStack.pop();
        undoStack.push(toRedo);
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
}
