package seedu.address.ui.consultation;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.patient.Patient;
import seedu.address.ui.PersonListPanel;
import seedu.address.ui.UiPart;
import seedu.address.ui.consultation.ConsultationCard;
import seedu.address.ui.consultation.ConsultationListPanel;

/**
 * Panel containing the list of persons.
 */
public class ConsultationListPanel extends UiPart<Region> {
    private static final String FXML = "consultation/ConsultationListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ConsultationListPanel.class);

    @FXML
    private ListView<Consultation> consultationListView;
    private ObservableList<Patient> personList;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ConsultationListPanel(ObservableList<Consultation> consultationList) {
        super(FXML);
        consultationListView.setItems(consultationList);
        consultationListView.setCellFactory(listView -> new ConsultationListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ConsultationListViewCell extends ListCell<Consultation> {
        @Override
        protected void updateItem(Consultation consultation, boolean empty) {
//            System.out.println("ConsultationListViewCell.updateItem "+consultation.toString());
            super.updateItem(consultation, empty);

            if (empty || consultation == null) {
                System.out.println("Consultation is null?");
                setGraphic(null);
                setText(null);
            } else {

                setGraphic(new ConsultationCard(consultation, getIndex() + 1).getRoot());
            }
        }
    }

}

