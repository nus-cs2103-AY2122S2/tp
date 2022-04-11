package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * Panel containing the view of an expanded person card.
 */
public class ExpandedPersonListPanel extends UiPart<Region> {
    private static final String FXML = "ExpandedPersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ExpandedPersonListPanel.class);
    private final ObservableList<Event> eventList;

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList} of event and person.
     */
    public ExpandedPersonListPanel(ObservableList<Person> personList, ObservableList<Event> eventList) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        this.eventList = eventList;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    private class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);
            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ExpandedPersonCard(person, eventList).getRoot());
            }
        }
    }
}
