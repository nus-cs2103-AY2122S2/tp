package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Panel containing the list of persons or a detailed view of a person.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Person> personListView;

    @FXML
    private FlowPane activatedTags;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList} of persons and activated tags
     */
    public PersonListPanel(ObservableList<Person> personList, ObservableList<Tag> activatedTagList) {
        super(FXML);
        activatedTagList.addListener((ListChangeListener<Tag>) change -> {
            while (change.next()) {
                activatedTags.getChildren().clear();
                activatedTagList.stream()
                        .sorted(Comparator.comparing(tag -> tag.tagName))
                        .forEach(tag -> activatedTags.getChildren().add(new Label(tag.tagName)));

            }
        });
        activatedTagList.stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> activatedTags.getChildren().add(new Label(tag.tagName)));
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }
}
