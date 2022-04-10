package seedu.address.ui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.record.Record;

public class RecordCard extends UiPart<Region> {

    private static final String FXML = "RecordListCard.fxml";
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
    private Label id;

    /**
     * Creates a {@code RecordCode} with the given {@code Record} and index to display.
     */
    public RecordCard(Record record, int displayedIndex, MainWindow main) {
        super(FXML);
        this.record = record;
        id.setText(displayedIndex + ". " + record.getClientID());
        title.setText(" - " + record.getInsuranceID());

        cardPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                System.out.println("Mouse pressed box " + displayedIndex);
                main.updateRecordDetailPanel(record, displayedIndex);
            }
        });
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecordCard)) {
            return false;
        }

        // state check
        RecordCard card = (RecordCard) other;
        return title.getText().equals(card.title.getText())
                && record.equals(card.record);
    }
}
