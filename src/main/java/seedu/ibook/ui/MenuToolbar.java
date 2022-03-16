package seedu.ibook.ui;

import javafx.scene.control.MenuBar;

/**
 * The menu toolbar of the application.
 */
public class MenuToolbar extends UiComponent<MenuBar> {

    private static final String FXML = "MenuToolbar.fxml";

    /**
     * Initializes a {@code MenuToolbar}.
     *
     * @param mainWindow The {@code MainWindow} that this component resides on.
     */
    MenuToolbar(MainWindow mainWindow) {
        super(FXML, mainWindow);
    }

}
