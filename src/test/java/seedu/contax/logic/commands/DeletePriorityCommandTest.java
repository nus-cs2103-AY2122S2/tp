package seedu.contax.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.contax.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.contax.testutil.TypicalAppointments.getTypicalSchedule;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.Messages;
import seedu.contax.commons.core.index.Index;
import seedu.contax.model.AddressBook;
import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.UserPrefs;
import seedu.contax.model.appointment.Priority;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeletePriorityCommand}.
 */
public class DeletePriorityCommandTest {
    private Model model = new ModelManager(new AddressBook(), getTypicalSchedule(), new UserPrefs());

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        DeletePriorityCommand deleteCommand = new DeletePriorityCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_deletePriority_success() {
        model.getFilteredAppointmentList().get(1).setPriority(Priority.HIGH);
        Model originalModel = model;

        DeletePriorityCommand deleteCommand = new DeletePriorityCommand(Index.fromZeroBased(1));

        String expectedMessage = String.format(DeletePriorityCommand.MESSAGE_DELETE_PRIORITY_SUCCESS, 2);
        model.getFilteredAppointmentList().get(1).setPriority(null);
        Model expectedModel = model;

        assertCommandSuccess(deleteCommand, originalModel, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeletePriorityCommand deleteCommand = new DeletePriorityCommand(Index.fromZeroBased(0));
        DeletePriorityCommand deleteCommand2 = new DeletePriorityCommand(Index.fromZeroBased(1));

        assertTrue(deleteCommand.equals(deleteCommand));
        assertTrue(deleteCommand.equals(new DeletePriorityCommand(Index.fromZeroBased(0))));

        assertFalse(deleteCommand.equals(deleteCommand2));
        assertFalse(deleteCommand.equals(null));
        assertFalse(deleteCommand.equals(0));
    }
}
