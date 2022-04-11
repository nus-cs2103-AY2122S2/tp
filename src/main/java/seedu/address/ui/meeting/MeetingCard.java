package seedu.address.ui.meeting;

import static seedu.address.model.meeting.MeetingDateTime.DISPLAY_TIME_FORMAT;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDateTime;
import seedu.address.ui.UiPart;


/**
 * An UI component that displays information of a {@code Meeting}.
 */
public class MeetingCard extends UiPart<Region> {
    private static final String FXML = "MeetingCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Meeting meeting;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label module;
    @FXML
    private Label name;
    @FXML
    private Label startDateTime;
    @FXML
    private Label endDateTime;
    @FXML
    private Label url;
    @FXML
    private Label isRecurring;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code MeetingCard} with the given {@code Meeting} and index to display.
     */
    public MeetingCard(Meeting meeting, int displayedIndex) {
        super(FXML);
        this.meeting = meeting;
        id.setText(displayedIndex + ". ");
        module.setText(meeting.getModule().code);
        name.setText(meeting.getName().name);
        startDateTime.setText(meeting.getStartDateTime().toString());
        endDateTime.setText(formatEndDateTime(meeting));
        url.setText(meeting.getUrl().meetingUrl.toString());
        isRecurring.setVisible(meeting.getIsRecurring().isRecurring);
        if (meeting.getIsRecurring().isRecurring) {
            isRecurring.setText("R");
        }
        isRecurring.managedProperty().bind(isRecurring.visibleProperty());
        meeting.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetingCard)) {
            return false;
        }

        // state check
        final MeetingCard card = (MeetingCard) other;
        return id.getText().equals(card.id.getText())
                && meeting.equals(card.meeting);
    }

    private String formatEndDateTime(Meeting meeting) {
        final MeetingDateTime start = meeting.getStartDateTime();
        final MeetingDateTime end = meeting.getEndDateTime();

        if (start.datetime.toLocalDate().isEqual(end.datetime.toLocalDate())) {
            return end.datetime.format(DISPLAY_TIME_FORMAT);
        } else {
            return end.toString();
        }
    }
}
