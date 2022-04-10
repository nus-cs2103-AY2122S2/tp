package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.record.Record;

public class RecordDetailCard extends UiPart<Region> {

    private static final String FXML = "RecordDetailCard.fxml";

    public final Record record;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label datetime;

    /**
     * Creates a {@code RecordCode} with the given {@code Record} and index to display.
     */
    public RecordDetailCard(Record record, int displayedIndex) {
        super(FXML);
        this.record = record;
        id.setText(displayedIndex + ". " + record.getClientID());
        title.setText("Insurance type: " + record.getInsuranceID());
        datetime.setText("Valid from " + record.getStartDate() + " to " + record.getEndDate());

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecordDetailCard)) {
            return false;
        }

        // state check
        RecordDetailCard card = (RecordDetailCard) other;
        return id.getText().equals(card.id.getText())
                && record.equals(card.record);
    }
}
