package seedu.address.ui.listpanel;

import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * Panel containing a list of something.
 */
public abstract class ListPanel extends UiPart<Region> {
    /**
     * Creates a {@code ListPanel}.
     *
     * @param fxml Provided FXML File.
     */
    public ListPanel(String fxml) {
        super(fxml);
    }
}
