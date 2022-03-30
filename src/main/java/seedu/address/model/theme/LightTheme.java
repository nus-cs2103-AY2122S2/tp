package seedu.address.model.theme;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Represents a Light Theme.
 */
public class LightTheme extends Theme {
    private final String lightThemeResource = requireNonNull(getClass().getResource("/view/LightTheme.css"))
            .toExternalForm();
    private final String lightExtensionsResource = requireNonNull(getClass()
            .getResource("/view/ExtensionsLight.css")).toExternalForm();

    public LightTheme() {}

    /**
     * Switch to light theme.
     */
    @Override
    @FXML
    public void applyTheme(Stage stage) {
        stage.getScene().getStylesheets().clear();
        stage.getScene().getStylesheets().add(this.lightThemeResource);
        stage.getScene().getStylesheets().add(this.lightExtensionsResource);
    }

    @Override
    public String toString() {
        return "Light Theme";
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof LightTheme
                && lightThemeResource.equals(((LightTheme) other).lightExtensionsResource)
                && lightExtensionsResource.equals(((LightTheme) other).lightExtensionsResource));
    }
}
