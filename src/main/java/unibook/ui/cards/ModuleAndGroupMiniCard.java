package unibook.ui.cards;

import static unibook.ui.util.CustomListChangeListeners.addBasicListChangeListener;
import static unibook.ui.util.CustomVBoxListFiller.fillPaneFromList;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import unibook.commons.core.LogsCenter;
import unibook.model.module.Module;
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


    public ModuleAndGroupMiniCard(Module module) {
        super(FXML);
        logger.info(String.format("Instantiating a moduleAndGroupMiniCard with module code %s",
                module.getModuleCode()));
        this.module = module;
        moduleCode.setText(module.getModuleCode().toString());

        //fill in all the group codes
        fillPaneFromList(groupNameList, module.getGroups(), group -> new Label(group.getGroupName()));

        //set up list listener to update group name on event of underlying list change
        addBasicListChangeListener(groupNameList, module.getGroups(), group -> new Label(group.getGroupName()));
    }
}
