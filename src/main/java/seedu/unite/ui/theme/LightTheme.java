package seedu.unite.ui.theme;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import seedu.unite.ui.AddProfileWindow;
import seedu.unite.ui.AddTagWindow;

/**
 * Represents a Light Theme.
 */
public class LightTheme extends Theme {
    private final String lightThemeResource = requireNonNull(getClass().getResource("/view/LightTheme.css"))
            .toExternalForm();
    private final String lightExtensionsResource = requireNonNull(getClass()
            .getResource("/view/ExtensionsLight.css")).toExternalForm();
    private final String lightAddTagWindow = requireNonNull(getClass()
            .getResource("/view/AddTagWindowLight.css")).toExternalForm();
    private final String lightAddProfileWindow = requireNonNull(getClass()
            .getResource("/view/AddProfileWindowLight.css")).toExternalForm();

    public LightTheme() {}

    /**
     * Switch to light theme.
     */
    @Override
    @FXML
    public void applyTheme(Stage stage, AddTagWindow addTagWindow, AddProfileWindow addProfileWindow) {
        stage.getScene().getStylesheets().clear();
        stage.getScene().getStylesheets().add(this.lightThemeResource);
        stage.getScene().getStylesheets().add(this.lightExtensionsResource);
        addTagWindow.getSecondaryStage().getScene().getStylesheets().clear();
        addTagWindow.getSecondaryStage().getScene().getStylesheets().add(this.lightAddTagWindow);
        addProfileWindow.getSecondaryStage().getScene().getStylesheets().clear();
        addProfileWindow.getSecondaryStage().getScene().getStylesheets().add(this.lightAddProfileWindow);
    }

    @Override
    public String toString() {
        return "Light Theme";
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof LightTheme
                && lightThemeResource.equals(((LightTheme) other).lightThemeResource)
                && lightExtensionsResource.equals(((LightTheme) other).lightExtensionsResource)
                && lightAddTagWindow.equals(((LightTheme) other).lightAddTagWindow)
                && lightAddProfileWindow.equals(((LightTheme) other).lightAddProfileWindow));
    }
}
