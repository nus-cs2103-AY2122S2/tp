package seedu.address.ui.infopanel;

import java.util.Comparator;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;
import seedu.address.ui.LessonCard;

public class PersonInfoPanel extends InfoPanel {
    private static final String FXML = "PersonInfoPanel.fxml";

    public final Person person;

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
     * Creates a {@code PersonInfoPanel} filled with information with the given details of {@code Person}
     *
     * @param person Fills the {@code PersonInfoPanel} with the given details of {@code Person}
     */
    public PersonInfoPanel(Person person) {
        super(FXML);
        this.person = person;
        setDetails(person);
    }

    private void setDetails(Person person) {
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        // TODO: Add function to list assigned classes after implementing assigned lesson list in Person
    }

    // TODO: This is public temporarily (Person doesn't hold assigned lessons)

    /**
     * Sets the assigned lessons list to the provided {@code Lesson} list
     *
     * @param lessonList Provided lesson list
     */
    public void setAssignedLessons(ObservableList<Lesson> lessonList) {
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
                setGraphic(new LessonCard(lesson, getIndex() + 1).getRoot());
            }
        }
    }
}
