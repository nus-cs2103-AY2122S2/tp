package seedu.address.ui.applicant;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.applicant.Applicant;
import seedu.address.ui.InfoPanel;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of Applicants.
 */
public class ApplicantListPanel extends UiPart<Region> {
    // Todo: Create ApplicantListPanel
    private static final String FXML = "ApplicantListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ApplicantListPanel.class);
    private InfoPanel infoPanel;
    @FXML
    private ListView<Applicant> applicantListView;


    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ApplicantListPanel(ObservableList<Applicant> applicantList, InfoPanel applicantInfoPanel) {
        super(FXML);
        applicantListView.setItems(applicantList);
        applicantListView.setCellFactory(listView -> new ApplicantListViewCell());
        this.infoPanel = applicantInfoPanel;
    }

    /**
     * Displays ListView item clicked on the ListView on the {@code testPanel}
     */
    public void handleApplicantClicks() {
        applicantListView.setOnMouseClicked(event -> {
            Applicant applicant = applicantListView.getSelectionModel().getSelectedItem();
            if (applicant != null) {
                infoPanel.setApplicantInfo(applicant);
            } else {
                infoPanel.resetPanel();
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ApplicantListViewCell extends ListCell<Applicant> {
        @Override
        protected void updateItem(Applicant applicant, boolean empty) {
            super.updateItem(applicant, empty);
            if (empty || applicant == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ApplicantCard(applicant, getIndex() + 1).getRoot());
            }
        }
    }
}

