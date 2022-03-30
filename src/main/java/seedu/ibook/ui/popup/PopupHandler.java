package seedu.ibook.ui.popup;

import seedu.ibook.model.item.Item;
import seedu.ibook.model.product.Product;
import seedu.ibook.ui.MainWindow;
import seedu.ibook.ui.popup.item.PopupAddItem;
import seedu.ibook.ui.popup.item.PopupManageItem;

public class PopupHandler {

    private final PopupAddProduct popupAddProduct;
    private final PopupUpdateProduct popupUpdateProduct;
    private final PopupDeleteProduct popupDeleteProduct;
    private final PopupAddItem popupAddItem;
    private final PopupManageItem popupManageItem;

    private Popup showing;

    /**
     * Initializes a {@code PopupHandler}.
     *
     * @param mainWindow The {@code MainWindow} that this component resides on.
     */
    public PopupHandler(MainWindow mainWindow) {
        popupAddProduct = new PopupAddProduct(mainWindow);
        popupUpdateProduct = new PopupUpdateProduct(mainWindow);
        popupDeleteProduct = new PopupDeleteProduct(mainWindow);
        popupAddItem = new PopupAddItem(mainWindow);
        popupManageItem = new PopupManageItem(mainWindow);
    }

    /**
     * Checks whether a popup is showing.
     *
     * @return True if a popup is showing, else false.
     */
    public boolean isShowing() {
        return showing != null;
    }

    /**
     * Shows a feedback message to the user in the popup.
     *
     * @param message The feedback.
     */
    public void setFeedbackToUser(String message) {
        showing.setFeedbackToUser(message);
    }

    /**
     * Show the popup window for adding product.
     */
    public void showPopupAddProduct() {
        hidePopup();
        popupAddProduct.show();
        showing = popupAddProduct;
    }

    /**
     * Shows the popup window for updating product.
     */
    public void showPopupUpdateProduct(int index, Product product) {
        hidePopup();
        popupUpdateProduct.show(index, product);
        showing = popupUpdateProduct;
    }

    /**
     * Shows the popup window for deleting product.
     */
    public void showPopupDeleteProduct(int index, Product product) {
        hidePopup();
        popupDeleteProduct.show(index, product);
        showing = popupDeleteProduct;
    }

    /**
     * Shows the popup window for adding item.
     */
    public void showPopupAddItem(int index, Product product) {
        hidePopup();
        popupAddItem.show(index, product);
        showing = popupAddItem;
    }

    /**
     * Shows the popup window for managing item.
     */
    public void showPopupManageItem(int productIndex, int itemIndex,
                                    Product product, Item item) {
        hidePopup();
        popupManageItem.show(productIndex, itemIndex, product, item);
        showing = popupManageItem;
    }

    /**
     * Hides the popup window.
     */
    public void hidePopup() {
        if (showing != null) {
            showing.hide();
        }
        showing = null;
    }
}
