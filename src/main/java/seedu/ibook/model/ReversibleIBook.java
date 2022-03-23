package seedu.ibook.model;

import java.util.List;
import java.util.function.Consumer;

import seedu.ibook.model.exceptions.AtLatestStateException;
import seedu.ibook.model.exceptions.AtOldestStateException;
import seedu.ibook.model.product.Product;

/**
 * An IBook that can be reversed to previous state as well as restored to previously undone state.
 */
public class ReversibleIBook extends IBook {

    private final StateChangeRecorder stateChangeRecorder;

    /**
     * Creates an ReversibleIBook using the Products in the {@code toBeCopied}
     */
    public ReversibleIBook(ReadOnlyIBook toBeCopied) {
        super(toBeCopied);

        stateChangeRecorder = new StateChangeRecorder();
    }

    /**
     * Saves the changes made to iBook.
     */
    public void saveChanges() {
        stateChangeRecorder.saveStateChange();
    }

    /**
     * Checks if the current state can be undone.
     */
    public boolean canUndo() {
        return stateChangeRecorder.hasOlderStateChange();
    }

    /**
     * Checks if there is any undone state that can be redone.
     */
    public boolean canRedo() {
        return stateChangeRecorder.hasNewerStateChange();
    }

    /**
     * Reverts the state of IBook to previous state.
     */
    public void undo() {
        if (!canUndo()) {
            throw new AtOldestStateException();
        }

        List<Consumer<IBook>> reverseActionList = stateChangeRecorder.getCurrentReverseActionList();
        reverseActionList.forEach(action -> action.accept(this));
        stateChangeRecorder.revertStateChange();
    }

    /**
     * Restores the previously undone state of IBook.
     */
    public void redo() {
        if (!canRedo()) {
            throw new AtLatestStateException();
        }

        stateChangeRecorder.restoreStateChange();
        List<Consumer<IBook>> forwardActionList = stateChangeRecorder.getCurrentForwardActionList();
        forwardActionList.forEach(action -> action.accept(this));
    }

    /// reversible versions of parent class methods

    /**
     * A reversible version of parent's {@code addProduct} method.
     */
    public void reversibleAddProduct(Product product) {
        addProduct(product);
        stateChangeRecorder.addForwardAction(iBook -> iBook.addProduct(product));
        stateChangeRecorder.addReverseAction(iBook -> iBook.removeProduct(product));
    }

    /**
     * A reversible version of parent's {@code removeProduct} method.
     */
    public void reversibleRemoveProduct(Product product) {
        removeProduct(product);
        stateChangeRecorder.addForwardAction(iBook -> iBook.removeProduct(product));
        stateChangeRecorder.addReverseAction(iBook -> iBook.addProduct(product));
    }

    /**
     * A reversible version of parent's {@code setProduct} method.
     */
    public void reversibleSetProduct(Product target, Product updatedProduct) {
        setProduct(target, updatedProduct);
        stateChangeRecorder.addForwardAction(iBook -> iBook.setProduct(target, updatedProduct));
        stateChangeRecorder.addReverseAction(iBook -> iBook.setProduct(updatedProduct, target));
    }

    @Override
    public String toString() {
        return "Reversible " + super.toString();
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other)
                && other instanceof ReversibleIBook
                && this.stateChangeRecorder.equals(((ReversibleIBook) other).stateChangeRecorder);
    }
}
