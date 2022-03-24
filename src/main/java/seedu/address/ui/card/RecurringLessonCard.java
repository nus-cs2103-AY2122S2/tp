package seedu.address.ui.card;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.address.model.lesson.Lesson;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class RecurringLessonCard extends Card {

    private static final String FXML = "RecurringLessonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on StudentBook level 4</a>
     */

    public final Lesson lesson;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label subject;
    @FXML
    private Label date;
    @FXML
    private Label time;
    @FXML
    private Label day;

    /**
     * Creates a {@code LessonCard} with the given {@code Lesson} and index to display.
     */
    public RecurringLessonCard(Lesson lesson, int displayedIndex) {
        super(FXML);
        this.lesson = lesson;
        id.setText(displayedIndex + ". ");
        name.setText(lesson.getName().fullName);
        subject.setText(lesson.getSubject().subjectName);
        date.setText(lesson.getDateTimeSlot().getDateString());
        time.setText(lesson.getDateTimeSlot().getTimeString());
        day.setText(lesson.getDateTimeSlot().getDayString());
    }

    /**
     * Creates a {@code LessonCard} with the given {@code Lesson} with no index.
     */
    public RecurringLessonCard(Lesson lesson) {
        super(FXML);
        this.lesson = lesson;
        id.setText("");
        name.setText(lesson.getName().fullName);
        subject.setText(lesson.getSubject().subjectName);
        date.setText(lesson.getDateTimeSlot().getDateString());
        time.setText(lesson.getDateTimeSlot().getTimeString());
        day.setText(lesson.getDateTimeSlot().getDayString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecurringLessonCard)) {
            return false;
        }

        // state check
        RecurringLessonCard card = (RecurringLessonCard) other;
        return id.getText().equals(card.id.getText())
                && lesson.equals(card.lesson);
    }
}
