package unibook.ui.cards;

import static unibook.ui.util.CustomListChangeListeners.addBasicListChangeListener;
import static unibook.ui.util.CustomPaneListFiller.fillPaneFromList;

import java.util.function.Function;
import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import unibook.commons.core.LogsCenter;
import unibook.logic.commands.exceptions.CommandException;
import unibook.logic.parser.exceptions.ParseException;
import unibook.model.module.Module;
import unibook.model.module.group.Group;
import unibook.ui.MainWindow;
import unibook.ui.UiPart;
import unibook.ui.exceptions.ButtonActionException;

public class ModuleAndGroupMiniCard extends UiPart<Region> {
    private static final String FXML = "cards/ModuleAndGroupMiniCard.fxml";

    private final Module module;
    private final Logger logger = LogsCenter.getLogger(ModuleAndGroupMiniCard.class);

    private final MainWindow mainWindow;

    @FXML
    private HBox moduleAndGroupMiniPane;
    @FXML
    private Label moduleCode;
    @FXML
    private FlowPane groupNameList;

    /**
     * A Card displaying module code and the group names of groups of that module.
     * @param module to display.
     * @param index index of this mini card.
     * @param mainWindow of the UI.
     */
    public ModuleAndGroupMiniCard(Module module, Integer index, MainWindow mainWindow) {
        super(FXML);
        logger.info(String.format("Instantiating a moduleAndGroupMiniCard with module code %s",
            module.getModuleCode()));
        this.mainWindow = mainWindow;
        this.module = module;
        moduleCode.setText(module.getModuleCode().toString());

        //common function to use in generating group name labels
        Function<Group, Label> f = new Function<Group, Label>() {
            @Override
            public Label apply(Group group) {
                Label label = new Label(group.getGroupName());
                label.getStyleClass().add("mini-pane-group-name-label");
                label.setWrapText(true);
                label.setMaxWidth(80);
                //handler to navigate to the specific module
                label.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            switchToModulePage();
                            mainWindow.executeCommand("list o/group g/" + group.getGroupName());
                        } catch (CommandException | ParseException e) {
                            throw new ButtonActionException();
                        }
                    }
                });
                return label;
            }
        };

        //fill in all the group codes
        fillPaneFromList(groupNameList, module.getGroups(), f);

        //set up list listener to update group name on event of underlying list change
        addBasicListChangeListener(groupNameList, module.getGroups(), f);

        //basic commmon style class for all mini cards
        moduleAndGroupMiniPane.getStyleClass().add("mini-pane");

        //change background colour according to index of module card
        if (index % 2 == 0) {
            moduleAndGroupMiniPane.getStyleClass().add("odd-mini-pane");
        } else {
            moduleAndGroupMiniPane.getStyleClass().add("even-mini-pane");
        }

        moduleCode.addEventHandler(MouseEvent.MOUSE_CLICKED,
            new EventHandler<>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        switchToModulePage();
                    } catch (CommandException | ParseException e) {
                        throw new ButtonActionException();
                    }
                }
            });
    }


    /**
     * Navigates to the module page of the {@code Module} with the {@code moduleCode} of this card.
     */
    private void switchToModulePage() throws CommandException, ParseException {
        mainWindow.setModuleListPanel();
        mainWindow.executeCommand("list m/" + moduleCode.getText());
    }
}
