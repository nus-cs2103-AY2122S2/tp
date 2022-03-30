package seedu.address.ui.general;

import javafx.scene.layout.Region;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import seedu.address.ui.UiPart;

public class GrabResult extends UiPart<Region> {
    private static final String FXML = "GrabResult.fxml";

    @FXML
    private TextArea grabResult;

    /**
     * Creates a {@code GrabResult}.
     */
    public GrabResult() {
        super(FXML);
    }

    public void setResult(String result) {
        grabResult.setEditable(true);
        grabResult.setText(result);
    }
}
