package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seedu.address.model.event.Event;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class EventCard extends UiPart<Region> {

    private static final Font font = new Font("Segoe UI Semibold", 13);

    private static final String FXML = "EventListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Event event;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private TextFlow dateTime;
    @FXML
    private TextFlow description;
    @FXML
    private FlowPane friends;

    /**
     * Creates a {@code EventCard} with the given {@code Event} and index to display.
     */
    public EventCard(Event event, int displayedIndex) {
        super(FXML);
        this.event = event;
        id.setText(displayedIndex + ". ");
        name.setText(event.getName().fullName);

        Text dateTimeText = new Text(" : " + event.getDateTime().toString());
        Text dateTimeLabel = new Text("Event Date");
        dateTimeText.setFill(Color.WHITE);
        dateTimeLabel.setFill(Color.WHITE);
        dateTimeText.setFont(font);
        dateTimeLabel.setFont(font);
        dateTimeLabel.setUnderline(true);
        dateTime.getChildren().addAll(dateTimeLabel, dateTimeText);


        Text descriptionText = new Text(" : " + (event.getDescription().value == null ? "-" : event.getDescription().value));
        Text descriptionLabel = new Text("Event description");
        descriptionText.setFill(Color.WHITE);
        descriptionLabel.setFill(Color.WHITE);
        descriptionText.setFont(font);
        descriptionLabel.setFont(font);
        descriptionLabel.setUnderline(true);
        description.getChildren().addAll(descriptionLabel, descriptionText);


        Text friendsText = new Text("Friends");
        Text colon = new Text(" : ");
        friendsText.setUnderline(true);
        friendsText.setFill(Color.WHITE);
        friendsText.setFont(font);
        colon.setFill(Color.WHITE);
        colon.setFont(font);
        friends.getChildren().addAll(friendsText, colon);
        friends.setHgap(4);
        if (event.getFriendNames().size() == 0) {
            Text empty = new Text("-");
            empty.setFill(Color.WHITE);
            empty.setFont(font);
            friends.getChildren().add(empty);
        } else {
            event.getFriendNames().stream()
                    .forEach(friend -> friends.getChildren().add(new Label(friend.fullName)));
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventCard)) {
            return false;
        }

        // state check
        EventCard card = (EventCard) other;
        return id.getText().equals(card.id.getText())
                && event.equals(card.event);
    }
}
