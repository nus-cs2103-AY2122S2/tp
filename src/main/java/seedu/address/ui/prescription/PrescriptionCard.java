package seedu.address.ui.prescription;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.address.model.prescription.Prescription;
import seedu.address.ui.UiPart;
import javafx.scene.layout.Region;


public class PrescriptionCard extends UiPart<Region> {

    private static final String FXML = "prescription/PrescriptionListCard.fxml";
    public final Prescription prescription;

    @FXML
    private HBox cardPane;
    @FXML
    private Label nric;
    @FXML
    private Label id;
    @FXML
    private Label drugname;
    @FXML
    private Label date;
    @FXML
    private Label instruction;

    public PrescriptionCard(Prescription prescription, int displayedIndex) {
        super(FXML);
        this.prescription = prescription;
        id.setText(displayedIndex + ". ");
        nric.setText(prescription.getPrescriptionTarget().value);
        drugname.setText(prescription.getDrugName().drugName);
        date.setText(prescription.getPrescriptionDate().toString());
        instruction.setText(prescription.getInstruction().value);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PrescriptionCard)) {
            return false;
        }

        // state check
        PrescriptionCard card = (PrescriptionCard) other;
        return id.getText().equals(card.id.getText())
                && prescription.equals(card.prescription);
    }
}
