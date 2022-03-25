package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.interview.Interview;

/**
 * Panel containing the list of interviews.
 */
public class FocusListPanel extends UiPart<Region> {
    private static final String FXML = "FocusListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FocusListPanel.class);

    @FXML
    private ListView<Interview> focusListView;

    /**
     * Creates a {@code InterviewListPanel} with the given {@code ObservableList}.
     */
    public FocusListPanel(ObservableList<Interview> interviewList) {
        super(FXML);
        focusListView.setItems(interviewList);
        focusListView.setCellFactory(listView -> new FocusListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Interview} using a {@code InterviewCard}.
     */
    class FocusListViewCell extends ListCell<Interview> {
        @Override
        protected void updateItem(Interview interview, boolean empty) {
            super.updateItem(interview, empty);

            if (empty || interview == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FocusCard(interview, getIndex() + 1).getRoot());
            }
        }
    }

}
