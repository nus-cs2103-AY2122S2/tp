package seedu.unite.ui.theme;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import seedu.unite.ui.AddProfileWindow;
import seedu.unite.ui.AddTagWindow;

/**
 * Represents a Dark Theme.
 */
public class DarkTheme extends Theme {
    private final String darkThemeResource = requireNonNull(getClass().getResource("/view/DarkTheme.css"))
            .toExternalForm();
    private final String darkExtensionsResource = requireNonNull(getClass()
            .getResource("/view/ExtensionsDark.css")).toExternalForm();
    private final String darkAddTagWindow = requireNonNull(getClass()
            .getResource("/view/AddTagWindowDark.css")).toExternalForm();
    private final String darkAddProfileWindow = requireNonNull(getClass()
            .getResource("/view/AddProfileWindowDark.css")).toExternalForm();

    public DarkTheme() {}

    /**
     * Switch to the dark theme.
     */
    @Override
    @FXML
    public void applyTheme(Stage stage, AddTagWindow addTagWindow, AddProfileWindow addProfileWindow) {
        stage.getScene().getStylesheets().clear();
        stage.getScene().getStylesheets().add(this.darkThemeResource);
        stage.getScene().getStylesheets().add(this.darkExtensionsResource);
        addTagWindow.getSecondaryStage().getScene().getStylesheets().clear();
        addTagWindow.getSecondaryStage().getScene().getStylesheets().add(this.darkAddTagWindow);
        addProfileWindow.getSecondaryStage().getScene().getStylesheets().clear();
        addProfileWindow.getSecondaryStage().getScene().getStylesheets().add(this.darkAddProfileWindow);
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
                && darkExtensionsResource.equals(((DarkTheme) other).darkExtensionsResource)
                && darkAddTagWindow.equals(((DarkTheme) other).darkAddTagWindow)
                && darkAddProfileWindow.equals(((DarkTheme) other).darkAddProfileWindow));
    }
}
