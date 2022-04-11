package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.buyer.Buyer;

/**
 * Panel containing the list of clients.
 */
public class BuyerListPanel extends UiPart<Region> {
    private static final String FXML = "BuyerListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(BuyerListPanel.class);

    @FXML
    private ListView<Buyer> clientListView;

    /**
     * Creates a {@code clientListPanel} with the given {@code ObservableList}.
     */
    public BuyerListPanel(ObservableList<Buyer> clientList) {
        super(FXML);
        clientListView.setItems(clientList);
        clientListView.setCellFactory(listView -> new ClientListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code client} using a {@code clientCard}.
     */
    class ClientListViewCell extends ListCell<Buyer> {
        @Override
        protected void updateItem(Buyer client, boolean empty) {
            super.updateItem(client, empty);

            if (empty || client == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new BuyerCard(client, getIndex() + 1).getRoot());
            }
        }
    }

}
