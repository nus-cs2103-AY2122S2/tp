package seedu.contax.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.contax.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.contax.testutil.TypicalAppointments.getTypicalSchedule;
import static seedu.contax.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.Messages;
import seedu.contax.commons.core.index.Index;
import seedu.contax.model.AddressBook;
import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.UserPrefs;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.Priority;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditPriorityCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalSchedule(), new UserPrefs());

    @Test
    public void execute_priorityLow_success() {
        Priority priority = Priority.LOW;
        Index index = Index.fromOneBased(1);
        EditPriorityCommand command = new EditPriorityCommand(index, Priority.LOW);

        String expectedMessage = String.format(
                EditPriorityCommand.MESSAGE_EDIT_PRIORITY_SUCCESS, index.getOneBased(), priority.toString());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), getTypicalSchedule(),
                new UserPrefs());
        Appointment appointment = expectedModel.getFilteredAppointmentList().get(0);
        Appointment expectAppointment = appointment.withPriority(Priority.LOW);
        expectedModel.setAppointment(appointment, expectAppointment);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_priorityMedium_success() {
        Priority priority = Priority.MEDIUM;
        Index index = Index.fromOneBased(1);
        EditPriorityCommand command = new EditPriorityCommand(index, Priority.MEDIUM);

        String expectedMessage = String.format(
                EditPriorityCommand.MESSAGE_EDIT_PRIORITY_SUCCESS, index.getOneBased(), priority.toString());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), getTypicalSchedule(),
                new UserPrefs());
        Appointment appointment = expectedModel.getFilteredAppointmentList().get(0);
        Appointment expectAppointment = appointment.withPriority(Priority.MEDIUM);
        expectedModel.setAppointment(appointment, expectAppointment);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_priorityHigh_success() {
        Priority priority = Priority.HIGH;
        Index index = Index.fromOneBased(1);
        EditPriorityCommand command = new EditPriorityCommand(index, Priority.HIGH);

        String expectedMessage = String.format(
                EditPriorityCommand.MESSAGE_EDIT_PRIORITY_SUCCESS, index.getOneBased(), priority.toString());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), getTypicalSchedule(),
                new UserPrefs());
        Appointment appointment = expectedModel.getFilteredAppointmentList().get(0);
        Appointment expectAppointment = appointment.withPriority(Priority.HIGH);
        expectedModel.setAppointment(appointment, expectAppointment);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index index = Index.fromOneBased(5);
        EditPriorityCommand command = new EditPriorityCommand(index, Priority.HIGH);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {

        Index first = Index.fromOneBased(1);
        Index second = Index.fromOneBased(2);
        EditPriorityCommand command = new EditPriorityCommand(first, Priority.HIGH);
        EditPriorityCommand command2 = new EditPriorityCommand(first, Priority.LOW);
        EditPriorityCommand command3 = new EditPriorityCommand(second, Priority.HIGH);
        EditPriorityCommand command4 = new EditPriorityCommand(second, Priority.LOW);

        assertTrue(command.equals(command));
        assertTrue(command.equals(new EditPriorityCommand(first, Priority.HIGH)));

        // Different index and descriptors
        assertFalse(command.equals(command2));

        // Different descriptors
        assertFalse(command.equals(command3));

        // Different index
        assertFalse(command.equals(command4));

        assertFalse(command.equals(null));
        assertFalse(command.equals(0));
    }
}
