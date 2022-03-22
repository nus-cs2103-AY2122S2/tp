package seedu.ibook.ui;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;

/**
 * The menu toolbar of the application.
 */
public class MenuToolbar extends UiComponent<MenuBar> {

    private static final String FXML = "MenuToolbar.fxml";

    private MainWindow mainWindow;
    private HelpWindow helpWindow;

    /**
     * Initializes a {@code MenuToolbar}.
     *
     * @param mainWindow The {@code MainWindow} that this component resides on.
     */
    MenuToolbar(MainWindow mainWindow) {
        super(FXML, mainWindow);

        this.mainWindow = mainWindow;
        this.helpWindow = new HelpWindow();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        mainWindow.handleExit();
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
}
