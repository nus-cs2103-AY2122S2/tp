package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;

public class UndoRedoStorage {

    private static int undoRedoLimit = 5;

    private List<AddressBook> undoList;
    private Stack<AddressBook> redoStack;

    private final Logger logger = LogsCenter.getLogger(UndoRedoStorage.class);

    /**
     * Constructs a {@code UndoRedoStorage}.
     */
    public UndoRedoStorage() {
        undoList = new ArrayList<>();
        redoStack = new Stack<>();
    }

    private void resize() {
        while (undoList.size() > undoRedoLimit) {
            logger.info("Undo-able list updated");
            undoList.remove(0);
        }

        while (redoStack.size() > undoRedoLimit) {
            logger.info("Redo-able stack updated");
            redoStack.remove(0);
        }
    }

    /**
     * Resets the stack storing Redo-able AddressBook states.
     */
    public void resetRedoStack() {
        logger.info("Redo-able stack reset");
        redoStack = new Stack<>();
    }

    /**
     * Adds an address book to a list for storage.
     */
    public void addToUndo(AddressBook addressBook) {
        logger.info("New entry in Undo-able list");
        undoList.add(addressBook);
        resize();
    }

    /**
     * Undoes the previous command and returns the old {@code AddressBook}.
     */
    public AddressBook undo() throws CommandException {
        if (undoList.isEmpty() || undoList.size() == 1) {
            logger.log(Level.WARNING, "Current AddressBook is not undo-able");
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
            logger.log(Level.WARNING, "Current AddressBook is not redo-able");
            throw new CommandException("No previous command to redo");
        }

        AddressBook addressBook = redoStack.pop();
        undoList.add(addressBook);
        resize();

        return addressBook;
    }

}
