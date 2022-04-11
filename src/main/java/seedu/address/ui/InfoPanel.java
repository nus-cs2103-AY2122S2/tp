package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.job.Job;

/**
 * Panel containing all the information of the Applicant that has been selected in the list.
 */
public class InfoPanel extends UiPart<Region> {
    // Todo: create InfoPanel.fxml
    private static final String FXML = "InfoPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(InfoPanel.class);

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private ImageView image;
    @FXML
    private FlowPane tags;
    @FXML
    private Label firstLabel;
    @FXML
    private Label secondLabel;
    @FXML
    private Label thirdLabel;
    @FXML
    private Label fourthLabel;
    @FXML
    private Label fifthLabel;
    @FXML
    private Label sixthLabel;
    @FXML
    private Label seventhLabel;
    @FXML
    private Label eighthLabel;
    @FXML
    private Label ninthLabel;
    @FXML
    private Label firstSubHeader;
    @FXML
    private Label secondSubHeader;
    @FXML
    private Label thirdSubHeader;
    @FXML
    private Label fourthSubHeader;
    @FXML
    private Label fifthSubHeader;
    @FXML
    private Label sixthSubHeader;
    @FXML
    private Label seventhSubHeader;
    @FXML
    private Label eighthSubHeader;
    @FXML
    private Label ninthSubHeader;

    // Stores image of applicant icon
    private final Image applicantIcon = new Image(this.getClass().getResourceAsStream("/images/applicant_icon.png"),
            50, 50, false, false);
    // Stores image of job icon
    private final Image jobIcon = new Image(this.getClass().getResourceAsStream("/images/job_icon.png"),
            50, 50, false, false);

    /**
     * Constructs InfoPanel.
     */
    public InfoPanel() {
        super(FXML);
    }

    /**
     * Displays an applicant info.
     *
     * @param applicant an applicant selected from the list
     */
    public void setApplicantInfo(Applicant applicant) {
        // Reset panels to display the applicant
        resetPanel();
        setApplicantSubHeader();
        image.setImage(applicantIcon);
        // Set name
        name.setText(applicant.getName().fullName);
        // Set NRIC value
        firstLabel.setText(applicant.getNric().toString());
        // Set phone number
        secondLabel.setText(applicant.getPhone().value);
        // Set email
        thirdLabel.setText(applicant.getEmail().value);
        // Set address
        fourthLabel.setText(applicant.getAddress().value);
        // Set dateApplied
        fifthLabel.setText(applicant.getDateApplied().toString());
        // Set interviewDate
        sixthLabel.setText(applicant.getInterviewDate().toString());
        // Set jobID
        seventhLabel.setText(applicant.getJobId().toString());
        // Set qualification
        eighthLabel.setText(applicant.getQualification().toString());
        // Set status
        StyleUtil.setApplicantStatusStyle(ninthLabel, applicant.getApplicantStatus().toString());
        // Set tag
        applicant.getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Sets the sub-headers for displaying an applicant.
     */
    private void setApplicantSubHeader() {
        firstSubHeader.setText("Nric: ");
        secondSubHeader.setText("Phone number: ");
        thirdSubHeader.setText("Email: ");
        fourthSubHeader.setText("Address: ");
        fifthSubHeader.setText("Date applied: ");
        sixthSubHeader.setText("Interview date: ");
        seventhSubHeader.setText("Job ID: ");
        eighthSubHeader.setText("Qualification: ");
        ninthSubHeader.setText("Application status: ");
    }

    /**
     * Displays a job info.
     *
     * @param job a job selected from the list
     */
    public void setJobInfo(Job job) {
        resetPanel();
        setJobSubHeader();
        image.setImage(jobIcon);
        name.setText(job.getJobTitle().toString());
        id.setText("#" + job.getJobId().toString());
        // Set company name
        firstLabel.setText(job.getCompany().toString());
        // Set job position
        StyleUtil.setJobPositionStyle(secondLabel, job.getPosition().toString());
        // Set salary
        thirdLabel.setText(job.getSalary().toString());
        // Set address
        fourthLabel.setText(job.getAddress().toString());
        // Set job status
        StyleUtil.setJobStatusStyle(fifthLabel, job.getJobStatus().toString());
        // Set job qualification
        sixthLabel.setText(job.getQualification().toString());
        // Make unused label to invisible
    }

    /**
     * Sets the sub-headers for job information.
     */
    private void setJobSubHeader() {
        firstSubHeader.setText("Company name: ");
        secondSubHeader.setText("Position: ");
        thirdSubHeader.setText("Salary: ");
        fourthSubHeader.setText("Address: ");
        fifthSubHeader.setText("Job Status: ");
        sixthSubHeader.setText("Qualification: ");
    }

    /**
     * Deletes the old sub header on the info panel
     */
    private void resetSubHeader() {
        firstSubHeader.setText(null);
        secondSubHeader.setText(null);
        thirdSubHeader.setText(null);
        fourthSubHeader.setText(null);
        fifthSubHeader.setText(null);
        sixthSubHeader.setText(null);
        seventhSubHeader.setText(null);
        eighthSubHeader.setText(null);
        ninthSubHeader.setText(null);
    }

    private void resetLabel() {
        firstLabel.setText(null);
        secondLabel.setText(null);
        thirdLabel.setText(null);
        fourthLabel.setText(null);
        fifthLabel.setText(null);
        sixthLabel.setText(null);
        seventhLabel.setText(null);
        eighthLabel.setText(null);
        ninthLabel.setText(null);
    }

    /**
     * Delete the old texts on the info panel
     */
    public void resetPanel() {
        tags.getChildren().clear();
        image.setImage(null);
        name.setText(null);
        id.setText(null);
        resetSubHeader();
        resetLabel();
        StyleUtil.resetLabelStyle(secondLabel);
        StyleUtil.resetLabelStyle(fifthLabel);
        StyleUtil.resetLabelStyle(ninthLabel);
    }
}
