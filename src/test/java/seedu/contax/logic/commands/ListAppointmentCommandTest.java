package seedu.contax.logic.commands;

import static seedu.contax.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALONE;
import static seedu.contax.testutil.TypicalAppointments.getTypicalSchedule;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.GuiListContentType;
import seedu.contax.model.AddressBook;
import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.UserPrefs;
import seedu.contax.model.appointment.Appointment;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListAppointmentCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), getTypicalSchedule(), new UserPrefs());
        expectedModel = new ModelManager(new AddressBook(), model.getSchedule(), new UserPrefs());
    }

    @Test
    public void execute_unfilteredList_listsAllAppointments() {
        assertCommandSuccess(new ListAppointmentCommand(), model, new CommandResult(
                ListAppointmentCommand.MESSAGE_SUCCESS, GuiListContentType.APPOINTMENT), expectedModel);
    }

    @Test
    public void execute_filteredList_resetFilterListsAllAppointments() {
        Predicate<Appointment> testFilter = appointment -> appointment.equals(APPOINTMENT_ALONE);
        model.updateFilteredAppointmentList(testFilter);
        assertCommandSuccess(new ListAppointmentCommand(), model, new CommandResult(
                ListAppointmentCommand.MESSAGE_SUCCESS, GuiListContentType.APPOINTMENT), expectedModel);
    }
}
