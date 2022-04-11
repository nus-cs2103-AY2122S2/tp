package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
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
import seedu.address.storage.ReminderPersons;

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
    public void execute_wrongIndex_throwsCommandException() throws Exception {
        Index index = Index.fromZeroBased(7);
        RemindCommand remindCommand = new RemindCommand(index, Optional.of(new Reminder("sign contract")));

        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, () -> remindCommand.execute(model));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() throws CommandException {
        // reset the ReminderPersons for testing
        ReminderPersons reminderPersons = ReminderPersons.getInstance();
        reminderPersons.setTestingInstance();

        Person personToRemind = model.getFilteredAndSortedPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        // creating a new Reminder for a Person
        RemindCommand remindCommand = new RemindCommand(INDEX_FIRST_PERSON,
                Optional.of(new Reminder("contact client for property details")));

        String expectedMessage = String.format(RemindCommand.MESSAGE_REMIND_PERSON_SUCCESS, personToRemind.getName());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        CommandResult commandResultNewReminder = remindCommand.execute(expectedModel);

        assertEquals(expectedMessage, commandResultNewReminder.getFeedbackToUser());

        // editing an existing Reminder for a Person
        RemindCommand editRemindCommand = new RemindCommand(INDEX_FIRST_PERSON,
                Optional.of(new Reminder("meet client for home viewing")));

        expectedMessage = String.format(RemindCommand.MESSAGE_EDIT_REMIND_PERSON_SUCCESS, personToRemind.getName());

        CommandResult commandResultEditReminder = editRemindCommand.execute(expectedModel);

        assertEquals(expectedMessage, commandResultEditReminder.getFeedbackToUser());

        // removing an existing Reminder for a Person
        RemindCommand deleteRemindCommand = new RemindCommand(INDEX_FIRST_PERSON,
                Optional.empty());

        expectedMessage = String.format(RemindCommand.MESSAGE_UNREMIND_PERSON_SUCCESS, personToRemind.getName());

        CommandResult commandResultDeleteReminder = deleteRemindCommand.execute(expectedModel);

        assertEquals(expectedMessage, commandResultDeleteReminder.getFeedbackToUser());
    }
}
