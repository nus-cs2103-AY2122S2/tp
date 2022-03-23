package seedu.address.ui.infopanel;

import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.student.Student;
import seedu.address.ui.card.LessonCard;

public class StudentInfoPanel extends InfoPanel {
    private static final String FXML = "StudentInfoPanel.fxml";

    public final Student student;

    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private FlowPane tags;
    @FXML
    private ListView<Lesson> assignedLessonList;

    /**
     * Creates a {@code StudentInfoPanel} filled with information with the given details of {@code Student}.
     *
     * @param student Fills the {@code StudentInfoPanel} with the given details of {@code Student}.
     */
    public StudentInfoPanel(Student student) {
        super(FXML);
        this.student = student;
        setDetails(student);
    }

    private void setDetails(Student student) {
        name.setText(student.getName().fullName);
        phone.setText(student.getPhone().value);
        address.setText(student.getAddress().value);
        email.setText(student.getEmail().value);
        student.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        setAssignedLessons(FXCollections.observableList(student.getEnrolledLessons().getLessonsList()));
    }

    /**
     * Sets the assigned lessons list to the provided {@code Lesson} list.
     *
     * @param lessonList Provided lesson list.
     */
    private void setAssignedLessons(ObservableList<Lesson> lessonList) {
        assignedLessonList.setItems(lessonList);
        assignedLessonList.setCellFactory(listView -> new LessonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Lesson} using a {@code LessonCard}.
     */
    class LessonListViewCell extends ListCell<Lesson> {
        @Override
        protected void updateItem(Lesson lesson, boolean empty) {
            super.updateItem(lesson, empty);

            if (empty || lesson == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new LessonCard(lesson).getRoot());
            }
        }
    }
}
