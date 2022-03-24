package seedu.address.model.theme;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;

/**
 * Represents a Dark Theme.
 */
public class DarkTheme extends Theme {
    private final String darkThemeResource = requireNonNull(getClass().getResource("/view/DarkTheme.css"))
            .toExternalForm();

    public DarkTheme() {}

    /**
     * Switch to the dark theme.
     */
    @Override
    @FXML
    public void applyTheme() {
        this.stage.getScene().getStylesheets().clear();
        this.stage.getScene().getStylesheets().add(this.darkThemeResource);
        this.stage.getScene().getStylesheets().add(this.extensionsResource);
    }

    @Override
    public String toString() {
        return "Dark Theme";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DarkTheme;
    }
}
