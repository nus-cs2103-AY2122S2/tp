package seedu.address.ui.medical;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.medical.Medical;
import seedu.address.ui.UiPart;


public class MedicalCard extends UiPart<Region> {

    private static final String FXML = "medical/MedicalListCard.fxml";
    public final Medical medical;

    @FXML
    private HBox cardPane;
    @FXML
    private Label index;
    @FXML
    private Label nric;
    @FXML
    private Label age;
    @FXML
    private Label bloodType;
    @FXML
    private Label medication;
    @FXML
    private Label height;

    @FXML
    private Label weight;

    @FXML
    private Label illnesses;

    @FXML
    private Label surgeries;

    @FXML
    private Label familyHistory;


    @FXML
    private Label immunizationHistory;

    @FXML
    private Label gender;

    @FXML
    private Label ethnicity;

    /**
     * Constructor for medical card.
     */
    public MedicalCard(Medical medical, int displayedIndex) {
        super(FXML);
        this.medical = null;
        index.setText(displayedIndex + ". ");
        nric.setText("Patient's NRIC: " + medical.getPatientNric().value);
        age.setText("Age: " + medical.getAge().value);
        bloodType.setText("Blood type: " + medical.getBloodType().value);
        medication.setText("Medication: " + medical.getMedication().value);
        weight.setText("Weight: " + medical.getWeight().value);
        height.setText("Height: " + medical.getHeight().value);
        illnesses.setText("Illnesses: " + medical.getIllnesses().value);
        surgeries.setText("Surgeries: " + medical.getSurgeries().value);
        familyHistory.setText("Family history: " + medical.getFamilyHistory().value);
        immunizationHistory.setText("Immunization history: " + medical.getImmunizationHistory().value);
        gender.setText("Gender: " + medical.getGender().value);
        ethnicity.setText("Ethnicity: " + medical.getEthnicity().value);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MedicalCard)) {
            return false;
        }

        // state check
        MedicalCard card = (MedicalCard) other;
        return nric.getText().equals(card.nric.getText())
                && medical.equals(card.medical);
    }
}
