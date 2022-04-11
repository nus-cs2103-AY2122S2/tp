package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.summarybar.SummaryBox;

/**
 * Panel containing the list of application summaries.
 */
public class SummaryListPanel extends UiPart<Region> {
    private static final String FXML = "SummaryListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SummaryListPanel.class);

    @FXML
    private ListView<SummaryBox> summaryListView;

    /**
     * Creates a {@code SummaryListPanel} with the given {@code ObservableList}.
     */
    public SummaryListPanel(ObservableList<SummaryBox> summaryList) {
        super(FXML);
        summaryListView.setItems(summaryList);
        summaryListView.setCellFactory(listView -> new SummaryListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code summaryBox} using a {@code SummaryCard}.
     */
    class SummaryListViewCell extends ListCell<SummaryBox> {
        @Override
        protected void updateItem(SummaryBox summaryBox, boolean empty) {
            super.updateItem(summaryBox, empty);

            if (empty || summaryBox == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SummaryCard(summaryBox).getRoot());
            }
        }
    }

}
