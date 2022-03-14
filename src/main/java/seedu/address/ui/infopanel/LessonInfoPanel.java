package seedu.address.ui.infopanel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.student.Student;
import seedu.address.ui.StudentCard;

public class LessonInfoPanel extends InfoPanel {
    private static final String FXML = "LessonInfoPanel.fxml";

    public final Lesson lesson;

    @FXML
    private Label name;
    @FXML
    private Label subject;
    @FXML
    private Label date;
    @FXML
    private Label time;
    @FXML
    private ListView<Student> enrolledStudentsList;

    /**
     * Creates a {@code LessonInfoPanel} filled with information of the given {@code Lesson}.
     *
     * @param lesson Fills the {@code LessonInfoPanel} with the given details of {@code Lesson}.
     */
    public LessonInfoPanel(Lesson lesson) {
        super(FXML);
        this.lesson = lesson;
        setDetails(lesson);
        setEnrolledStudents(FXCollections.observableList(lesson.getEnrolledStudents().getStudentsList()));
    }

    private void setDetails(Lesson lesson) {
        name.setText(lesson.getName().fullName);
        subject.setText(lesson.getSubject().subjectName);
        date.setText(lesson.getDateTimeSlot().getDateString());
        time.setText(lesson.getDateTimeSlot().getTimeString());
    }

    /**
     * Sets the enrolled students list to the provided {@code Student} list.
     *
     * @param enrolledStudents Provided lesson list.
     */
    public void setEnrolledStudents(ObservableList<Student> enrolledStudents) {
        enrolledStudentsList.setItems(enrolledStudents);
        enrolledStudentsList.setCellFactory(listView -> new StudentListViewCell());
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
