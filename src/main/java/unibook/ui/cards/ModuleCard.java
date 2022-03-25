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
        STUDENTS, PROFESSORS, GROUPS, KEYEVENTS, NONE;
    }

    private ModuleCardTab currentTab = ModuleCardTab.NONE;

    private final Logger logger = LogsCenter.getLogger(ModuleCard.class);

    /**
     * Creates a {@code ModuleCode} with the given {@code Module} and index to display.
     */
    public ModuleCard(Module module, int displayedIndex) {
        super(FXML);
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

        ObservableList<Professor> modelProfessorList = module.getProfessors();
        ObservableList<Student> modelStudentList = module.getStudents();

        //add listeners to the underlying prof and student lists of module object
        //so that the list shown in this card will change on update
        modelProfessorList.addListener(new ListChangeListener<Professor>() {
            /**
             * Rebuild the list of profs shown to viewer on any kind of change
             * @param c
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
    }

    /**
     * Sets up all the mouseevent handlers for switch tabs of the view of a module
     */
    private void setUpTabMouseClickHandlers() {
        Pane.addEventHandler(MouseEvent.MOUSE_CLICKED,
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

    private void setUpProfessorHeaderClickHandler() {
        professorTab.addEventHandler(MouseEvent.MOUSE_CLICKED,
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    logger.info(String.format("Professors tab clicked. Professors of module %s now showing.", module.getModuleCode());
                    currentTab = ModuleCardTab.PROFESSORS;
                    professors.setVisible);
                    professors.setManaged(additionalDetailsVisible);
                }
            });
        additionalDetailsVisible = false;
    }

    /**
     * Triggers switch to show given tab of module.
     * @param tab tab to show.
     */
    private void changeToTab(ModuleCardTab tab) {
        //make all tabs not visible first
        for (Node node : allTabContents) {
            node.setVisible(false);
            node.setManaged(false);
        }

        if (tab == ModuleCardTab.STUDENTS) {
            students.setManaged(true);
            students.setVisible(true);
        } else if (tab == ModuleCardTab.PROFESSORS) {
            professors.setManaged(true);
            professors.setVisible(true);
        } else if (tab == ModuleCardTab.GROUPS) {
            groups.setManaged(true);
            groups.setVisible(true);
        } else if (tab == ModuleCardTab.KEYEVENTS) {
            keyEvents.setManaged(true);
            keyEvents.setVisible(true);
        } else {
            throw new RuntimeException("This pathway should not happen");
        }

        currentTab = tab;
    }

    /**
     * Set up handlers for all the
     */
    private void setUpListChangeListeners() {
        ObservableList<Professor> modelProfessorList = module.getProfessors();
        ObservableList<Student> modelStudentList = module.getStudents();

        //add listeners to the underlying prof and student lists of module object
        //so that the list shown in this card will change on update
        modelProfessorList.addListener(new ListChangeListener<Professor>() {
            /**
             * Rebuild the list of profs shown to viewer on any kind of change
             * @param c
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
