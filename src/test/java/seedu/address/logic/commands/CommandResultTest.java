package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.model.student.Student;

public class CommandResultTest {

    private final Student firstStudent = ALICE;
    private final Student secondStudent = BENSON;
    private final CommandResult commandResult = new CommandResult("feedback", false, false, firstStudent);

    @Test
    public void equals() {

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback",
                false, false, firstStudent)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false, null)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true, null)));

        // different view value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, null)));

        // different viewDetails value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback",
                false, false, secondStudent)));
    }

    @Test
    public void hashcode() {

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(),
                new CommandResult("feedback", false, false, firstStudent).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", true, false, firstStudent).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", false, true, firstStudent).hashCode());

        // different view value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", false, false, null).hashCode());

        // different viewStatus value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", false, false, secondStudent).hashCode());
    }

    @Test
    public void getStudent_isViewStudentExists_success() {
        assertEquals(
                new CommandResult("feedback", false, false, firstStudent).getStudent(),
                firstStudent
        );
    }

    @Test
    public void getStudent_studentDoesNotExists_throwsIllegalStateException() {
        assertThrows(IllegalStateException.class, () ->
                new CommandResult("feedback", false, false, null).getStudent()
        );
    }

    @Test
    public void isView_test() {
        assertTrue(new CommandResult("feedback", false, false, firstStudent).isView());
        assertFalse(new CommandResult("feedback").isView());
    }

    @Test
    public void isShowHelp_test() {
        assertTrue(new CommandResult("feedback", true, false, null).isShowHelp());
        assertFalse(new CommandResult("feedback").isView());
    }

    @Test
    public void isExit_test() {
        assertTrue(new CommandResult("feedback", false, true, null).isExit());
        assertFalse(new CommandResult("feedback").isView());
    }

}
