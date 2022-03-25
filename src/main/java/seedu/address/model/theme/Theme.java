package seedu.address.model.theme;

import static java.util.Objects.requireNonNull;

import java.io.File;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import seedu.address.ui.UiManager;

/**
 * Represents a Theme of UNite.
 */
public abstract class Theme {
    protected final Stage stage = UiManager.getMainWindow().getPrimaryStage();
    protected final String extensionsResource = requireNonNull(getClass().getResource("/view/Extensions.css"))
            .toExternalForm();

    /**
     * Switch to the specified theme.
     */
    @FXML
    public abstract void applyTheme();

    /**
     * The current theme user is using.
     *
     * @return the current using theme
     */
    public Theme currentTheme() {
        File styleSheet = new File(stage.getScene().getStylesheets().get(0));
        if (styleSheet.getName().equals("LightTheme.css")) {
            return new LightTheme();
        }
        return new DarkTheme();
    }
}
