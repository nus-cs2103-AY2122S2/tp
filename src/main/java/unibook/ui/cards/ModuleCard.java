package unibook.ui.cards;

import java.util.ArrayList;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import unibook.model.module.Module;
import unibook.model.person.Professor;
import unibook.model.person.Student;
import unibook.ui.UiPart;

/**
 * A UI component that displays information of {@code Module}
 */
public class ModuleCard extends UiPart<Region> {

    private static final String FXML = "cards/ModuleListCard.fxml";

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

    /**
     * Creates a {@code ModuleCode} with the given {@code Module} and index to display.
     */
    public ModuleCard(Module module, int displayedIndex) {
        super(FXML);
        this.module = module;
        id.setText(displayedIndex + ". ");
        moduleCode.setText(module.getModuleCode().toString());
        moduleName.setText(module.getModuleName().toString());

        fillProfessorList();
        fillStudentList();

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
