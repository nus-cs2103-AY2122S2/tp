package seedu.contax.ui;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class StatusBarFooter extends UiPart<Region> {

    private static final String FXML = "StatusBarFooter.fxml";

    @FXML
    private Label saveLocationStatus;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public StatusBarFooter(Path saveLocation) {
        super(FXML);
        setSaveLocation(saveLocation);
    }

    /**
     * Sets the content of the {@code saveLocationStatus} label.
     *
     * @param saveLocation The path that should be displayed in the footer.
     */
    public void setSaveLocation(Path saveLocation) {
        saveLocationStatus.setText(Paths.get(".").resolve(saveLocation).toString());
    }

}