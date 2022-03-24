package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.GroupBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code ViewTaskCommand}.
 */
public class ViewTaskCommandTest {

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ViewTaskCommand(null));
    }

    @Test
    public void equals() {
        final ViewTaskCommand standardCommand = new ViewTaskCommand(new GroupBuilder()
                .withGroupName("NUS Fintech Society").build());

        // same value --> returns true
        ViewTaskCommand commandWithSameValues = new ViewTaskCommand(new GroupBuilder()
                .withGroupName("NUS Fintech Society").build());

        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object --> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null --> return false
        assertFalse(standardCommand.equals(null));

        // different group -> returns false
        assertFalse(standardCommand.equals(new ViewTaskCommand(new GroupBuilder()
                .withGroupName("NUS Data Science Society").build())));
    }
}
