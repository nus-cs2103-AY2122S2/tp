package unibook.ui.cards;

import static unibook.ui.util.CustomVBoxListFiller.fillPaneFromList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import unibook.commons.core.LogsCenter;
import unibook.model.module.group.Group;
import unibook.ui.UiPart;
import unibook.ui.util.CustomListChangeListeners;
import unibook.ui.util.CustomVBoxListFiller;

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

        setUpVBoxLists();

        setUpListChangeListeners();

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
    }

    /**
     * Set up listeners for changes in all the tab content lists, so that
     * when a change in one of them is detected, the list shown in ui
     * can change accordingly.
     */
    private void setUpListChangeListeners() {
        CustomListChangeListeners.addIndexedListChangeListener(membersList, group.getMembers(),
            (member, index) -> new StudentCard(member, index + 1).getRoot());
        CustomListChangeListeners.addIndexedListChangeListener(meetingTimesList, group.getMeetingTimes(),
            (meetingTime, index) -> createLabelFromMeetingTime(meetingTime, index + 1));
    }

    /**
     * Converts a given {@code MeetingTime} to a {@code Label} for display in GUI.
     */
    private Label createLabelFromMeetingTime(LocalDateTime meetingTime, int index) {
        return new Label(index + ".    "
            + meetingTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
    }

    /**
     * Sets up all the vbox lists which display lists of fields of a group to user.
     */
    private void setUpVBoxLists() {
        CustomVBoxListFiller.fillPaneFromList(membersList, group.getMembers(), (member, i) ->
            new StudentCard(member, i + 1).getRoot());
        CustomVBoxListFiller.fillPaneFromList(meetingTimesList, group.getMeetingTimes(), (meetingTime, i) ->
            createLabelFromMeetingTime(meetingTime, i + 1));
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
