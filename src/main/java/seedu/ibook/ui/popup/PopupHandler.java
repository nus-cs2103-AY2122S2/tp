package seedu.ibook.ui.popup;

import seedu.ibook.model.product.Product;
import seedu.ibook.ui.MainWindow;

public class PopupHandler {

    private final PopupAdd popupAdd;
    private final PopupUpdate popupUpdate;
    private final PopupDelete popupDelete;

    /**
     * Initializes a {@code PopupHandler}.
     *
     * @param mainWindow The {@code MainWindow} that this component resides on.
     */
    public PopupHandler(MainWindow mainWindow) {
        popupAdd = new PopupAdd(mainWindow);
        popupUpdate = new PopupUpdate(mainWindow);
        popupDelete = new PopupDelete(mainWindow);
    }

    /**
     * Checks whether a popup is showing.
     *
     * @return True if a popup is showing, else false.
     */
    public boolean isShowing() {
        return popupAdd.isShowing()
                || popupUpdate.isShowing()
                || popupDelete.isShowing();
    }

    /**
     * Shows a feedback message to the user in the popup.
     *
     * @param message The feedback.
     */
    public void setFeedbackToUser(String message) {
        if (popupAdd.isShowing()) {
            popupAdd.setFeedbackToUser(message);
        } else if (popupUpdate.isShowing()) {
            popupUpdate.setFeedbackToUser(message);
        } else if (popupDelete.isShowing()) {
            popupDelete.setFeedbackToUser(message);
        }
    }

    /**
     * Show the popup window for adding product.
     */
    public void showPopupAdd() {
        if (popupAdd.isShowing()) {
            popupAdd.focus();
        } else {
            popupAdd.show();
        }
    }

    /**
     * Shows the popup window for updating product.
     */
    public void showPopupUpdate(int index, Product product) {
        if (popupUpdate.isShowing()) {
            popupUpdate.hide();
        }
        popupUpdate.show(index, product);
    }

    /**
     * Shows the popup window for deleting product.
     */
    public void showPopupDelete(int index, Product product) {
        if (popupDelete.isShowing()) {
            popupDelete.hide();
        }
        popupDelete.show(index, product);
    }

    /**
     * Hides the popup window.
     */
    public void hidePopup() {
        if (popupAdd.isShowing()) {
            popupAdd.hide();
        }
        if (popupUpdate.isShowing()) {
            popupUpdate.hide();
        }
        if (popupDelete.isShowing()) {
            popupDelete.hide();
        }
    }
}
