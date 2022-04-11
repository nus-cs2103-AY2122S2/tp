package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.lesson.Lesson;

//@@author Gernene
/**
 * An UI component that displays information of a {@code ClassGroup}.
 */
public class ClassGroupCard extends UiPart<Region> {

    private static final String FXML = "ClassGroupListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final ClassGroup classGroup;

    private AttendanceWindow attendanceWindow;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label classId;
    @FXML
    private Label classType;
    @FXML
    private Label moduleCode;
    @FXML
    private Label moduleName;
    @FXML
    private Label academicYear;

    /**
     * Creates a {@code ClassGroupCard} with the given {@code ClassGroup} and index to display.
     * 
     * @param classGroup {@code ClassGroup} to display info for.
     * @param displayedIndex Index of the class group in the displayed list.
     */
    public ClassGroupCard(ClassGroup classGroup, int displayedIndex) {
        super(FXML);
        this.classGroup = classGroup;
        id.setText(displayedIndex + "");
        classId.setText(classGroup.getClassGroupId().value);
        classType.setText(classGroup.getClassGroupType().toString());
        moduleCode.setText(classGroup.getModule().getModuleCode().value);
        moduleName.setText(classGroup.getModule().getModuleName().value);
        academicYear.setText(classGroup.getModule().getAcademicYear().value);
        ObservableList<Lesson> lessonList = FXCollections.observableArrayList();
        classGroup.getLessons().forEach((lesson) -> {
            lessonList.add(lesson);
        });

        attendanceWindow = new AttendanceWindow(lessonList);
    }

    /**
     * Opens the attendance window or focuses on it if it's already opened.
     */
    @FXML
    public void handleAttendance() {
        if (!attendanceWindow.isShowing()) {
            attendanceWindow.show();
        } else {
            attendanceWindow.focus();
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClassGroupCard)) {
            return false;
        }

        // state check
        ClassGroupCard card = (ClassGroupCard) other;
        return id.getText().equals(card.id.getText())
                && classGroup.equals(card.classGroup);
    }
}
