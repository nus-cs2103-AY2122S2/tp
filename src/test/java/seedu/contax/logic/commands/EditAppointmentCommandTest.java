package seedu.contax.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_APPOINTMENT_NAME_ALONE;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_APPOINTMENT_NAME_EXTRA;
import static seedu.contax.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.contax.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALICE;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_EXTRA;
import static seedu.contax.testutil.TypicalAppointments.getTypicalSchedule;
import static seedu.contax.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.contax.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.contax.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalTime;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.Messages;
import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.contax.model.AddressBook;
import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.Schedule;
import seedu.contax.model.UserPrefs;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.testutil.AppointmentBuilder;
import seedu.contax.testutil.DateInputUtil;
import seedu.contax.testutil.EditAppointmentDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditAppointmentCommand.
 */
public class EditAppointmentCommandTest {

    private Model model = new ModelManager(new AddressBook(), getTypicalSchedule(), new UserPrefs());

    @Test
    public void execute_allFieldsExceptPersonSpecified_success() {
        Appointment editedAppointment = new AppointmentBuilder(APPOINTMENT_EXTRA).build();
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder(editedAppointment)
                .withPerson(null).build();
        EditAppointmentCommand editCommand = new EditAppointmentCommand(Index.fromOneBased(1), descriptor);

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                editedAppointment);

        Model expectedModel = new ModelManager(new AddressBook(), new Schedule(model.getSchedule()),
                new UserPrefs());
        expectedModel.setAppointment(model.getFilteredAppointmentList().get(0), editedAppointment);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allFieldsWithPersonSpecified_success() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalSchedule(), new UserPrefs());
        Appointment editedAppointment = new AppointmentBuilder(APPOINTMENT_EXTRA)
                .withPerson(getTypicalAddressBook().getPersonList().get(1)).build();
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder(editedAppointment)
                .withPerson(INDEX_SECOND_PERSON).build();
        EditAppointmentCommand editCommand = new EditAppointmentCommand(Index.fromOneBased(1), descriptor);

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                editedAppointment);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new Schedule(model.getSchedule()),
                new UserPrefs());
        expectedModel.setAppointment(model.getFilteredAppointmentList().get(0), editedAppointment);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        Index indexLastPosition = Index.fromOneBased(model.getFilteredAppointmentList().size());
        Appointment lastAppointment = model.getFilteredAppointmentList().get(indexLastPosition.getZeroBased());

        Appointment editedAppointment = new AppointmentBuilder(lastAppointment)
                .withName(VALID_APPOINTMENT_NAME_EXTRA).build();

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withName(VALID_APPOINTMENT_NAME_EXTRA).build();
        EditAppointmentCommand editCommand = new EditAppointmentCommand(indexLastPosition, descriptor);

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                editedAppointment);

        Model expectedModel = new ModelManager(new AddressBook(), new Schedule(model.getSchedule()),
                new UserPrefs());
        expectedModel.setAppointment(lastAppointment, editedAppointment);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_success() {
        EditAppointmentCommand editCommand = new EditAppointmentCommand(INDEX_FIRST_PERSON,
                new EditAppointmentDescriptor());
        Appointment targetAppointment = model.getFilteredAppointmentList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_NOT_EDITED, targetAppointment);

        Model expectedModel = new ModelManager(new AddressBook(), new Schedule(model.getSchedule()),
                new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_overlappingAppointment_failure() {
        Index targetIndex = Index.fromOneBased(1);
        Appointment firstAppointment = model.getFilteredAppointmentList().get(targetIndex.getZeroBased());
        LocalTime modifiedOverlappingTime = firstAppointment.getStartDateTime().toLocalTime()
                .minusMinutes(1);
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder(firstAppointment)
                .withStartTime(DateInputUtil.formatTimeToInputString(modifiedOverlappingTime))
                .build();
        EditAppointmentCommand editCommand = new EditAppointmentCommand(Index.fromOneBased(2), descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_APPOINTMENTS_OVERLAPPING);
    }

    @Test
    public void execute_overlappingAppointmentFilteredList_failure() {
        model.addAppointment(APPOINTMENT_EXTRA);
        Predicate<Appointment> testPredicate = appointment -> !appointment.equals(APPOINTMENT_ALICE);
        model.updateFilteredAppointmentList(testPredicate);

        Index targetIndex = Index.fromOneBased(1);
        Appointment firstAppointment = model.getFilteredAppointmentList().get(targetIndex.getZeroBased());
        LocalTime modifiedOverlappingTime = firstAppointment.getStartDateTime().toLocalTime()
                .minusMinutes(1);
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder(firstAppointment)
                .withStartTime(DateInputUtil.formatTimeToInputString(modifiedOverlappingTime))
                .build();
        EditAppointmentCommand editCommand = new EditAppointmentCommand(Index.fromOneBased(2), descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_APPOINTMENTS_OVERLAPPING);
    }

    @Test
    public void execute_invalidAppointmentIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withName(VALID_APPOINTMENT_NAME_ALONE).build();
        EditAppointmentCommand editCommand = new EditAppointmentCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidAppointmentIndexFilteredList_failure() {
        Predicate<Appointment> testPredicate = appointment -> !appointment.equals(APPOINTMENT_ALICE);
        model.updateFilteredAppointmentList(testPredicate);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withName(VALID_APPOINTMENT_NAME_ALONE).build();
        EditAppointmentCommand editCommand = new EditAppointmentCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndex_failure() {
        Index validAppointmentIndex = Index.fromOneBased(1);
        Index outOfBoundsPersonIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withPerson(outOfBoundsPersonIndex).build();
        EditAppointmentCommand editCommand = new EditAppointmentCommand(validAppointmentIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        EditAppointmentDescriptor refDescriptor = new EditAppointmentDescriptorBuilder()
                .withName(VALID_APPOINTMENT_NAME_ALONE).build();
        EditAppointmentDescriptor differentDescriptor = new EditAppointmentDescriptorBuilder(refDescriptor)
                .withName("Another Name").build();
        EditAppointmentCommand refCommand = new EditAppointmentCommand(Index.fromOneBased(1), refDescriptor);

        // same values -> returns true
        EditAppointmentDescriptor copyDescriptor = new EditAppointmentDescriptor(refDescriptor);
        EditAppointmentCommand commandWithSameValues =
                new EditAppointmentCommand(Index.fromOneBased(1), copyDescriptor);
        assertTrue(refCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(refCommand.equals(refCommand));

        // null -> returns false
        assertFalse(refCommand.equals(null));

        // different types -> returns false
        assertFalse(refCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(refCommand.equals(new EditAppointmentCommand(Index.fromOneBased(2), refDescriptor)));

        // different descriptor -> returns false
        assertFalse(refCommand.equals(new EditAppointmentCommand(Index.fromOneBased(1), differentDescriptor)));
    }

    @Test
    public void editAppointmentDescriptorEqualsTest() {
        EditAppointmentDescriptor refDescriptor = new EditAppointmentDescriptorBuilder()
                .withName(VALID_APPOINTMENT_NAME_ALONE).build();
        EditAppointmentDescriptor copyDescriptor = new EditAppointmentDescriptor(refDescriptor);

        assertTrue(refDescriptor.equals(copyDescriptor));
        assertTrue(refDescriptor.equals(refDescriptor));

        assertFalse(refDescriptor.equals(null));
        assertFalse(refDescriptor.equals(1));
        assertFalse(refDescriptor.equals(new EditAppointmentDescriptorBuilder(refDescriptor)
                .withName("Another Name").build()));
        assertFalse(refDescriptor.equals(new EditAppointmentDescriptorBuilder(refDescriptor)
                .withStartDate("12-02-2000").build()));
        assertFalse(refDescriptor.equals(new EditAppointmentDescriptorBuilder(refDescriptor)
                .withStartTime("05:43").build()));
        assertFalse(refDescriptor.equals(new EditAppointmentDescriptorBuilder(refDescriptor)
                .withDuration(5).build()));
        assertFalse(refDescriptor.equals(new EditAppointmentDescriptorBuilder(refDescriptor)
                .withPerson(Index.fromOneBased(20)).build()));
    }

    @Test
    public void editAppointmentDescriptorPersonModifiedTest() {
        EditAppointmentDescriptor refDescriptor = new EditAppointmentDescriptorBuilder().build();
        EditAppointmentDescriptor refDescriptor2 = new EditAppointmentDescriptorBuilder(refDescriptor).build();

        assertFalse(refDescriptor.isPersonModified());
        assertFalse(refDescriptor2.isPersonModified());

        refDescriptor.setPersonIndex(Index.fromOneBased(2));
        refDescriptor2.setPersonIndex(null);

        assertTrue(refDescriptor.isPersonModified());
        assertTrue(refDescriptor2.isPersonModified());
        assertTrue(refDescriptor.isAnyFieldEdited());
        assertTrue(refDescriptor2.isAnyFieldEdited());
    }

}
