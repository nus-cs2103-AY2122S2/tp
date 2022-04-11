package seedu.address.ui;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.student.Student;
import seedu.address.model.studentattendance.Attendance;
import seedu.address.model.studentattendance.StudentAttendance;

//@@author Gernene
/**
 * An UI component that displays information of a {@code Lesson}.
 */
public class LessonCard extends UiPart<Region> {

    private static final String FXML = "LessonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Lesson lesson;

    @FXML
    private HBox cardPane;
    @FXML
    private Label week;
    @FXML
    private Label absentees;

    /**
     * Creates a {@code LessonCard} with the given {@code Lesson} and index to display.
     *
     * @param lesson Lesson to display information for.
     * @param displayedIndex Index of the lesson in the displayed list.
     */
    public LessonCard(Lesson lesson, int displayedIndex) {
        super(FXML);
        this.lesson = lesson;
        week.setText(lesson.getWeekId() + "");
        absentees.setText(getAbsenteeText());
    }

    /**
     * Creates a list of all absentees for this lesson.
     *
     * @return String list of all absentees.
     */
    private String getAbsenteeText() {
        List<StudentAttendance> studentAttendanceList = lesson.getStudentAttendanceList();
        List<String> absenteeStrings = studentAttendanceList.stream()
                .filter(filterAbsentees())
                .map(this::getAbsenteeString)
                .collect((Collectors.toList()));
        return String.join("\n", absenteeStrings);
    }

    /**
     * Creates an absentee string.
     *
     * @return String that represents an absentee.
     */
    private String getAbsenteeString(StudentAttendance studentAttendance) {
        Student student = studentAttendance.getStudent();
        return String.format("%s (%s)", student.getName(), student.getStudentId());
    }

    /**
     * Filters absentees from student list.
     *
     * @return Predicate to filter students who were absent for this lesson.
     */
    private Predicate<StudentAttendance> filterAbsentees() {
        return (StudentAttendance studentAttendance) -> {
            Attendance attendance = studentAttendance.getAttendance();
            boolean isPresent = attendance.value;
            return !isPresent;
        };
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LessonCard)) {
            return false;
        }

        // state check
        LessonCard card = (LessonCard) other;
        return lesson.equals(card.lesson);
    }
}
