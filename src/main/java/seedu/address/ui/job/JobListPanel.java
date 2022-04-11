package seedu.address.ui.job;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.job.Job;
import seedu.address.ui.InfoPanel;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of Applicants.
 */
public class JobListPanel extends UiPart<Region> {
    // Todo: Create ApplicantListPanel
    private static final String FXML = "JobListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(JobListPanel.class);
    private InfoPanel infoPanel;
    @FXML
    private ListView<Job> jobListView;


    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public JobListPanel(ObservableList<Job> jobList, InfoPanel jobInfoPanel) {
        super(FXML);
        jobListView.setItems(jobList);
        jobListView.setCellFactory(listView -> new JobListViewCell());
        this.infoPanel = jobInfoPanel;
    }

    /**
     * Displays ListView item clicked on the ListView on the {@code testPanel}
     */
    public void handleJobClicks() {
        jobListView.setOnMouseClicked(event -> {
            Job job = jobListView.getSelectionModel().getSelectedItem();
            if (job != null) {
                infoPanel.setJobInfo(job);
            } else {
                infoPanel.resetPanel();
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class JobListViewCell extends ListCell<Job> {
        @Override
        protected void updateItem(Job job, boolean empty) {
            super.updateItem(job, empty);
            if (empty || job == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new JobCard(job, getIndex() + 1).getRoot());
            }
        }
    }
}

