package seedu.address.ui;

import javafx.scene.layout.Region;

/**
 * Abstract class for all types of panels.
 */
public abstract class ListPanel extends UiPart<Region> {
    /**
     * Creates a {@code ListPanel} abstract class.
     */
    public ListPanel(String fxml) {
        super(fxml);
    }

}

