package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Reminder;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

class RemindCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RemindCommand(null, Optional.of(new Reminder("reminder"))));
    }

    @Test
    public void constructor_nullReminder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RemindCommand(Index.fromZeroBased(0), null));
    }

    @Test
    public void execute_createEditAndRemoveReminder_addEditRemoveSuccessful() throws Exception {
        /* because of the singleton nature of ReminderPersons,
        the addition, edit and removal of a Reminder are localized */

        // adding a new Reminder
        Person personToRemind = model.getFilteredPersonList().get(0);

        CommandResult commandResult = new RemindCommand(Index.fromZeroBased(0),
                Optional.of(new Reminder("apartment viewing"))).execute(model);

        assertEquals(String.format(RemindCommand.MESSAGE_REMIND_PERSON_SUCCESS, personToRemind),
                commandResult.getFeedbackToUser());

        // editing an existing Reminder
        personToRemind = model.getFilteredPersonList().get(0);

        commandResult = new RemindCommand(Index.fromZeroBased(0),
                Optional.of(new Reminder("finalizing leasing options"))).execute(model);

        assertEquals(String.format(RemindCommand.MESSAGE_EDIT_REMIND_PERSON_SUCCESS, personToRemind),
                commandResult.getFeedbackToUser());

        // removing an existing Reminder
        personToRemind = model.getFilteredPersonList().get(0);

        commandResult = new RemindCommand(Index.fromZeroBased(0),
                Optional.empty()).execute(model);

        assertEquals(String.format(RemindCommand.MESSAGE_UNREMIND_PERSON_SUCCESS, personToRemind),
                commandResult.getFeedbackToUser());
    }


    @Test
    public void execute_wrongIndex_throwsCommandException() throws Exception {
        Index index = Index.fromZeroBased(7);
        RemindCommand remindCommand = new RemindCommand(index, Optional.of(new Reminder("sign contract")));

        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, () -> remindCommand.execute(model));
    }
}
