package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model) for {@code DeleteFilteredCommandTest}.
 */
class DeleteFilteredCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_deleteUnfilteredList_success() {
        DeleteFilteredCommand deleteCommand = new DeleteFilteredCommand();

        String expectedMessage = String.format(DeleteFilteredCommand.MESSAGE_PERSONS_DELETED_OVERVIEW,
                model.getFilteredPersonList().size());

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }
}
