package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_FELIX;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_FELIX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_FELIX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAppointmentAtIndex;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static seedu.address.testutil.TypicalInsurances.getTypicalInsuranceBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalRecords.getTypicalRecordBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.model.AppointmentBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalInsuranceBook(),
            getTypicalAppointmentBook(), getTypicalRecordBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Appointment editedAppointment = new AppointmentBuilder().build();
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder(editedAppointment).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_OBJECT, descriptor);

        String expectedMessage =
                String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment);

        Model expectedModel = new ModelManager(new AppointmentBook(model.getAppointmentBook()), new UserPrefs());
        expectedModel.setAppointment(model.getFilteredAppointmentList().get(0), editedAppointment);

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastAppointment = Index.fromOneBased(model.getFilteredAppointmentList().size());
        Appointment lastAppointment = model.getFilteredAppointmentList().get(indexLastAppointment.getZeroBased());

        AppointmentBuilder appointmentInList = new AppointmentBuilder(lastAppointment);
        Appointment editedAppointment = appointmentInList.withDescription(VALID_DESCRIPTION_FELIX)
                .withDateTime(VALID_DATETIME_FELIX)
                .build();

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_FELIX).withDateTime(VALID_DATETIME_FELIX).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(indexLastAppointment, descriptor);

        String expectedMessage =
                String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment);

        Model expectedModel = new ModelManager(new AppointmentBook(model.getAppointmentBook()), new UserPrefs());
        expectedModel.setAppointment(lastAppointment, editedAppointment);

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditAppointmentCommand editAppointmentCommand =
                new EditAppointmentCommand(INDEX_FIRST_OBJECT, new EditAppointmentDescriptor());
        Appointment editedAppointment = model.getFilteredAppointmentList().get(INDEX_FIRST_OBJECT.getZeroBased());

        String expectedMessage =
                String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment);

        Model expectedModel = new ModelManager(new AppointmentBook(model.getAppointmentBook()), new UserPrefs());

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showAppointmentAtIndex(model, INDEX_FIRST_OBJECT);

        Appointment appointmentInFilteredList = model.getFilteredAppointmentList()
                .get(INDEX_FIRST_OBJECT.getZeroBased());
        Appointment editedAppointment = new AppointmentBuilder(appointmentInFilteredList)
                .withDescription(VALID_DESCRIPTION_FELIX).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_OBJECT,
                new EditAppointmentDescriptorBuilder().withDescription(VALID_DESCRIPTION_FELIX).build());

        String expectedMessage =
                String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment);

        Model expectedModel = new ModelManager(new AppointmentBook(model.getAppointmentBook()), new UserPrefs());
        expectedModel.setAppointment(model.getFilteredAppointmentList().get(0), editedAppointment);

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateAppointmentUnfilteredList_failure() {
        Appointment firstAppointment = model.getFilteredAppointmentList().get(INDEX_FIRST_OBJECT.getZeroBased());
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder(firstAppointment).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_SECOND_OBJECT, descriptor);

        assertCommandFailure(editAppointmentCommand, model, EditAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT);
    }

    @Test
    public void execute_duplicateAppointmentFilteredList_failure() {
        showAppointmentAtIndex(model, INDEX_FIRST_OBJECT);

        // edit appointment in filtered list into a duplicate in address book
        Appointment appointmentInList = model.getAppointmentBook().getAppointmentList()
                .get(INDEX_SECOND_OBJECT.getZeroBased());
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_OBJECT,
                new EditAppointmentDescriptorBuilder(appointmentInList).build());

        assertCommandFailure(editAppointmentCommand, model, EditAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT);
    }

    @Test
    public void execute_invalidAppointmentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_FELIX).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editAppointmentCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidAppointmentIndexFilteredList_failure() {
        showAppointmentAtIndex(model, INDEX_FIRST_OBJECT);
        Index outOfBoundIndex = INDEX_SECOND_OBJECT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAppointmentBook().getAppointmentList().size());

        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(outOfBoundIndex,
                new EditAppointmentDescriptorBuilder().withDescription(VALID_DESCRIPTION_FELIX).build());

        assertCommandFailure(editAppointmentCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditAppointmentCommand standardCommand = new EditAppointmentCommand(INDEX_FIRST_OBJECT, DESC_TED);

        // same values -> returns true
        EditAppointmentDescriptor copyDescriptor = new EditAppointmentDescriptor(DESC_TED);
        EditAppointmentCommand commandWithSameValues = new EditAppointmentCommand(INDEX_FIRST_OBJECT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditAppointmentCommand(INDEX_SECOND_OBJECT, DESC_TED)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditAppointmentCommand(INDEX_FIRST_OBJECT, DESC_FELIX)));
    }

}
