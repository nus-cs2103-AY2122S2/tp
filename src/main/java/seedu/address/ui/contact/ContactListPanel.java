package seedu.address.ui.contact;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.contact.Contact;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of persons.
 */
public class ContactListPanel extends UiPart<Region> {
    private static final String SCREEN_TITLE = "Contacts";
    private static final String FXML = "contact/ContactListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ContactListPanel.class);

    @FXML
    private ListView<Contact> contactListView;

    @FXML
    private Label screenTitle;

    /**
     * Creates a {@code ContactListPanel} with the given {@code ObservableList}.
     */
    public ContactListPanel(ObservableList<Contact> contactList) {
        super(FXML);
        screenTitle.setText(SCREEN_TITLE);
        contactListView.setItems(contactList);
        contactListView.setCellFactory(listView -> new ContactListViewCell());
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
