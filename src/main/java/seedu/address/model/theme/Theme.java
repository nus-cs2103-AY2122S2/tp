package seedu.address.model.theme;

import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Represents a Theme of UNite.
 */
public abstract class Theme {
    /**
     * Switch to the specified theme.
     */
    @FXML
    public abstract void applyTheme(Stage stage);
}
