package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.record.Record;

public class ExpiredRecordCard extends UiPart<Region> {
    private static final String FXML = "ExpiredRecordCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    public final Record record;
    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label startDate;
    @FXML
    private Label endDate;

    /**
     * Creates a {@code RecordCode} with the given {@code record} and index to display.
     */
    public ExpiredRecordCard(Record record, int displayedIndex) {
        super(FXML);
        this.record = record;
        title.setText(record.getClientID().toString());
        startDate.setText("Start: " + record.getStartDate().toString());
        endDate.setText("End: " + record.getEndDate().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ExpiredRecordCard)) {
            return false;
        }

        // state check
        ExpiredRecordCard card = (ExpiredRecordCard) other;
        return title.getText().equals(card.title.getText())
                && record.equals(card.record);
    }
}
