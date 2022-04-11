package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalStudents.getTypicalStudentBook;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewLessonInfoCommand}
 */
public class ViewLessonInfoCommandTest {
    private Model model = new ModelManager(getTypicalStudentBook(), new UserPrefs());

    // TODO: Can't test for lesson as TypicalStudents.java does not include lessons yet
    /*  @Test
        public void execute_validIndexUnfilteredList_success() {
            Student selectedStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
            ViewLessonInfoCommand viewCommand = new ViewLessonInfoCommand(INDEX_FIRST_STUDENT);

            String expectedMessage = String.format(ViewLessonInfoCommand.MESSAGE_VIEW_SUCCESS, selectedStudent);

            ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
            expectedModel.setSelectedStudent(selectedStudent);

            assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
        }
    */
}
