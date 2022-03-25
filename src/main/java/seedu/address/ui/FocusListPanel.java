package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.candidate.Candidate;

/**
 * Panel containing the list of candidates.
 */
public class FocusListPanel extends UiPart<Region> {
    private static final String FXML = "FocusListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FocusListPanel.class);

    @FXML
    private ListView<Candidate> focusListView;

    /**
     * Creates a {@code CandidateListPanel} with the given {@code ObservableList}.
     */
    public FocusListPanel(ObservableList<Candidate> candidateList) {
        super(FXML);
        focusListView.setItems(candidateList);
        focusListView.setCellFactory(listView -> new FocusListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Candidate} using a {@code CandidateCard}.
     */
    class FocusListViewCell extends ListCell<Candidate> {
        @Override
        protected void updateItem(Candidate candidate, boolean empty) {
            super.updateItem(candidate, empty);

            if (empty || candidate == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FocusCard(candidate, getIndex() + 1).getRoot());
            }
        }
    }

}
