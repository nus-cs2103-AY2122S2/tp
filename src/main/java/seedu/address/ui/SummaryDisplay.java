package seedu.address.ui;

import static java.util.Objects.requireNonNull;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * UI for medical card
 */
public class SummaryDisplay extends UiPart<Region> {

    private static final String FXML = "SummaryDisplay.fxml";

    @FXML
    private TextArea summaryDisplay;

    public SummaryDisplay() {
        super(FXML);
    }

    public void setSummary(String summary) {
        requireNonNull(summary);
        summaryDisplay.setText(summary);
    }
}