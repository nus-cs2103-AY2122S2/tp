package seedu.address.logic.commands;

import java.util.Stack;

/**
 * Solution adapted from 
 * https://github.com/AY1920S1-CS2103T-T09-4/main/pull/161/commits/094fbe26c9917afa2cf3e6a4e2fc5808166e4f79
 */

public class StackUndoRedo {

    private Stack<RedoableCommand> undoStack;
    private Stack<RedoableCommand> redoStack;

    /**
     * Pushes {@code command} onto the undo-stack if it is of type {@code UndoableCommand}. Clears the redo-stack
     * if {@code command} is not of type {@code UndoCommand} or {@code RedoCommand}.
     */
    public StackUndoRedo() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    /**
     * Pushes {@code command} onto the undo-stack if it is of type {@code UndoableCommand}. Clears the redo-stack
     * if {@code command} is not of type {@code UndoCommand} or {@code RedoCommand}.
     */
    public StackUndoRedo(StackUndoRedo otherUndoRedoStack) {
        undoStack = otherUndoRedoStack.undoStack;
        redoStack = otherUndoRedoStack.redoStack;
    }

    /**
     * Pushes {@code command} onto the undo-stack if it is of type {@code UndoableCommand}. Clears the redo-stack
     * if {@code command} is not of type {@code UndoCommand} or {@code RedoCommand}.
     */
    public void push(Command command) {
        if (!(command instanceof RedoableCommand)) {
            return;
        }

        if (!(command instanceof UndoCommand) && !(command instanceof RedoCommand)) {
            redoStack.clear();
        }


        undoStack.add((RedoableCommand) command);
    }

    /**
     * Pops and returns the next {@code UndoableCommand} to be undone in the stack.
     */
    public RedoableCommand popUndo() {
        RedoableCommand toUndo = undoStack.remove(0);
        redoStack.add(toUndo);
        return toUndo;
    }

    /**
     * Pops and returns the next {@code UndoableCommand} to be redone in the stack.
     */
    public RedoableCommand popRedo() {
        RedoableCommand toRedo = redoStack.remove(0);
        undoStack.add(toRedo);
        return toRedo;
    }

    /**
     * Returns true if can undo
     */
    public boolean canUndo() {
        return undoStack.size() > 0;
    }

    /**
     * Returns true if can redo
     */
    public boolean canRedo() {
        return redoStack.size() > 0;
    }

    /**
     *
     * @param other
     * @return boolean whether the command are the same
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof StackUndoRedo)) {
            return false;
        }

        StackUndoRedo stack = (StackUndoRedo) other;

        return undoStack.equals(stack.undoStack)
                && redoStack.equals(stack.redoStack);
    }
}
