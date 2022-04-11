package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.assessment.Assessment;

//@@author Gernene
/**
 * Panel containing the list of assessments.
 */
public class AssessmentListPanel extends UiPart<Region> {
    private static final String FXML = "AssessmentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AssessmentListPanel.class);

    @FXML
    private ListView<Assessment> assessmentListView;

    /**
     * Creates a {@code AssessmentListPanel} with the given {@code ObservableList}.
     */
    public AssessmentListPanel(ObservableList<Assessment> assessmentList) {
        super(FXML);
        assessmentListView.setItems(assessmentList);
        assessmentListView.setCellFactory(listView -> new AssessmentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Assessment} using a {@code AssessmentCard}.
     */
    class AssessmentListViewCell extends ListCell<Assessment> {
        @Override
        protected void updateItem(Assessment assessment, boolean empty) {
            super.updateItem(assessment, empty);

            if (empty || assessment == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AssessmentCard(assessment, getIndex() + 1).getRoot());
            }
        }
    }

}
