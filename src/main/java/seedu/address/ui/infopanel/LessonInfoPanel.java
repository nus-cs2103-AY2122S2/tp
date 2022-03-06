package seedu.address.ui.infopanel;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;
import seedu.address.ui.PersonCard;

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
    private ListView<Person> enrolledStudentsList;

    /**
     * Creates a {@code LessonInfoPanel} filled with information of the given {@code Lesson}
     *
     * @param lesson Fills the {@code LessonInfoPanel} with the given details of {@code Lesson}
     */
    public LessonInfoPanel(Lesson lesson) {
        super(FXML);
        this.lesson = lesson;
        setDetails(lesson);
    }

    private void setDetails(Lesson lesson) {
        name.setText(lesson.getName().fullName);
        subject.setText(lesson.getSubject().subjectName);
        date.setText(lesson.getTimeSlot().getDateString());
        time.setText(lesson.getTimeSlot().getTimeString());
    }

    /**
     * Sets the enrolled students list to the provided {@code Person} list.
     *
     * @param enrolledStudents Provided lesson list
     */
    public void setEnrolledStudents(ObservableList<Person> enrolledStudents) {
        enrolledStudentsList.setItems(enrolledStudents);
        enrolledStudentsList.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }
}
