package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.record.Record;

public class SmallRecordCard extends UiPart<Region> {

    private static final String FXML = "SmallRecordListCard.fxml";
    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    public final Record record;
    @FXML
    private HBox cardPane2;
    @FXML
    private Label title2;
    @FXML
    private Label id2;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code RecordCode} with the given {@code Record} and index to display.
     */
    public SmallRecordCard(Record record, int displayedIndex) {
        super(FXML);
        this.record = record;
        title2.setText("Insurance type: " + record.getInsuranceID());

        tags.getChildren().add(new Label("Start: " + record.getStartDate().toString()));
        tags.getChildren().add(new Label("End: " + record.getEndDate().toString()));
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SmallRecordCard)) {
            return false;
        }

        // state check
        SmallRecordCard card = (SmallRecordCard) other;
        return title2.getText().equals(card.title2.getText())
                && record.equals(card.record);
    }
}
