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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seedu.address.model.event.Event;
import seedu.address.model.person.Log;
import seedu.address.model.person.Person;

/**
 * An UI component that displays the full information of a {@code Person}.
 */
public class ExpandedPersonCard extends UiPart<Region> {

    private static final String FXML = "ExpandedPersonListCard.fxml";
    private static final Font font = new Font("Segoe UI Semibold", 16);

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
    private TextFlow phone;
    @FXML
    private TextFlow address;
    @FXML
    private TextFlow email;
    @FXML
    private TextFlow description;
    @FXML
    private FlowPane tags;
    @FXML
    private Label eventsHeader;
    @FXML
    private Label noEventsText;
    @FXML
    private StackPane upcomingEventsPanelPlaceholder;
    @FXML
    private Label logsHeader;
    @FXML
    private Label logs;

    /**
     * Creates a {@code PersonCard} with the given {@code Person} and index to display.
     */
    public ExpandedPersonCard(Person person, ObservableList<Event> eventList) {
        super(FXML);
        this.person = person;
        name.setText("1. " + person.getName().fullName);

        Text colon1 = new Text(" : ");
        colon1.setFill(Color.WHITE);
        colon1.setFont(font);


        Text phoneNumText = new Text("           : " + (person.getPhone().value == null ? "  -" : person.getPhone().value));
        Text phoneLabel = new Text("Phone");
        phoneNumText.setFill(Color.WHITE);
        phoneLabel.setFill(Color.WHITE);
        phoneNumText.setFont(font);
        phoneLabel.setFont(font);
        phoneLabel.setUnderline(true);
        phone.getChildren().addAll(phoneLabel, phoneNumText);

        Text addressText = new Text("       : " + (person.getAddress().value == null ? "  -" : person.getAddress().value));
        Text addressLabel = new Text("Address");
        addressLabel.setFill(Color.WHITE);
        addressText.setFill(Color.WHITE);
        addressText.setFont(font);
        addressLabel.setFont(font);
        addressLabel.setUnderline(true);
        address.getChildren().addAll(addressLabel, addressText);

        Text emailText = new Text("             : " + (person.getEmail().value == null ? "  -" : person.getEmail().value));
        Text emailLabel = new Text("Email");
        emailLabel.setFill(Color.WHITE);
        emailText.setFill(Color.WHITE);
        emailText.setFont(font);
        emailLabel.setFont(font);
        emailLabel.setUnderline(true);
        email.getChildren().addAll(emailLabel, emailText);

        Text descriptionText = new Text(" : " + (person.getDescription().value == null ? "  -" : person.getDescription().value));
        Text descriptionLabel = new Text("Description");
        descriptionLabel.setFill(Color.WHITE);
        descriptionText.setFill(Color.WHITE);
        descriptionText.setFont(font);
        descriptionLabel.setFont(font);
        descriptionLabel.setUnderline(true);
        description.getChildren().addAll(descriptionLabel, descriptionText);

        Text tagsText = new Text("Tags");
        tagsText.setFill(Color.WHITE);
        tagsText.setFont(font);
        tagsText.setUnderline(true);
        tags.getChildren().addAll(tagsText, new Text("           "), colon1);
        if (person.getTags().size() == 0) {
            Text empty = new Text("-");
            empty.setFill(Color.WHITE);
            empty.setFont(font);
            tags.getChildren().add(empty);
        } else {
            person.getTags().stream()
                    .sorted(Comparator.comparing(tag -> tag.tagName))
                    .forEach(tag ->
                            tags.getChildren().add(new Label(tag.tagName)));
        }

        // displaying upcoming events
        upcomingEventsPanelPlaceholder.setStyle("-fx-background-radius:10;"
                + " -fx-border-radius: 10px; -fx-border-color: #05d1e8;");
        upcomingEventsPanel = new EventListPanel(eventList);

        if (eventList.size() > 0) {
            eventsHeader.setText("Upcoming Events :");
            eventsHeader.setUnderline(true);
            noEventsText.setMaxSize(0, 0);
            noEventsText.setMinSize(0, 0);
            upcomingEventsPanelPlaceholder.getChildren().add(upcomingEventsPanel.getRoot());

        } else {
            eventsHeader.setText("Upcoming Events : ");
            eventsHeader.setUnderline(true);
            noEventsText.setText("No upcoming events!");

            //forces the size of upcomingEventsPanelPlaceholder to be (0, 0)
            upcomingEventsPanelPlaceholder.setMaxSize(0, 0);
            upcomingEventsPanelPlaceholder.setMinSize(0, 0);

        }

        //displaying each log
        List<Log> logList = person.getLogs();

        if (logList.size() > 0) {
            logsHeader.setText("Logs :");
            logsHeader.setUnderline(true);
            StringBuilder sb = new StringBuilder();
            int numberOfLogs = logList.size();
            for (int i = 1; i <= numberOfLogs; i++) {
                sb.append(i + ". " + logList.get(i - 1).toString() + "\n");
            }
            logs.setText(sb.toString());
        } else {
            logsHeader.setText("Logs : ");
            logsHeader.setUnderline(true);
            logs.setText("No logs!");
        }
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
