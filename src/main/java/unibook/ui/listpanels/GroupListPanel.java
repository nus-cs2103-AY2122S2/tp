package unibook.ui.listpanels;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import unibook.commons.core.LogsCenter;
import unibook.model.module.group.Group;
import unibook.ui.UiPart;
import unibook.ui.cards.GroupCard;

public class GroupListPanel extends UiPart<Region> {
    private static final String FXML = "listpanels/GroupListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(GroupListPanel.class);
    //Flag that indicates if only a single group is being shown
    private boolean singleGroupFlag = false;
    @FXML
    private ListView<Group> groupListView;

    /**
     * Creates a {@code GroupListPanel} with the given {@code ObservableList}.
     */
    public GroupListPanel(ObservableList<Group> groupList) {
        super(FXML);
        logger.info("Instantiating a new group list panel");
        groupListView.setItems(groupList);
        groupListView.setCellFactory(listView -> new GroupListPanel.GroupListViewCell());
        if (groupList.size() == 1) {
            singleGroupFlag = true;
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Group} using a {@code GroupCard}.
     */
    class GroupListViewCell extends ListCell<Group> {
        @Override
        protected void updateItem(Group group, boolean empty) {
            super.updateItem(group, empty);

            if (empty || group == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new GroupCard(group, getIndex() + 1, singleGroupFlag).getRoot());
            }
        }
    }
}
