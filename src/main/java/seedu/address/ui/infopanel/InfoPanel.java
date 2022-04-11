package seedu.address.ui.infopanel;

import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * Information Panel showing detailed information of something.
 */
public abstract class InfoPanel extends UiPart<Region> {

    /**
     * Creates a {@code InfoPanel}.
     *
     * @param fxml Provided FXML File.
     */
    public InfoPanel(String fxml) {
        super(fxml);
    }
}
