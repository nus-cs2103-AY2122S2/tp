package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.record.Record;

public class RecordUnderPersonPanel extends UiPart<Region> {

    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(RecordUnderPersonPanel.class);
    @FXML
    private ListView<Record> personListView;

    /**
     * Creates a {@code RecordUnderPersonPanel} with the given {@code ObservableList}.
     */
    public RecordUnderPersonPanel(ObservableList<Record> personList) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new SmallRecordListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Record} using a {@code Record Card}.
     */
    class SmallRecordListViewCell extends ListCell<Record> {

        SmallRecordListViewCell() {
            super();
        }

        @Override
        protected void updateItem(Record record, boolean empty) {
            super.updateItem(record, empty);

            if (empty || record == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SmallRecordCard(record, getIndex() + 1).getRoot());
            }
        }
    }

}
