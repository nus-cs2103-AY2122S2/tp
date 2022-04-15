package seedu.address.ui.prescription;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.prescription.Prescription;
import seedu.address.ui.UiPart;


public class PrescriptionCard extends UiPart<Region> {

    private static final String FXML = "prescription/PrescriptionListCard.fxml";
    public final Prescription prescription;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label drugName;
    @FXML
    private Label date;
    @FXML
    private Label instruction;

    /**
     * Creates a {@code PrescriptionCode} with the given {@code Prescription} and index to display.
     */
    public PrescriptionCard(Prescription prescription, int displayedIndex) {
        super(FXML);
        this.prescription = prescription;
        id.setText(displayedIndex + ". ");
        drugName.setText(prescription.getDrugName().drugName);
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
