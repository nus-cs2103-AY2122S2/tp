package seedu.address.logic.commands;

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
}
