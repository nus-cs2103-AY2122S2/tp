package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;

public class UndoRedoStorage {

    private static int undoRedoLimit = 5;

    private List<AddressBook> undoList;
    private Stack<AddressBook> redoStack;

    /**
     * Constructs a {@code UndoRedoStorage}.
     */
    public UndoRedoStorage() {
        undoList = new ArrayList<>();
        redoStack = new Stack<>();
    }


    private void resize() {
        while (undoList.size() > undoRedoLimit) {
            undoList.remove(0);
        }

        while (redoStack.size() > undoRedoLimit) {
            redoStack.remove(0);
        }
    }

    public void resetRedoStack() {
        redoStack = new Stack<>();
    }

    /**
     * Adds an address book to a list for storage.
     */
    public void addToUndo(AddressBook addressBook) {
        undoList.add(addressBook);
        resize();
    }

    /**
     * Undoes the previous command and returns the old {@code AddressBook}.
     */
    public AddressBook undo() throws CommandException {
        if (undoList.isEmpty() || undoList.size() == 1) {
            throw new CommandException("No previous command to undo");
        }

        AddressBook currAddressBook = undoList.get(undoList.size() - 1);
        redoStack.add(currAddressBook);
        AddressBook prevAddressBook = undoList.get(undoList.size() - 2);
        undoList.remove(undoList.size() - 1);
        resize();

        return prevAddressBook;
    }

    /**
     * Redoes the previous command and returns the old {@code AddressBook}.
     */
    public AddressBook redo() throws CommandException {
        if (redoStack.isEmpty()) {
            throw new CommandException("No previous command to redo");
        }

        AddressBook addressBook = redoStack.pop();
        undoList.add(addressBook);
        resize();

        return addressBook;
    }

}
