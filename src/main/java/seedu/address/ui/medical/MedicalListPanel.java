package seedu.address.ui.medical;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.medical.Medical;
import seedu.address.ui.UiPart;


public class MedicalListPanel extends UiPart<Region> {
    private static final String SCREEN_TITLE = "Medical Details";
    private static final String FXML = "medical/MedicalListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(MedicalListPanel.class);

    @javafx.fxml.FXML
    private ListView<Medical> medicalListView;

    @javafx.fxml.FXML
    private Label screenTitle;

    /**
     * Creates a {@code MedicalListPanel} with the given {@code ObservableList}.
     */
    public MedicalListPanel(ObservableList<Medical> medicalList) {
        super(FXML);
        screenTitle.setText(SCREEN_TITLE);
        medicalListView.setItems(medicalList);
        medicalListView.setCellFactory(listView -> new MedicalListViewCell());
    }

    class MedicalListViewCell extends ListCell<Medical> {
        @Override
        protected void updateItem(Medical medical, boolean empty) {
            super.updateItem(medical, empty);

            if (empty || medical == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MedicalCard(medical, getIndex() + 1).getRoot());
            }
        }
    }
}
