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
import javafx.scene.layout.VBox;
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
     *
     * @param module     to display.
     * @param index      index of this mini card.
     * @param mainWindow of the UI.
     */
    public ModuleAndGroupMiniCard(Module module, Integer index, MainWindow mainWindow) {
        super(FXML);
        logger.info(String.format("Instantiating a moduleAndGroupMiniCard with module code %s",
            module.getModuleCode()));
        this.mainWindow = mainWindow;
        this.module = module;
        setUpModuleCodeLabel();
        setUpGroupNameList();
        setUpStyling(index);
        setUpClickEventHandler();
    }

    /**
     * Sets up the list of group names being shown of this module and group mini card.
     * Sets up the listener for changes to the underlying list of groups of the module of this card.
     */
    private void setUpGroupNameList() {
        //common function to use in generating group name vboxes containing labels
        //vboxes are to ensure flowpane wraps the labels properly
        Function<Group, VBox> f = group -> {
            VBox vbox = new VBox();
            Label label = new Label(group.getGroupName());
            label.getStyleClass().add("mini-pane-group-name-label");
            label.setWrapText(true);
            label.setMaxWidth(80);
            vbox.getChildren().add(label);
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
            return vbox;
        };

        //fill in all the group codes
        fillPaneFromList(groupNameList, module.getGroups(), f);
        //set up list listener to update group name on event of underlying list change
        addBasicListChangeListener(groupNameList, module.getGroups(), f);
    }

    /**
     * Set up the styling for the minipane.
     * All minpanes have a common basic styling, and some additional styling depending on
     * the parity of its position in its parent listing pane.
     *
     * @param index index of this mini pane in its parent listing pane.
     */
    private void setUpStyling(int index) {
        //basic commmon style class for all mini cards
        moduleAndGroupMiniPane.getStyleClass().add("mini-pane");

        //change background colour according to index of module card
        if (index % 2 == 0) {
            moduleAndGroupMiniPane.getStyleClass().add("odd-mini-pane");
        } else {
            moduleAndGroupMiniPane.getStyleClass().add("even-mini-pane");
        }
    }

    /**
     * Sets up the handler function that is called when the mini pane is clicked.
     */
    private void setUpClickEventHandler() {
        moduleCode.addEventHandler(MouseEvent.MOUSE_CLICKED,
            event -> {
                try {
                    switchToModulePage();
                } catch (CommandException | ParseException e) {
                    throw new ButtonActionException();
                }
            });
    }

    /**
     * Sets up the module code label.
     */
    private void setUpModuleCodeLabel() {
        moduleCode.setText(module.getModuleCode().toString());
        //adjust the styling of the label depending on its size
        //long labels will have a smaller text size
        int lengthOfModuleCode = module.getModuleCode().toString().length();
        if (lengthOfModuleCode >= 8) {
            moduleCode.getStyleClass().add("mini-pane-module-code-long");
        } else if (lengthOfModuleCode >= 6) {
            moduleCode.getStyleClass().add("mini-pane-module-code-medium");
        } else {
            moduleCode.getStyleClass().add("mini-pane-module-code-short");
        }
    }


    /**
     * Navigates to the module page of the {@code Module} with the {@code moduleCode} of this card.
     */
    private void switchToModulePage() throws CommandException, ParseException {
        mainWindow.setModuleListPanel();
        mainWindow.executeCommand("list m/" + moduleCode.getText());
    }
}
