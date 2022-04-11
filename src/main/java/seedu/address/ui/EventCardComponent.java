package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.event.Event;

/**
 * An UI component that displays information of an {@code Event}.
 */
public class EventCardComponent extends UiPart<Region> {

    private static final String FXML = "EventListCard.fxml";

    public final Event event;

    @FXML
    private HBox cardPane;
    @FXML
    private Label eventname;
    @FXML
    private Label id;
    @FXML
    private Label info;
    @FXML
    private Label datetime;
    @FXML
    private FlowPane participants;

    /**
     * Creates a {@code EventCode} with the given {@code Event} and index to display.
     */
    public EventCardComponent(Event event, int displayedIndex) {
        super(FXML);
        this.event = event;
        id.setText(displayedIndex + ". ");
        eventname.setText(event.getEventName().toString());
        datetime.setText(event.getDateTime().displayDateTime());
        info.setText("Details: " + event.getEventInfo().toString());
        event.getParticipants().stream()
                .sorted(Comparator.comparing(p -> p.fullName))
                .forEach(p -> participants.getChildren().add(new Label(p.fullName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventCardComponent)) {
            return false;
        }

        // state check
        EventCardComponent card = (EventCardComponent) other;
        return id.getText().equals(card.id.getText())
                && event.equals(card.event);
    }
}
