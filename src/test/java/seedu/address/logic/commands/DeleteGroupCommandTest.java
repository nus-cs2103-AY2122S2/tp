package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalGroups.getTypicalAddressBook;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteGroupCommand}.
 */
public class DeleteGroupCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
}
