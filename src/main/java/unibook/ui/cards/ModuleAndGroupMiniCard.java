package unibook.ui.cards;

import static unibook.ui.util.CustomListChangeListeners.addBasicListChangeListener;
import static unibook.ui.util.CustomPaneListFiller.fillPaneFromList;

import java.util.function.Function;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import unibook.commons.core.LogsCenter;
import unibook.model.module.Module;
import unibook.model.module.group.Group;
import unibook.ui.UiPart;

public class ModuleAndGroupMiniCard extends UiPart<Region> {
    private static final String FXML = "cards/ModuleAndGroupMiniCard.fxml";

    private final Module module;
    private final Logger logger = LogsCenter.getLogger(ModuleAndGroupMiniCard.class);

    @FXML
    private HBox moduleAndGroupMiniPane;
    @FXML
    private Label moduleCode;
    @FXML
    private FlowPane groupNameList;


    public ModuleAndGroupMiniCard(Module module, Integer index) {
        super(FXML);
        logger.info(String.format("Instantiating a moduleAndGroupMiniCard with module code %s",
                module.getModuleCode()));
        this.module = module;
        moduleCode.setText(module.getModuleCode().toString());

        //common function to use in generating group name labels
        Function<Group, Label> f = new Function<Group, Label>() {
            @Override
            public Label apply(Group group) {
                Label label = new Label(group.getGroupName());
                label.getStyleClass().add("mini-pane-group-name-label");
                return label;
            }
        };

        //fill in all the group codes
        fillPaneFromList(groupNameList, module.getGroups(), f);

        //set up list listener to update group name on event of underlying list change
        addBasicListChangeListener(groupNameList, module.getGroups(), f);

        //change background colour according to index of module card
        if (index % 2 == 0) {
            moduleAndGroupMiniPane.getStyleClass().add("odd-mini-pane");
        } else {
            moduleAndGroupMiniPane.getStyleClass().add("even-mini-pane");
        }


    }
}
