package seedu.address.model.theme;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Represents a Dark Theme.
 */
public class DarkTheme extends Theme {
    private final String darkThemeResource = requireNonNull(getClass().getResource("/view/DarkTheme.css"))
            .toExternalForm();
    private final String darkExtensionsResource = requireNonNull(getClass()
            .getResource("/view/ExtensionsDark.css")).toExternalForm();

    public DarkTheme() {}

    /**
     * Switch to the dark theme.
     */
    @Override
    @FXML
    public void applyTheme(Stage stage) {
        stage.getScene().getStylesheets().clear();
        stage.getScene().getStylesheets().add(this.darkThemeResource);
        stage.getScene().getStylesheets().add(this.darkExtensionsResource);
    }

    @Override
    public String toString() {
        return "Dark Theme";
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DarkTheme
                && darkThemeResource.equals(((DarkTheme) other).darkThemeResource)
                && darkExtensionsResource.equals(((DarkTheme) other).darkExtensionsResource));
    }
}
