package seedu.address.ui;

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
    private static Path defaultPath;
    private static Path archivePath;
    private static Path saveLocation;
    private static boolean isArchive;
    @FXML
    private Label saveLocationStatus;


    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}, and stores a reference to
     * the defaultPath and archivePath.
     */
    public StatusBarFooter(Path saveLocation, Path defaultPath, Path archivePath) {
        super(FXML);
        this.defaultPath = defaultPath;
        this.archivePath = archivePath;
        this.saveLocation = saveLocation;
        isArchive = false;
        saveLocationStatus.setText(Paths.get(".").resolve(saveLocation).toString());
    }


    public String getSaveLocationStatusText() {
        return saveLocationStatus.getText();
    }

    /**
     * Changes the text for {@code StatusBarFooter} with to the correct {@code Path}.
     */
    public String swapPaths() {
        String currentPathString = getSaveLocationStatusText();
        String archivePathString = Paths.get(".").resolve(archivePath).toString();
        String defaultPathString = Paths.get(".").resolve(defaultPath).toString();

        if (currentPathString.equals(archivePathString)) {
            saveLocationStatus.setText(defaultPathString);
            isArchive = false;
            return "default";
        } else if (currentPathString.equals(defaultPathString)) {
            saveLocationStatus.setText(archivePathString);
            isArchive = true;
            return "archives";
        }

        return "";
    }

    /**
     * Provides a way to check if we are currently in the archive book or not.
     * @return true if we are on the archived book.
     */
    public static boolean isArchiveBook() {
        return isArchive;
    }

}
