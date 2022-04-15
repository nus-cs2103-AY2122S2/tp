package seedu.address.ui.prescription;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.prescription.Prescription;
import seedu.address.ui.UiPart;


public class PrescriptionListPanel extends UiPart<Region> {
    private static final String SCREEN_TITLE = "Prescriptions";
    private static final String FXML = "prescription/PrescriptionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PrescriptionListPanel.class);

    @javafx.fxml.FXML
    private ListView<Prescription> prescriptionListView;

    @javafx.fxml.FXML
    private Label screenTitle;
    /**
     * Creates a {@code PrescriptionListPanel} with the given {@code ObservableList}.
     */
    public PrescriptionListPanel(ObservableList<Prescription> prescriptionList) {
        super(FXML);
        screenTitle.setText(SCREEN_TITLE);
        prescriptionListView.setItems(prescriptionList);
        prescriptionListView.setCellFactory(listView -> new PrescriptionListViewCell());
    }

    class PrescriptionListViewCell extends ListCell<Prescription> {
        @Override
        protected void updateItem(Prescription prescription, boolean empty) {
            super.updateItem(prescription, empty);
            if (empty || prescription == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PrescriptionCard(prescription, getIndex() + 1).getRoot());
            }
        }
    }
}
