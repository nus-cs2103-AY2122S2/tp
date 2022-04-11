package seedu.ibook.ui;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import seedu.ibook.logic.commands.item.ExpiredCommand;
import seedu.ibook.logic.commands.product.OutOfStockCommand;

/**
 * The menu toolbar of the application.
 */
public class MenuToolbar extends UiComponent<MenuBar> {

    private static final String FXML = "MenuToolbar.fxml";

    private HelpWindow helpWindow;

    /**
     * Initializes a {@code MenuToolbar}.
     *
     * @param mainWindow The {@code MainWindow} that this component resides on.
     */
    MenuToolbar(MainWindow mainWindow) {
        super(FXML, mainWindow);

        this.helpWindow = new HelpWindow();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        getMainWindow().handleExit();
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Finds items that are expired.
     */
    @FXML
    private void handleExpired() {
        getMainWindow().executeCommand(ExpiredCommand.COMMAND_WORD);
    }

    /**
     * Finds products that are out of stock.
     */
    @FXML
    private void handleOutOfStock() {
        getMainWindow().executeCommand(OutOfStockCommand.COMMAND_WORD);
    }
}
