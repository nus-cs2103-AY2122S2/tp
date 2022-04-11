package seedu.address.ui;

import java.util.HashSet;
import java.util.Iterator;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Meeting}.
 */
public class MeetingCard extends UiPart<Region> {

    private static final String FXML = "MeetingListCard.fxml";

    private final Meeting meeting;
    private final ObservableList<Person> personList;

    @FXML
    private VBox cardPane;

    @FXML
    private Label index;

    @FXML
    private Label agenda;

    @FXML
    private Label time;

    @FXML
    private Label place;

    @FXML
    private Label attendees;

    /**
     * Creates a {@code MeetingCard} with the given {@code Meeting} and index to display.
     */
    public MeetingCard(Meeting meeting, int displayedIndex, ObservableList<Person> personList) {
        super(FXML);
        this.meeting = meeting;
        this.personList = personList;
        this.index.setText(displayedIndex + ". ");
        this.agenda.setText(meeting.getAgenda().toString());
        this.time.setText(meeting.getTime().toPrettyString());
        this.place.setText(meeting.getPlace().toString());
        this.attendees.setText(attendeesToLabel(meeting.getAttendees()));
    }

    private String attendeesToLabel(HashSet<Id> attendees) {
        StringBuilder strBuilder = new StringBuilder();

        Iterator<Id> iterator = attendees.iterator();

        while (iterator.hasNext()) {
            Id attendee = iterator.next();

            String name = "Unknown attendee";

            for (Person person : personList) {
                if (person.getId().equals(attendee)) {
                    name = person.getName().toString();
                }
            }

            strBuilder.append(name);

            if (iterator.hasNext()) {
                strBuilder.append(", ");
            }
        }

        return strBuilder.toString();
    }
}
