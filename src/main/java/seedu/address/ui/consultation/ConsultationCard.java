package seedu.address.ui.consultation;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.consultation.*;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class ConsultationCard extends UiPart<Region> {
    private static final String FXML = "ConsultationListCard.fxml";
    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    public final Consultation consultation;
    @FXML
    private HBox cardPane;
    @FXML
    private Label nric;
    @FXML
    private Label date;
    @FXML
    private Label time;
    @FXML
    private Label notes;
    @FXML
    private Label prescription;
    @FXML
    private Label testsTakenAndResults;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ConsultationCard(Consultation consultation) {
        super(FXML);
        this.consultation = consultation;
        nric.setText(consultation.getNric().toString());
        date.setText(consultation.getDate().toString());
        time.setText(consultation.getTime().toString());
        notes.setText(consultation.getNotes().toString());
        prescription.setText(consultation.getPrescription().toString());
        testsTakenAndResults.setText(consultation.getTestAndResults().toString());
    }
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof ConsultationCard)) {
            return false;
        }
        // state check
        ConsultationCard card = (ConsultationCard) other;
        return consultation.equals(card.consultation);
    }
}
