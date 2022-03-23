package seedu.contax.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.contax.commons.core.LogsCenter;
import seedu.contax.model.chrono.ScheduleItem;

/**
 * Panel containing a list of appointments.
 */
public class AppointmentListPanel extends UiPart<Region> {
    private static final String FXML = "AppointmentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AppointmentListPanel.class);

    @FXML
    private ListView<ScheduleItem> appointmentListView;

    private final AppointmentListCardFactory cardFactory;

    /**
     * Creates a {@code AppointmentListPanel} with the given appointment {@code ObservableList}.
     */
    public AppointmentListPanel(ObservableList<ScheduleItem> appointmentList) {
        super(FXML);
        this.cardFactory = new AppointmentListCardFactory();
        appointmentListView.setItems(appointmentList);
        appointmentListView.setCellFactory(listView -> new AppointmentListPanel.AppointmentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of an {@code Appointment} using
     * an {@code AppointmentCard}.
     */
    class AppointmentListViewCell extends ListCell<ScheduleItem> {
        @Override
        protected void updateItem(ScheduleItem scheduleItem, boolean empty) {
            super.updateItem(scheduleItem, empty);

            if (empty || scheduleItem == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(cardFactory.createCard(scheduleItem, getIndex() + 1));
            }
        }
    }

}
