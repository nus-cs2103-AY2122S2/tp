package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * UI for summary display
 */
public class SummaryDisplay extends UiPart<Region> {
    private static final String SCREEN_TITLE = "Summary";
    private static final String FXML = "SummaryDisplay.fxml";

    @FXML
    private Label screenTitle;

    @FXML
    private TextArea summaryDisplay;

    /**
     * UI for summary
     */
    public SummaryDisplay() {
        super(FXML);
        screenTitle.setText(SCREEN_TITLE);
    }

    public void setSummary(String summary) {
        requireNonNull(summary);
        summaryDisplay.setText(summary);
    }
}
