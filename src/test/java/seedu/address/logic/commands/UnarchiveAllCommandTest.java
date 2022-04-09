package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL;
import static seedu.address.model.Model.PREDICATE_SHOW_ARCHIVED_ONLY;
import static seedu.address.model.Model.PREDICATE_SHOW_UNARCHIVED_ONLY;
import static seedu.address.testutil.TypicalEntries.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class UnarchiveAllCommandTest {
    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new UnarchiveAllCommand(), model, UnarchiveAllCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.showPersonList(PREDICATE_SHOW_ARCHIVED_ONLY);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.showPersonList(PREDICATE_SHOW_ARCHIVED_ONLY);

        expectedModel.getAddressBook().getPersonList().forEach(entry -> entry.setArchived(false));
        expectedModel.updateCurrentlyDisplayedList(PREDICATE_SHOW_ALL);
        expectedModel.updateCurrentlyDisplayedList(PREDICATE_SHOW_UNARCHIVED_ONLY);

        assertCommandSuccess(new UnarchiveAllCommand(), model, UnarchiveAllCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
