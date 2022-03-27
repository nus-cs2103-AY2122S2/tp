package seedu.contax.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.contax.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALICE;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALONE;
import static seedu.contax.testutil.TypicalAppointments.getTypicalSchedule;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.Messages;
import seedu.contax.commons.core.index.Index;
import seedu.contax.model.AddressBook;
import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.UserPrefs;
import seedu.contax.model.appointment.Appointment;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteAppointmentCommand}.
 */
public class DeleteAppointmentCommandTest {

    private Model model = new ModelManager(new AddressBook(), getTypicalSchedule(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Appointment appointmentToDelete = model.getFilteredAppointmentList().get(0);
        DeleteAppointmentCommand deleteCommand = new DeleteAppointmentCommand(Index.fromZeroBased(0));

        String expectedMessage = String.format(DeleteAppointmentCommand.MESSAGE_SUCCESS,
                appointmentToDelete);

        ModelManager expectedModel = new ModelManager(new AddressBook(), model.getSchedule(), new UserPrefs());
        expectedModel.deleteAppointment(appointmentToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        Predicate<Appointment> testFilter = appointment -> appointment.equals(APPOINTMENT_ALONE);
        model.updateFilteredAppointmentList(testFilter);
        Appointment appointmentToDelete = model.getFilteredAppointmentList().get(0);
        DeleteAppointmentCommand deleteCommand = new DeleteAppointmentCommand(Index.fromZeroBased(0));

        String expectedMessage = String.format(DeleteAppointmentCommand.MESSAGE_SUCCESS,
                appointmentToDelete);

        ModelManager expectedModel = new ModelManager(new AddressBook(), model.getSchedule(), new UserPrefs());
        expectedModel.updateFilteredAppointmentList(testFilter);
        expectedModel.deleteAppointment(appointmentToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        DeleteAppointmentCommand deleteCommand = new DeleteAppointmentCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        model.updateFilteredAppointmentList(appointment -> !appointment.equals(APPOINTMENT_ALICE));
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        DeleteAppointmentCommand deleteCommand = new DeleteAppointmentCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        DeleteAppointmentCommand deleteFirstCommand = new DeleteAppointmentCommand(Index.fromZeroBased(0));
        DeleteAppointmentCommand deleteSecondCommand = new DeleteAppointmentCommand(Index.fromZeroBased(1));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteAppointmentCommand deleteFirstCommandCopy = new DeleteAppointmentCommand(Index.fromZeroBased(0));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
