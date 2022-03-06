package unibook.ui.cards;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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
    private ListView<Professor> professors;
    @FXML
    private ListView<Student> students;

    /**
     * Creates a {@code ModuleCode} with the given {@code Module} and index to display.
     */
    public ModuleCard(Module module, int displayedIndex) {
        super(FXML);
        this.module = module;
        id.setText(displayedIndex + ". ");
        moduleCode.setText(module.getModuleCode().toString());
        moduleName.setText(module.getModuleName().toString());

        //add all the professors of the module to professors listview
        professors.setItems(module.getProfessors());
        professors.setCellFactory(Professor -> new ProfessorListViewCell());

        //add all the students of the module to students listview
        students.setItems(module.getStudents());
        students.setCellFactory(Student -> new StudentListViewCell());
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

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Professor} using a {@code ProfessorCard}.
     */
    class ProfessorListViewCell extends ListCell<Professor> {
        @Override
        protected void updateItem(Professor professor, boolean empty) {
            super.updateItem(professor, empty);

            if (empty || professor == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ProfessorCard(professor, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code StudentCard}.
     */
    class StudentListViewCell extends ListCell<Student> {
        @Override
        protected void updateItem(Student student, boolean empty) {
            super.updateItem(student, empty);

            if (empty || student == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StudentCard(student, getIndex() + 1).getRoot());
            }
        }
    }


}


