package unibook.ui.cards;

import static unibook.ui.util.CustomListChangeListeners.addIndexedAndFlagListChangeListener;
import static unibook.ui.util.CustomListChangeListeners.addIndexedListChangeListener;
import static unibook.ui.util.CustomPaneListFiller.fillPaneFromList;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import unibook.commons.core.LogsCenter;
import unibook.model.module.Module;
import unibook.ui.UiPart;

/**
 * A UI component that displays information of {@code Module}.
 */
public class ModuleCard extends UiPart<Region> {

    private static final String FXML = "cards/ModuleCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on UniBook level 4</a>
     */

    private final Module module;
    private final List<Node> allTabContents;
    private final Logger logger = LogsCenter.getLogger(ModuleCard.class);
    @FXML
    private HBox modulePane;
    @FXML
    private Label moduleCode;
    @FXML
    private Label id;
    @FXML
    private Label moduleName;
    @FXML
    private VBox professors;
    @FXML
    private VBox students;
    @FXML
    private VBox keyEvents;
    @FXML
    private VBox groups;
    //tab headings of module card
    @FXML
    private Label professorsTab;
    @FXML
    private Label studentsTab;
    @FXML
    private Label groupsTab;
    @FXML
    private Label keyEventsTab;
    private ModuleCardTab currentTab = ModuleCardTab.NONE;

    /**
     * Creates a {@code ModuleCode} with the given {@code Module} and index to display.
     */
    public ModuleCard(Module module, int displayedIndex) {
        super(FXML);
        logger.info(String.format("Instantiating module card with code %s at index %s", module.getModuleCode(),
            displayedIndex));
        this.module = module;
        id.setText(displayedIndex + ". ");
        moduleCode.setText(module.getModuleCode().toString());
        moduleName.setText(module.getModuleName().toString());

        //collect all tabs in list of tabs for easy management
        allTabContents = new ArrayList<>();
        setUpAllTabContents();

        //set up all the vbox lists, make them all invisible in the ui at the start
        setUpVBoxLists();

        //Set up all the listeners to the underlying lists of module
        setUpListChangeListeners();

        //set up mouse click handlers for each tab
        setUpTabMouseClickHandlers();
    }

    private void setUpAllTabContents() {
        //collect all tabs in list of tabs for easy management
        allTabContents.add(professors);
        allTabContents.add(students);
        allTabContents.add(groups);
        allTabContents.add(keyEvents);
    }

    /**
     * Sets up all the vbox lists which display lists of fields of a module to user.
     * Initially all Vboxes are not visible, until one tab is clicked by user.
     */
    private void setUpVBoxLists() {
        fillPaneFromList(professors, module.getProfessors(), (professor, i) ->
            new ProfessorCard(professor, i + 1).getRoot());
        fillPaneFromList(students, module.getStudents(), (student, i) ->
            new StudentCard(student, i + 1).getRoot());
        fillPaneFromList(groups, module.getGroups(), (group, i, flag) ->
            new GroupCard(group, i + 1, flag).getRoot());
        fillPaneFromList(keyEvents, module.getKeyEvents(), (keyEvent, i) ->
            new ModuleKeyEventCard(keyEvent, i + 1).getRoot());

        students.setManaged(false);
        students.setVisible(false);
        professors.setManaged(false);
        professors.setVisible(false);
        groups.setManaged(false);
        groups.setVisible(false);
        keyEvents.setManaged(false);
        keyEvents.setVisible(false);
    }

    /**
     * Sets up all the mouseevent handlers for switch tabs of the view of a module
     */
    private void setUpTabMouseClickHandlers() {
        setUpProfessorHeaderClickHandler();
        setUpStudentsHeaderClickHandler();
        setUpGroupsHeaderClickHandler();
        setUpModuleKeyEventsHeaderClickHandler();
    }

    /**
     * Sets up the mouse click handler for professor tab.
     */
    private void setUpProfessorHeaderClickHandler() {
        professorsTab.addEventHandler(MouseEvent.MOUSE_CLICKED,
            e -> {
                logger.info(String.format("Professors tab clicked. Professors of module %s now showing.",
                    module.getModuleCode()));
                changeTab(ModuleCardTab.PROFESSORS);
            });
    }

    /**
     * Sets up the mouse click handler for students tab.
     */
    private void setUpStudentsHeaderClickHandler() {
        studentsTab.addEventHandler(MouseEvent.MOUSE_CLICKED,
            event -> {
                logger.info(String.format("Students tab clicked. Students of module %s now showing.",
                    module.getModuleCode()));
                changeTab(ModuleCardTab.STUDENTS);
            });
    }

    /**
     * Sets up the mouse click handler for groups tab.
     */
    private void setUpGroupsHeaderClickHandler() {
        groupsTab.addEventHandler(MouseEvent.MOUSE_CLICKED,
            event -> {
                logger.info(
                    String.format("Groups tab clicked. Groups of module %s now showing.", module.getModuleCode()));
                changeTab(ModuleCardTab.GROUPS);
            });
    }

    /**
     * Sets up the mouse click handler for module key events tab.
     */
    private void setUpModuleKeyEventsHeaderClickHandler() {
        keyEventsTab.addEventHandler(MouseEvent.MOUSE_CLICKED,
            event -> {
                logger.info(String.format("Key events tab clicked. Key events of module %s now showing.",
                    module.getModuleCode()));
                changeTab(ModuleCardTab.KEY_EVENTS);
            });
    }

    /**
     * Triggers switch to show correct tab based on the tab clicked.
     *
     * @param tab the tab heading that was clicked.
     */
    private void changeTab(ModuleCardTab tab) {
        //make all tabs not visible first
        for (Node node : allTabContents) {
            node.setVisible(false);
            node.setManaged(false);
        }

        //same tab was clicked twice, just close the tab
        if (tab == currentTab) {
            logger.info(String.format("Changed to no tab being shown for module %s", module.getModuleCode()));
            lightenTab(tab);
            currentTab = ModuleCardTab.NONE;
            return;
        }

        lightenAllTabs();
        darkenTab(tab);

        if (tab == ModuleCardTab.STUDENTS) {
            students.setManaged(true);
            students.setVisible(true);
        } else if (tab == ModuleCardTab.PROFESSORS) {
            professors.setManaged(true);
            professors.setVisible(true);
        } else if (tab == ModuleCardTab.GROUPS) {
            groups.setManaged(true);
            groups.setVisible(true);
        } else if (tab == ModuleCardTab.KEY_EVENTS) {
            keyEvents.setManaged(true);
            keyEvents.setVisible(true);
        } else {
            throw new RuntimeException("This pathway should not happen");
        }

        logger.info(String.format("Changed to %s tab being shown for module %s", tab, module.getModuleCode()));
        currentTab = tab;
    }

    /**
     * Darkens the button of specified tab.
     */
    private void darkenTab(ModuleCardTab tab) {
        if (tab == ModuleCardTab.STUDENTS) {
            studentsTab.getStyleClass().add("module-student-list-heading-pressed");
        } else if (tab == ModuleCardTab.PROFESSORS) {
            professorsTab.getStyleClass().add("module-professor-list-heading-pressed");
        } else if (tab == ModuleCardTab.GROUPS) {
            groupsTab.getStyleClass().add("module-groups-list-heading-pressed");
        } else if (tab == ModuleCardTab.KEY_EVENTS) {
            keyEventsTab.getStyleClass().add("module-key-events-list-heading-pressed");
        } else {
            throw new RuntimeException("This pathway should not happen");
        }
    }

    /**
     * Lightens the button of specified tab when unpressed.
     */
    private void lightenTab(ModuleCardTab tab) {
        if (tab == ModuleCardTab.STUDENTS) {
            studentsTab.getStyleClass().removeIf(style -> style.equals("module-student-list-heading-pressed"));
        } else if (tab == ModuleCardTab.PROFESSORS) {
            professorsTab.getStyleClass().removeIf(style -> style.equals("module-professor-list-heading-pressed"));
        } else if (tab == ModuleCardTab.GROUPS) {
            groupsTab.getStyleClass().removeIf(style -> style.equals("module-groups-list-heading-pressed"));
        } else if (tab == ModuleCardTab.KEY_EVENTS) {
            keyEventsTab.getStyleClass().removeIf(style -> style.equals("module-key-events-list-heading-pressed"));
        } else {
            throw new RuntimeException("This pathway should not happen");
        }
    }

    /**
     * Lightens all tabs.
     */
    private void lightenAllTabs() {
        lightenTab(ModuleCardTab.STUDENTS);
        lightenTab(ModuleCardTab.PROFESSORS);
        lightenTab(ModuleCardTab.GROUPS);
        lightenTab(ModuleCardTab.KEY_EVENTS);
    }

    /**
     * Set up listeners for changes in all the tab content lists, so that
     * when a change in one of them is detected, the list shown in ui
     * can change accordingly.
     */
    private void setUpListChangeListeners() {
        addIndexedListChangeListener(professors, module.getProfessors(), (professor, index) ->
                new ProfessorCard(professor, index + 1).getRoot());
        addIndexedListChangeListener(students, module.getStudents(), (student, index) ->
                new StudentCard(student, index + 1).getRoot());
        addIndexedAndFlagListChangeListener(groups, module.getGroups(), (group, index, flag) ->
                new GroupCard(group, index + 1, flag).getRoot());
        addIndexedListChangeListener(keyEvents, module.getKeyEvents(), (keyEvent, index) ->
                new ModuleKeyEventCard(keyEvent, index + 1).getRoot());
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
        ModuleCard card = (ModuleCard) other;
        return id.getText().equals(card.id.getText())
            && module.equals(card.module);
    }

    //Enum which represents which tab of the ModuleCard is visible at a time
    public enum ModuleCardTab {
        STUDENTS, PROFESSORS, GROUPS, KEY_EVENTS, NONE;
    }

}
