package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;

/**
 * Bordered container that displays a piece of information below its corresponding label.
 */
public class InfoBox extends UiPart<Region> {
    private static final String FXML = "InfoBox.fxml";
    private final Logger logger = LogsCenter.getLogger(InfoBox.class);

    @FXML
    private Label label;

    @FXML
    private Label value;

    /**
     * Creates an {@code InfoBox} with the given {@code label} and {@value}.
     */
    public InfoBox(String label, String value) {
        super(FXML);
        this.label.setText(label);
        this.value.setText(value);
    }
}
