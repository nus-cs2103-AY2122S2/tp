package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.model.client.SortCriteria;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false, false, false, false, null, null)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false, false, false, false,
                null, null)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, true, false, false,
                null, null)));

        // same sort criteria -> returns true
        commandResult = new CommandResult("feedback", SortCriteria.DEFAULT);
        assertTrue(commandResult.equals(new CommandResult("feedback", SortCriteria.DEFAULT)));

        // same index to show -> returns true
        commandResult = new CommandResult("feedback", INDEX_FIRST_CLIENT);
        assertTrue(commandResult.equals(new CommandResult("feedback", INDEX_FIRST_CLIENT)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false, false, false, false,
                null, null).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false, true, false, false,
                null, null).hashCode());
    }

    @Test
    public void test_toString() {
        CommandResult toTest = new CommandResult("feedback");
        assertTrue(toTest.toString().equals("feedback"));
    }

    @Test
    public void test_isMethods() {
        CommandResult toTest = new CommandResult("feedback");

        // is not show help
        assertFalse(toTest.isShowHelp());

        // is not show tutorial
        assertFalse(toTest.isShowTutorial());

        // is not exit
        assertFalse(toTest.isExit());

        // is not show meetings
        assertFalse(toTest.isShowMeetings());

        // is not show clients
        assertFalse(toTest.isShowClients());

        // is not show client
        assertFalse(toTest.isShowClient());

        // is not update client
        assertFalse(toTest.isUpdateClient());

        // is not delete client
        assertFalse(toTest.isDeleteClient());

        // is not sort clients
        assertFalse(toTest.isSortClients());
    }

    @Test
    public void test_getMethods() {
        CommandResult toTest = new CommandResult("feedback");

        // index to show is null
        assertTrue(toTest.getIndexToShow() == null);

        // client to delete is null
        assertTrue(toTest.getClientToDelete() == null);

        // client to update is null
        assertTrue(toTest.getClientToUpdate() == null);

        // sort criteria is null
        assertTrue(toTest.getSortCriteria() == null);
    }
}
