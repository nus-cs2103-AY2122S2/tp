package seedu.address.ui;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.contact.Contact;
import seedu.address.model.medical.Medical;
import seedu.address.model.patient.Patient;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.testresult.TestResult;
import seedu.address.ui.consultation.SummaryConsultationPanel;
import seedu.address.ui.contact.SummaryContactPanel;
import seedu.address.ui.medical.SummaryMedicalCard;
import seedu.address.ui.prescription.SummaryPrescriptionPanel;
import seedu.address.ui.testresult.SummaryTestResultPanel;

/**
 * UI for summary display
 */
public class SummaryDisplay extends UiPart<Region> {
    private static final String SCREEN_TITLE = "Summary";
    private static final String FXML = "SummaryDisplay.fxml";

    @FXML
    private Label screenTitle;
    @FXML
    private StackPane details;
    @FXML
    private StackPane medical;
    @FXML
    private StackPane consultation;
    @FXML
    private StackPane prescription;
    @FXML
    private StackPane test;
    @FXML
    private StackPane contact;

    /**
     * UI for summary
     */
    public SummaryDisplay() {
        super(FXML);
        screenTitle.setText(SCREEN_TITLE);
    }

    public void setSummary(ObservableList<Patient> patients, ObservableList<Medical> medicals,
                           ObservableList<Consultation> consultations, ObservableList<Prescription> prescriptions,
                           ObservableList<TestResult> results, ObservableList<Contact> contacts) {
        requireAllNonNull(patients, medicals);

        Patient patient = patients.get(0);
        PatientCard patientCard = new PatientCard(patient, 1);

        SummaryConsultationPanel summaryConsultationPanel = new SummaryConsultationPanel(consultations);
        SummaryPrescriptionPanel summaryPrescriptionPanel = new SummaryPrescriptionPanel(prescriptions);
        SummaryTestResultPanel summaryTestResultPanel = new SummaryTestResultPanel(results);
        SummaryContactPanel summaryContactPanel = new SummaryContactPanel(contacts);

        EmptyCard medicalEmptyCard = new EmptyCard();
        EmptyCard consultationEmptyCard = new EmptyCard();
        EmptyCard prescriptionEmptyCard = new EmptyCard();
        EmptyCard testResultEmptyCard = new EmptyCard();
        EmptyCard contactEmptyCard = new EmptyCard();

        details.getChildren().clear();
        details.getChildren().add(patientCard.getRoot());

        medical.getChildren().clear();
        if (medicals.size() != 0) {
            SummaryMedicalCard summaryMedicalCard = new SummaryMedicalCard(medicals.get(0));
            medical.getChildren().add(summaryMedicalCard.getRoot());
        } else {
            medical.getChildren().add(medicalEmptyCard.getRoot());
        }

        consultation.getChildren().clear();
        if (consultations.size() != 0) {
            consultation.getChildren().add(summaryConsultationPanel.getRoot());
        } else {
            consultation.getChildren().add(consultationEmptyCard.getRoot());
        }

        prescription.getChildren().clear();
        if (prescriptions.size() != 0) {
            prescription.getChildren().add(summaryPrescriptionPanel.getRoot());
        } else {
            prescription.getChildren().add(prescriptionEmptyCard.getRoot());
        }

        test.getChildren().clear();
        if (results.size() != 0) {
            test.getChildren().add(summaryTestResultPanel.getRoot());
        } else {
            test.getChildren().add(testResultEmptyCard.getRoot());
        }

        contact.getChildren().clear();
        if (contacts.size() != 0) {
            contact.getChildren().add(summaryContactPanel.getRoot());
        } else {
            contact.getChildren().add(contactEmptyCard.getRoot());
        }
    }
}
