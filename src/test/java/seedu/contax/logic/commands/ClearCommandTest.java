package seedu.contax.logic.commands;

import static seedu.contax.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.contax.testutil.TypicalAppointments.getTypicalSchedule;
import static seedu.contax.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.contax.model.AddressBook;
import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.Schedule;
import seedu.contax.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBookEmptySchedule_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBookEmptySchedule_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new Schedule(), new UserPrefs());
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_emptyAddressBookNonEmptySchedule_success() {
        Model model = new ModelManager(new AddressBook(), getTypicalSchedule(), new UserPrefs());
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBookAndSchedule_success() {
        Model model = new ModelManager(getTypicalAddressBook(), getTypicalSchedule(), new UserPrefs());
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
