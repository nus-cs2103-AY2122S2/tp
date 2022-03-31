package seedu.address.ui.consultation;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.consultation.Consultation;
import seedu.address.ui.UiPart;

public class SummaryConsultationPanel extends UiPart<Region> {
    private static final String FXML = "consultation/SummaryConsultationPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SummaryConsultationPanel.class);

    @FXML
    private ListView<Consultation> consultationListView;

    /**
     * Creates a {@code ConsultationListPanel} with the given {@code ObservableList}.
     */
    public SummaryConsultationPanel(ObservableList<Consultation> consultationList) {
        super(FXML);
        consultationListView.setItems(consultationList);
        consultationListView.setCellFactory(listView -> new SummaryConsultationPanel.ConsultationListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Consultation} using a {@code ConsultationCard}.
     */
    class ConsultationListViewCell extends ListCell<Consultation> {
        @Override
        protected void updateItem(Consultation consultation, boolean empty) {
            super.updateItem(consultation, empty);

            if (empty || consultation == null) {
                setGraphic(null);
                setText(null);
            } else {

                setGraphic(new ConsultationCard(consultation, getIndex() + 1).getRoot());
            }
        }
    }

}
