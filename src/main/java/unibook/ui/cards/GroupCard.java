package unibook.ui.cards;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import unibook.commons.core.LogsCenter;
import unibook.model.module.group.Group;
import unibook.model.person.Student;
import unibook.ui.UiPart;

/**
 * A class that displays the information of a {@code Group}.
 */
public class GroupCard extends UiPart<Region> {
    private static final String FXML = "cards/GroupCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on UniBook level 4</a>
     */

    public final Group group;
    private final Logger logger = LogsCenter.getLogger(GroupCard.class);
    @FXML
    private HBox groupPane;
    @FXML
    private Label moduleCode;
    @FXML
    private Label id;
    @FXML
    private Label groupName;
    @FXML
    private GridPane moreGroupDetails;
    @FXML
    private VBox membersList;
    @FXML
    private VBox meetingTimesList;
    //flag that indicates if the additional details - members and meeting times are being shown
    private boolean additionalDetailsVisible = true;

    /**
     * Creates a {@code GroupCode} with the given {@code Group} and index to display.
     *
     * @param group           to display.
     * @param displayedIndex  index to display with the group.
     * @param singleGroupFlag indicates if only this group being displayed in view.
     */
    public GroupCard(Group group, int displayedIndex, boolean singleGroupFlag) {
        super(FXML);
        this.group = group;
        id.setText(displayedIndex + ". ");
        moduleCode.setText(group.getModule().getModuleCode().toString());
        groupName.setText(group.getGroupName());

        fillMemberList();
        fillMeetingTimesList();

        ObservableList<Student> modelMemberList = group.getMembers();
        ObservableList<LocalDateTime> modelMeetingTimesList = group.getMeetingTimes();

        if (!singleGroupFlag) {
            groupPane.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        logger.info("Group card clicked. Mouse Event fired.");
                        additionalDetailsVisible = !additionalDetailsVisible;
                        moreGroupDetails.setVisible(additionalDetailsVisible);
                        moreGroupDetails.setManaged(additionalDetailsVisible);
                    }
                });
            additionalDetailsVisible = false;
        }

        moreGroupDetails.setVisible(additionalDetailsVisible);
        moreGroupDetails.setManaged(additionalDetailsVisible);

        //add listeners to the underlying lists of members and meetingTimes of student
        //so that the list shown in this card will change on update
        modelMemberList.addListener(new ListChangeListener<>() {
            /**
             * Rebuild the list of members shown to viewer on any kind of change
             * @param c
             */
            @Override
            public void onChanged(Change<? extends Student> c) {
                fillMemberList();
            }
        });

        modelMeetingTimesList.addListener(new ListChangeListener<>() {
            @Override
            public void onChanged(Change<? extends LocalDateTime> c) {
                fillMeetingTimesList();
            }
        });
    }

    /**
     * Fills the member studentcard list of this module card.
     */
    private void fillMemberList() {
        ObservableList<Student> modelMemberList = group.getMembers();
        int index = 0;
        ArrayList<Node> memberCards = new ArrayList<>();
        for (Student student : modelMemberList) {
            index++;
            memberCards.add(new StudentCard(student, index).getRoot());
        }
        membersList.getChildren().setAll(memberCards);
    }

    /**
     * Fills the meeting times list of this module card.
     */
    private void fillMeetingTimesList() {
        ObservableList<LocalDateTime> modelMeetingTimeList = group.getMeetingTimes();
        int index = 0;
        ArrayList<Node> meetingTimeLabels = new ArrayList<>();
        for (LocalDateTime dateTime : modelMeetingTimeList) {
            index++;
            meetingTimeLabels.add(new Label(index + ".    "
                + dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))));
        }
        meetingTimesList.getChildren().setAll(meetingTimeLabels);
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleCard)) {
            return false;
        }

        // state check
        GroupCard card = (GroupCard) other;
        return id.getText().equals(card.id.getText())
            && group.equals(card.group);
    }

}
