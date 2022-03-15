package seedu.ibook.ui;

import javafx.scene.control.MenuBar;

/**
 * The menu toolbar of the application.
 */
public class MenuToolbar extends UiPart<MenuBar> {

    private static final String FXML = "MenuToolbar.fxml";

    /**
     * Initializes a {@code MenuToolbar}.
     */
    MenuToolbar() {
        super(FXML);
    }

}
