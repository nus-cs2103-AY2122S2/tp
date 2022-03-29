package unibook.ui.listpanels;

import static unibook.ui.util.CustomListChangeListeners.addIndexedAndFlagListChangeListener;
import static unibook.ui.util.CustomPaneListFiller.fillPaneFromList;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import unibook.commons.core.LogsCenter;
import unibook.commons.util.TriFunction;
import unibook.model.module.group.Group;
import unibook.ui.UiPart;
import unibook.ui.cards.GroupCard;
import unibook.ui.util.CustomPaneListFiller;

public class GroupListPanel extends UiPart<Region> {
    private static final String FXML = "listpanels/GroupListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(GroupListPanel.class);
    //Flag that indicates if only a single group is being shown
    private boolean singleGroupFlag = false;
    @FXML
    private VBox groupListView;
    private ObservableList<Group> groupList;

    /**
     * Creates a {@code GroupListPanel} with the given {@code ObservableList}.
     */
    public GroupListPanel(ObservableList<Group> groupList) {
        super(FXML);
        logger.info("Instantiating a new group list panel");
        this.groupList = groupList;
        if (groupList.size() == 1) {
            singleGroupFlag = true;
        }

        TriFunction<Group, Integer, Boolean, Node> cardConverter = new TriFunction<>() {
            @Override
            public Node apply(Group group, Integer index, Boolean singleFlag) {
                return new GroupCard(group, index + 1, singleFlag).getRoot();
            }
        };

        fillPaneFromList(groupListView, this.groupList, cardConverter);

        addIndexedAndFlagListChangeListener(groupListView, this.groupList, cardConverter);
    }

    /**
     * Returns the list of groups showing in this list panel.
     */
    public ObservableList<Group> getGroupsList() {
        return groupList;
    }
}
