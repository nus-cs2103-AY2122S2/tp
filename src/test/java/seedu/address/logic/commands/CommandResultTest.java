package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.DisplayType;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");
        CommandResult studentCommandResult = CommandResult.createStudentCommandResult("feedback");
        CommandResult assessmentCommandResult = CommandResult.createAssessmentCommandResult("feedback");
        CommandResult attendanceCommandResult = CommandResult.createAttendanceCommandResult("feedback");
        CommandResult attendanceCommandResultCopy = CommandResult.createAttendanceCommandResult("feedback");
        CommandResult attendanceByStudentCommandResult =
                CommandResult.createAttendanceByStudentCommandResult("feedback");
        CommandResult tutorialCommandResult = CommandResult.createClassCommandResult("feedback");
        CommandResult scoreCommandResult = CommandResult.createScoreCommandResult("feedback");
        CommandResult commentCommandResult = CommandResult.createCommentCommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback",
                false, false, DisplayType.PERSON)));
        assertTrue(studentCommandResult.equals(new CommandResult("feedback",
                false, false, DisplayType.STUDENT)));
        assertTrue(assessmentCommandResult.equals(new CommandResult("feedback",
                false, false, DisplayType.ASSESSMENT)));
        assertTrue(attendanceCommandResult.equals(attendanceCommandResultCopy));
        attendanceCommandResult.setAttendanceWeek(2);
        attendanceCommandResultCopy.setAttendanceWeek(2);
        assertTrue(attendanceCommandResult.equals(attendanceCommandResultCopy));
        assertTrue(attendanceByStudentCommandResult.equals(new CommandResult("feedback",
                false, false, DisplayType.ATTENDANCE_BY_STUDENT)));

        assertTrue(tutorialCommandResult.equals(new CommandResult("feedback",
                false, false, DisplayType.CLASS)));
        assertTrue(scoreCommandResult.equals(new CommandResult("feedback",
                false, false, DisplayType.SCORE)));
        assertTrue(commentCommandResult.equals(new CommandResult("feedback",
                false, false, DisplayType.COMMENT)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback",
                true, false, DisplayType.PERSON)));

        // different attendance week value -> returns false
        attendanceCommandResultCopy.setAttendanceWeek(3);
        assertFalse(attendanceCommandResult.equals(attendanceCommandResultCopy));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback",
                false, true, DisplayType.PERSON)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");
        CommandResult studentCommandResult = CommandResult.createStudentCommandResult("feedback");
        CommandResult assessmentCommandResult = CommandResult.createAssessmentCommandResult("feedback");
        CommandResult attendanceCommandResult = CommandResult.createAttendanceCommandResult("feedback");
        CommandResult attendanceCommandResultCopy = CommandResult.createAttendanceCommandResult("feedback");
        CommandResult attendanceByStudentCommandResult =
                CommandResult.createAttendanceByStudentCommandResult("feedback");
        CommandResult tutorialCommandResult = CommandResult.createClassCommandResult("feedback");
        CommandResult scoreCommandResult = CommandResult.createScoreCommandResult("feedback");
        CommandResult commentCommandResult = CommandResult.createCommentCommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());
        assertEquals(studentCommandResult.hashCode(), new CommandResult("feedback",
                false, false, DisplayType.STUDENT).hashCode());
        assertEquals(assessmentCommandResult.hashCode(), new CommandResult("feedback",
                false, false, DisplayType.ASSESSMENT).hashCode());
        assertEquals(attendanceCommandResult.hashCode(), attendanceCommandResultCopy.hashCode());
        attendanceCommandResult.setAttendanceWeek(2);
        attendanceCommandResultCopy.setAttendanceWeek(2);
        assertEquals(attendanceCommandResult.hashCode(), attendanceCommandResultCopy.hashCode());
        assertEquals(attendanceByStudentCommandResult.hashCode(), new CommandResult("feedback",
                false, false, DisplayType.ATTENDANCE_BY_STUDENT).hashCode());
        assertEquals(tutorialCommandResult.hashCode(), new CommandResult("feedback",
                false, false, DisplayType.CLASS).hashCode());
        assertEquals(scoreCommandResult.hashCode(), new CommandResult("feedback",
                false, false, DisplayType.SCORE).hashCode());
        assertEquals(commentCommandResult.hashCode(), new CommandResult("feedback",
                false, false, DisplayType.COMMENT).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true,
                false, DisplayType.PERSON).hashCode());

        // different attendance week value -> returns different hashcode
        attendanceCommandResultCopy.setAttendanceWeek(3);
        assertNotEquals(attendanceCommandResult.hashCode(), attendanceCommandResultCopy.hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false,
                true, DisplayType.PERSON).hashCode());
    }

    @Test
    public void getDisplayType() {
        CommandResult commandResult = new CommandResult("feedback");
        CommandResult studentCommandResult = CommandResult.createStudentCommandResult("feedback");
        CommandResult assessmentCommandResult = CommandResult.createAssessmentCommandResult("feedback");
        CommandResult attendanceCommandResult = CommandResult.createAttendanceCommandResult("feedback");
        CommandResult attendanceByStudentCommandResult =
                CommandResult.createAttendanceByStudentCommandResult("feedback");
        CommandResult tutorialCommandResult = CommandResult.createClassCommandResult("feedback");
        CommandResult scoreCommandResult = CommandResult.createScoreCommandResult("feedback");
        CommandResult commentCommandResult = CommandResult.createCommentCommandResult("feedback");

        assertEquals(commandResult.getDisplayType(), DisplayType.PERSON);
        assertEquals(studentCommandResult.getDisplayType(), DisplayType.STUDENT);
        assertEquals(assessmentCommandResult.getDisplayType(), DisplayType.ASSESSMENT);
        assertEquals(attendanceCommandResult.getDisplayType(), DisplayType.ATTENDANCE);
        assertEquals(attendanceByStudentCommandResult.getDisplayType(), DisplayType.ATTENDANCE_BY_STUDENT);
        assertEquals(tutorialCommandResult.getDisplayType(), DisplayType.CLASS);
        assertEquals(scoreCommandResult.getDisplayType(), DisplayType.SCORE);
        assertEquals(commentCommandResult.getDisplayType(), DisplayType.COMMENT);
    }

    @Test
    public void getInquiry() {
        CommandResult commandResult = new CommandResult("feedback", true,
                false, DisplayType.STUDENT, "add");
        assertEquals(commandResult.getInquiry(), "add");
        assertEquals(commandResult.isShowHelp(), true);
        assertEquals(commandResult.isInquiry(), true);
        assertEquals(commandResult.isExit(), false);
    }

}
