package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

public class DetailedContactPanel extends UiPart<Region> {
    private static final String FXML = "DetailedContactPanel.fxml";

    @FXML
    private ListView<Person> detailedContactViewPanel;

    /**
     * Creates a {@code DetailedContactPanel} with the given {@code ObservableList}.
     */
    public DetailedContactPanel(ObservableList<Person> detailedContactView) {
        super(FXML);
        detailedContactViewPanel.setItems(detailedContactView);
        detailedContactViewPanel.setCellFactory(listView -> new DetailedPersonCardCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a
     * {@code DetailedPersonCard}.
     */
    class DetailedPersonCardCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DetailedPersonCard(person).getRoot());
            }
        }
    }
}
