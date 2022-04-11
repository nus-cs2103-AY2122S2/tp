package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of meetings that a person is involved in.
 */
public class ContactMeetingsPanel extends UiPart<Region> {
    private static final String FXML = "MeetingListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(MeetingListPanel.class);

    private final ObservableList<Person> personList;

    @FXML
    private ListView<Meeting> meetingListView;

    /**
     * Creates a {@code MeetingListPanel} with the given {@code ObservableList}.
     */
    public ContactMeetingsPanel(ObservableList<Meeting> meetingList, ObservableList<Person> personList, Person person) {
        super(FXML);
        this.personList = personList;

        ObservableList<Meeting> relevantMeetings = FXCollections.observableArrayList();

        for (Meeting meeting : meetingList) {
            if (meeting.contains(person.getId())) {
                relevantMeetings.add(meeting);
            }
        }

        meetingListView.setItems(relevantMeetings);
        meetingListView.setCellFactory(listView -> new ContactMeetingsPanel.MeetingListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Meeting} using a {@code MeetingCard}.
     */
    class MeetingListViewCell extends ListCell<Meeting> {
        @Override
        protected void updateItem(Meeting meeting, boolean empty) {
            super.updateItem(meeting, empty);

            if (empty || meeting == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MeetingCard(meeting, getIndex() + 1, personList).getRoot());
            }
        }
    }
}
