package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.record.Record;

/**
 * Panel containing the list of expired records.
 */
public class ExpiredRecordPanel extends UiPart<Region> {
    private static final String FXML = "AppointmentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AppointmentListPanel.class);
    @FXML
    private ListView<Record> appointmentListView;

    /**
     * Creates a {@code ExpiredRecordPanel} with the given {@code ObservableList}.
     * @param recordList
     */
    public ExpiredRecordPanel(ObservableList<Record> recordList) {
        super(FXML);
        appointmentListView.setItems(recordList);
        appointmentListView.setCellFactory(listView -> new ExpiredRecordPanel.ExpiredRecordListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Record} using a {@code RecordCard}.
     */
    class ExpiredRecordListViewCell extends ListCell<Record> {
        @Override
        protected void updateItem(Record record, boolean empty) {
            super.updateItem(record, empty);

            if (empty || record == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ExpiredRecordCard(record, getIndex() + 1).getRoot());
            }
        }
    }
}
