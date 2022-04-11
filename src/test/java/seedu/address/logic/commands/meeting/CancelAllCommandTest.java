package seedu.address.logic.commands.meeting;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class CancelAllCommandTest {
    @Test
    public void execute_emptyMeetingBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new CancelAllCommand(), model, CancelAllCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyMeetingBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), getTypicalMeetingBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalMeetingBook(), new UserPrefs());

        assertCommandSuccess(new CancelAllCommand(), model, CancelAllCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
