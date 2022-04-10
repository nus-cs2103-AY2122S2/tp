package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.record.Record;

public class RecordListPanel extends UiPart<Region> {

    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(RecordListPanel.class);
    @FXML
    private ListView<Record> personListView;

    /**
     * Creates a {@code RecordListPanel} with the given {@code ObservableList}.
     */
    public RecordListPanel(ObservableList<Record> personList, MainWindow main) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new RecordListViewCell(main));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Record} using a {@code Record Card}.
     */
    class RecordListViewCell extends ListCell<Record> {

        private MainWindow main;

        RecordListViewCell(MainWindow main) {
            super();
            this.main = main;
        }

        @Override
        protected void updateItem(Record record, boolean empty) {
            super.updateItem(record, empty);

            if (empty || record == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new RecordCard(record, getIndex() + 1, main).getRoot());
            }
        }
    }

}
