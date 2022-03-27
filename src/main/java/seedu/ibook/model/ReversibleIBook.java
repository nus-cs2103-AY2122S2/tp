package seedu.ibook.model;

import java.util.Collections;
import java.util.List;

import seedu.ibook.model.exceptions.AtLatestStateException;
import seedu.ibook.model.exceptions.AtOldestStateException;
import seedu.ibook.model.item.Item;
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
     * Prepares iBook for changes.
     */
    public void prepareForChanges() {
        stateChangeRecorder.prepareNewStateChange();
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

        List<ReversibleIBookAction> actionList = stateChangeRecorder.getCurrentActionList();
        Collections.reverse(actionList);
        actionList.forEach(action -> action.performBackwardAction(this));
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
        List<ReversibleIBookAction> actionList = stateChangeRecorder.getCurrentActionList();
        actionList.forEach(action -> action.performForwardAction(this));
    }

    /// reversible versions of parent class methods

    /**
     * A reversible version of parent's {@code resetData} method.
     */
    public void reversibleResetData(ReadOnlyIBook newData) {
        stateChangeRecorder.recordAction(new ReversibleResetDataAction(new IBook(this), newData));
        resetData(newData);
    }

    /**
     * A reversible version of parent's {@code addProduct} method.
     */
    public void reversibleAddProduct(Product product) {
        stateChangeRecorder.recordAction(new ReversibleAddProductAction(product));
        addProduct(product);
    }

    /**
     * A reversible version of parent's {@code setProduct} method.
     */
    public void reversibleSetProduct(Product target, Product updatedProduct) {
        stateChangeRecorder.recordAction(new ReversibleSetProductAction(target, updatedProduct));
        setProduct(target, updatedProduct);
    }

    /**
     * A reversible version of parent's {@code removeProduct} method.
     */
    public void reversibleRemoveProduct(Product product) {
        stateChangeRecorder.recordAction(new ReversibleRemoveProductAction(product));
        removeProduct(product);
    }

    /**
     * A reversible version of parent's {@code addItem} method.
     */
    public void reversibleAddItem(Product product, Item item) {
        stateChangeRecorder.recordAction(new ReversibleAddItemAction(product, item));
        addItem(product, item);
    }

    /**
     * A reversible version of parent's {@code setItem} method.
     */
    public void reversibleSetItem(Product targetProduct, Item targetItem, Item updatedItem) {
        stateChangeRecorder.recordAction(new ReversibleSetItemAction(targetProduct, targetItem, updatedItem));
        setItem(targetProduct, targetItem, updatedItem);
    }

    /**
     * A reversible version of parent's {@code removeItem} method.
     */
    public void reversibleRemoveItem(Product product, Item item) {
        stateChangeRecorder.recordAction(new ReversibleRemoveItemAction(product, item));
        removeItem(product, item);
    }

    @Override
    public String toString() {
        return "Reversible " + super.toString();
    }
}
