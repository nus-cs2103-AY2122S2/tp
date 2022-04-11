package seedu.address.ui;

import java.util.Map;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.assessment.Grade;
import seedu.address.model.student.Student;

//@@author Gernene
/**
 * Controller for an assessment attempts popup.
 */
public class AssessmentAttemptsWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "AssessmentAttemptsWindow.fxml";

    @FXML
    private ListView<AssessmentAttempt> attemptListView;

    /**
     * Creates a new popup to display assessment attempts.
     *
     * @param attempts Map of attempts to display.
     */
    public AssessmentAttemptsWindow(Map<Student, Grade> attempts) {
        super(FXML, new Stage());
        ObservableList<AssessmentAttempt> attemptList = FXCollections.observableArrayList();
        attempts.forEach((student, grade) -> {
            attemptList.add(new AssessmentAttempt(student, grade));
        });
        attemptListView.setItems(attemptList);
        attemptListView.setCellFactory(listView -> new AssessmentAttemptListViewCell());
    }

    /**
     * Shows the attempts window.
     * @throws IllegalStateException
     */
    public void show() {
        logger.fine("Showing assessment attempts page.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the attempts window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the attempts window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the attempts window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code AssessmentAttempt} using a
     * {@code AssessmentAttemptCard}.
     */
    class AssessmentAttemptListViewCell extends ListCell<AssessmentAttempt> {
        @Override
        protected void updateItem(AssessmentAttempt attempt, boolean empty) {
            super.updateItem(attempt, empty);

            if (empty || attempt == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AssessmentAttemptCard(attempt, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Class which represents an assessment attempt.
     */
    class AssessmentAttempt {
        private Student student;
        private Grade grade;

        AssessmentAttempt(Student student, Grade grade) {
            this.student = student;
            this.grade = grade;
        }

        public Student getStudent() {
            return student;
        }

        public Grade getGrade() {
            return grade;
        }
    }
}
