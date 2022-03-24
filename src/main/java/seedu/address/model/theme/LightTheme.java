package seedu.address.model.theme;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;

/**
 * Represents a Light Theme.
 */
public class LightTheme extends Theme {
    private final String lightThemeResource = requireNonNull(getClass().getResource("/view/LightTheme.css"))
            .toExternalForm();

    public LightTheme() {}

    /**
     * Switch to light theme.
     */
    @Override
    @FXML
    public void applyTheme() {
        this.stage.getScene().getStylesheets().clear();
        this.stage.getScene().getStylesheets().add(this.lightThemeResource);
        this.stage.getScene().getStylesheets().add(this.extensionsResource);
    }

    @Override
    public String toString() {
        return "Light Theme";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof LightTheme;
    }
}
