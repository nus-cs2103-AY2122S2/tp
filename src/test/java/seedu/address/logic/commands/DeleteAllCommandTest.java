package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEntries.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class DeleteAllCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new DeleteAllCommand(), model, DeleteAllCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.showPersonList(Model.PREDICATE_SHOW_UNARCHIVED_ONLY);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.showPersonList(Model.PREDICATE_SHOW_UNARCHIVED_ONLY);

        while (!expectedModel.getFilteredPersonList().isEmpty()) {
            expectedModel.deletePerson(expectedModel.getFilteredPersonList().get(0));
        }

        assertCommandSuccess(new DeleteAllCommand(), model, DeleteAllCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
