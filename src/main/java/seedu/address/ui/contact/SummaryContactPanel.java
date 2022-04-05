package seedu.address.ui.contact;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.contact.Contact;
import seedu.address.ui.UiPart;

public class SummaryContactPanel extends UiPart<Region> {

    private static final String FXML = "contact/SummaryContactPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SummaryContactPanel.class);

    @FXML
    private ListView<Contact> contactListView;

    /**
     * Creates a {@code ContactListPanel} with the given {@code ObservableList}.
     */
    public SummaryContactPanel(ObservableList<Contact> contactList) {
        super(FXML);
        contactListView.setItems(contactList);
        contactListView.setCellFactory(listView -> new SummaryContactPanel.ContactListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ContactListViewCell extends ListCell<Contact> {
        @Override
        protected void updateItem(Contact contact, boolean empty) {
            super.updateItem(contact, empty);

            if (empty || contact == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ContactCard(contact, getIndex() + 1).getRoot());
            }
        }
    }
}
