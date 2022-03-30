package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model) for {@code ClearFilteredCommandTest}.
 */
class ClearFilteredCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_clearUnfilteredList_success() {
        ClearFilteredCommand deleteCommand = new ClearFilteredCommand();

        String expectedMessage = String.format(ClearFilteredCommand.MESSAGE_PERSONS_DELETED_OVERVIEW,
                model.getFilteredPersonList().size());

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void equals() {
        ClearFilteredCommand command = new ClearFilteredCommand();
        ClearFilteredCommand otherCommand = new ClearFilteredCommand();

        assertTrue(command.equals(command));

        assertFalse(command.equals(otherCommand));
    }
}
