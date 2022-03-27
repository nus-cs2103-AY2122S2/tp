package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.model.student.ViewDetails;

public class CommandResultTest {

    private final ViewDetails firstViewDetails = ALICE.getViewDetails();
    private final ViewDetails secondViewDetails = BENSON.getViewDetails();
    private final CommandResult commandResult = new CommandResult("feedback", false, false, true, firstViewDetails);

    @Test
    public void equals() {

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback",
                false, false, true, firstViewDetails)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false, false, null)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true, false, null)));

        // different view value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, false, null)));

        // different viewDetails value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback",
                false, false, true, secondViewDetails)));
    }

    @Test
    public void hashcode() {

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(),
                new CommandResult("feedback", false, false, true, firstViewDetails).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", true, false, true, firstViewDetails).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", false, true, true, firstViewDetails).hashCode());

        // different view value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", false, false, false, null).hashCode());

        // different viewStatus value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", false, false, true, secondViewDetails).hashCode());
    }

    @Test
    public void getViewDetails_success() {
        assertEquals(commandResult.getViewDetails(), ALICE.getViewDetails());
    }

    @Test
    public void getViewDetails_failure() {
        assertThrows(
                IllegalStateException.class, () -> (
                        new CommandResult("feedback", false, false, true, null)).getViewDetails());
    }

}
