package unibook.ui.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
import unibook.model.module.ModuleKeyEvent;
import unibook.model.module.group.Group;
import unibook.model.person.Professor;
import unibook.model.person.Student;
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

    public final Module module;

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

    private final List<Node> allTabContents;


    //Tracks which field of Module is visible at a time
    public enum ModuleCardTab {
        STUDENTS, PROFESSORS, GROUPS, KEY_EVENTS, NONE;
    }

    private ModuleCardTab currentTab = ModuleCardTab.NONE;

    private final Logger logger = LogsCenter.getLogger(ModuleCard.class);

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
        allTabContents.add(professors);
        allTabContents.add(students);
        allTabContents.add(groups);
        allTabContents.add(keyEvents);

        //Set up all the listeners to the underlying lists of module
        setUpListChangeListeners();

        //set up mouse click handlers for each tab
        setUpTabMouseClickHandlers();
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
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    logger.info(String.format("Professors tab clicked. Professors of module %s now showing.", module.getModuleCode()));
                    changeTab(ModuleCardTab.PROFESSORS);
                }
            });
    }

    /**
     * Sets up the mouse click handler for students tab.
     */
    private void setUpStudentsHeaderClickHandler() {
        studentsTab.addEventHandler(MouseEvent.MOUSE_CLICKED,
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    logger.info(String.format("Students tab clicked. Students of module %s now showing.", module.getModuleCode()));
                    changeTab(ModuleCardTab.STUDENTS);
                }
            });
    }

    /**
     * Sets up the mouse click handler for groups tab.
     */
    private void setUpGroupsHeaderClickHandler() {
        groupsTab.addEventHandler(MouseEvent.MOUSE_CLICKED,
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    logger.info(String.format("Groups tab clicked. Groups of module %s now showing.", module.getModuleCode()));
                    changeTab(ModuleCardTab.GROUPS);
                }
            });
    }

    /**
     * Sets up the mouse click handler for module key events tab.
     */
    private void setUpModuleKeyEventsHeaderClickHandler() {
        keyEventsTab.addEventHandler(MouseEvent.MOUSE_CLICKED,
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    logger.info(String.format("Key events tab clicked. Key events of module %s now showing.", module.getModuleCode()));
                    changeTab(ModuleCardTab.KEY_EVENTS);
                }
            });
    }

    /**
     * Triggers switch to show correct tab based on the tab clicked.
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
            currentTab = ModuleCardTab.NONE;
            return;
        }

        if (tab == ModuleCardTab.STUDENTS) {
            fillStudentList();
            students.setManaged(true);
            students.setVisible(true);
        } else if (tab == ModuleCardTab.PROFESSORS) {
            fillProfessorList();
            professors.setManaged(true);
            professors.setVisible(true);
        } else if (tab == ModuleCardTab.GROUPS) {
            fillGroupList();
            groups.setManaged(true);
            groups.setVisible(true);
        } else if (tab == ModuleCardTab.KEY_EVENTS) {
            fillKeyEventList();
            keyEvents.setManaged(true);
            keyEvents.setVisible(true);
        } else {
            throw new RuntimeException("This pathway should not happen");
        }

        logger.info(String.format("Changed to %s tab being shown for module %s", tab, module.getModuleCode()));
        currentTab = tab;
    }

    /**
     * Set up listeners for changes in all the tab content lists, so that
     * when a change in one of them is detected, the list shown in ui
     * can change accordingly.
     */
    private void setUpListChangeListeners() {
        ObservableList<Professor> modelProfessorList = module.getProfessors();
        ObservableList<Student> modelStudentList = module.getStudents();
        ObservableList<Group> modelGroupList = module.getGroups();
        ObservableList<ModuleKeyEvent> modelKeyEventsList = module.getKeyEvents();

        //add listeners to the underlying lists of module objects
        //so that the list shown in this card will change on update
        modelProfessorList.addListener(new ListChangeListener<Professor>() {
            /**
             * Rebuild the list of profs shown to viewer on any kind of change
             */
            @Override
            public void onChanged(Change<? extends Professor> c) {
                fillProfessorList();
            }
        });

        modelStudentList.addListener(new ListChangeListener<Student>() {
            @Override
            public void onChanged(Change<? extends Student> c) {
                fillStudentList();
            }
        });

        modelGroupList.addListener(new ListChangeListener<Group>() {
            @Override
            public void onChanged(Change<? extends Group> c) {
                fillGroupList();
            }
        });

        modelKeyEventsList.addListener(new ListChangeListener<ModuleKeyEvent>() {
            @Override
            public void onChanged(Change<? extends ModuleKeyEvent> c) {
                fillKeyEventList();
            }
        });
    }

    /**
     * Fills the professor card list of this module card.
     */
    private void fillProfessorList() {
        ObservableList<Professor> modelProfessorList = module.getProfessors();
        int index = 0;
        ArrayList<Node> professorCards = new ArrayList<>();
        for (Professor prof : modelProfessorList) {
            index++;
            professorCards.add(new ProfessorCard(prof, index).getRoot());
        }
        professors.getChildren().setAll(professorCards);
    }

    /**
     * Fills the student card list of this module card.
     */
    private void fillStudentList() {
        ObservableList<Student> modelStudentList = module.getStudents();
        int index = 0;
        ArrayList<Node> studentCards = new ArrayList<>();
        for (Student student : modelStudentList) {
            index++;
            studentCards.add(new StudentCard(student, index).getRoot());
        }
        students.getChildren().setAll(studentCards);
    }

    /**
     * Fills the group card list of this module card.
     */
    private void fillGroupList() {
        ObservableList<Group> modelGroupList = module.getGroups();
        int index = 0;
        ArrayList<Node> groupCards = new ArrayList<>();
        for (Group group : modelGroupList) {
            index++;
            groupCards.add(new GroupCard(group, index, modelGroupList.size() == 1).getRoot());
        }
        groups.getChildren().setAll(groupCards);
    }

    /**
     * Fills the key event list of this module card.
     */
    private void fillKeyEventList() {
        ObservableList<ModuleKeyEvent> modelModuleKeyEventList = module.getKeyEvents();
        int index = 0;
        ArrayList<Node> moduleKeyEventCards = new ArrayList<>();
        for (ModuleKeyEvent keyEvent : modelModuleKeyEventList) {
            index++;
            moduleKeyEventCards.add(new ModuleKeyEventCard(keyEvent, index).getRoot());
        }
        keyEvents.getChildren().setAll(moduleKeyEventCards);
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

}
