package seedu.address.model.theme;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import seedu.address.ui.AddProfileWindow;
import seedu.address.ui.AddTagWindow;

/**
 * Represents a Theme of UNite.
 */
public abstract class Theme {
    /**
     * Switch to the specified theme, either dark or light.
     */
    @FXML
    public abstract void applyTheme(Stage stage, AddTagWindow addTagWindow, AddProfileWindow addProfileWindow);
}
