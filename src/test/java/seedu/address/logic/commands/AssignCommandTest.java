package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.AssignCommand.MESSAGE_NO_SUCH_ID;
import static seedu.address.logic.commands.AssignCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalLessons.getTypicalLessonBook;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.student.Student;

class AssignCommandTest {

    private Model model;
    @BeforeEach
    void setUp() {
        model = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());
    }

    @Test
    void execute_assignStudent_success() {
        Index studentId = Index.fromOneBased(2);
        Student student = getTypicalStudentBook().getStudentList().get(studentId.getZeroBased());
        Index lessonId = Index.fromOneBased(1);
        Lesson lesson = getTypicalLessonBook().getLessonList().get(lessonId.getZeroBased());
        Model expectedModel = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());
        try {
            assertEquals(new AssignCommand(studentId, lessonId).execute(model),
                    new CommandResult(String.format(MESSAGE_SUCCESS, student.getName(), lesson.getName())));
        } catch (CommandException ce) {
            assertFalse(false);
        }
    }

    @Test
    void execute_assignStudent_failure() {
        Index invalidStudentId = Index.fromOneBased(8);

        Index invalidLessonId = Index.fromOneBased(5);

        Index validStudentId = Index.fromOneBased(2);
        Student student = getTypicalStudentBook().getStudentList().get(validStudentId.getZeroBased());

        Index validLessonId = Index.fromOneBased(1);
        Lesson lesson = getTypicalLessonBook().getLessonList().get(validLessonId.getZeroBased());

        Model expectedModel = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());

        try {
            assertThrows(CommandException.class, (
                ) -> new AssignCommand(invalidStudentId, validLessonId).execute(model));
            new AssignCommand(invalidStudentId, validLessonId).execute(model);
            assert false : "Should not get here";
        } catch (CommandException ce) {
            assertEquals(ce.getMessage(),
                    String.format(MESSAGE_NO_SUCH_ID, "student", invalidStudentId.getOneBased()));
        }
        try {
            assertThrows(CommandException.class, (
                ) -> new AssignCommand(validStudentId, invalidLessonId).execute(model));
            new AssignCommand(validStudentId, invalidLessonId).execute(model);
            assert false : "Should not get here";
        } catch (CommandException ce) {
            assertEquals(ce.getMessage(), String.format(MESSAGE_NO_SUCH_ID, "lesson", invalidLessonId.getOneBased()));
        }
    }
}
