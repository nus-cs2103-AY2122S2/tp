package seedu.address.ui;

import javafx.scene.layout.Region;

/**
 * Panel containing a list of something
 */
public abstract class ListPanel extends UiPart<Region> {
    /**
     * Creates a {@code ListPanel}
     *
     * @param fxml Provided FXML File
     */
    public ListPanel(String fxml) {
        super(fxml);
    }
}
