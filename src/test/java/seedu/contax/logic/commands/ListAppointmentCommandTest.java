package seedu.contax.logic.commands;

import static seedu.contax.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.contax.testutil.TypicalAppointments.getTypicalSchedule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.GuiListContentType;
import seedu.contax.model.AddressBook;
import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.UserPrefs;

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
    public void execute_valid_listsAllAppointments() {
        assertCommandSuccess(new ListAppointmentCommand(), model, new CommandResult(
                ListAppointmentCommand.MESSAGE_SUCCESS, GuiListContentType.APPOINTMENT), expectedModel);
    }
}
