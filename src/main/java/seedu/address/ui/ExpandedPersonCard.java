package seedu.address.ui;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.event.Event;
import seedu.address.model.person.Log;
import seedu.address.model.person.Person;

/**
 * An UI component that displays the full information of a {@code Person}.
 */
public class ExpandedPersonCard extends UiPart<Region> {

    private static final String FXML = "ExpandedPersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private final Person person;
    private EventListPanel upcomingEventsPanel;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label description;
    @FXML
    private FlowPane tags;
    @FXML
    private StackPane upcomingEventsPanelPlaceholder;
    @FXML
    private Label logs;

    /**
     * Creates a {@code PersonCard} with the given {@code Person} and index to display.
     */
    public ExpandedPersonCard(Person person, ObservableList<Event> eventList) {
        super(FXML);
        this.person = person;
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value == null ? "" : "Phone: " + person.getPhone().value);
        address.setText(person.getAddress().value == null ? "" : "Address: " + person.getAddress().value);
        email.setText(person.getEmail().value == null ? "" : "Email: " + person.getEmail().value);
        description.setText(person.getDescription().value == null
                ? ""
                : person.getDescription().value);

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        upcomingEventsPanel = new EventListPanel(eventList);
        upcomingEventsPanelPlaceholder.getChildren().add(upcomingEventsPanel.getRoot());

        //displaying each log
        StringBuilder sb = new StringBuilder();
        List<Log> logList = person.getLogs();
        int numberOfLogs = logList.size();

        for (int i = 1; i <= numberOfLogs; i++) {
            sb.append(i + ". " + logList.get(i - 1).toString() + "\n");
        }
        logs.setText(sb.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ExpandedPersonCard)) {
            return false;
        }

        // state check
        ExpandedPersonCard card = (ExpandedPersonCard) other;
        return person.equals(card.person);
    }
}
