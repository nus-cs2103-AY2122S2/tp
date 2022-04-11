package seedu.trackbeau.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.trackbeau.commons.core.LogsCenter;
import seedu.trackbeau.model.service.Service;

/**
 * Panel containing the list of persons.
 */
public class ServiceListPanel extends UiPart<Region> {
    private static final String FXML = "ServiceListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ServiceListPanel.class);

    @FXML
    private ListView<Service> serviceListView;

    /**
     * Creates a {@code CustomerListPanel} with the given {@code ObservableList}.
     */
    public ServiceListPanel(ObservableList<Service> serviceList) {
        super(FXML);
        serviceListView.setItems(serviceList);
        serviceListView.setCellFactory(listView -> new ServiceListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Service} using a {@code ServiceCard}.
     */
    class ServiceListViewCell extends ListCell<Service> {
        @Override
        protected void updateItem(Service service, boolean empty) {
            super.updateItem(service, empty);

            if (empty || service == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ServiceCard(service, getIndex() + 1).getRoot());
            }
        }
    }

}
