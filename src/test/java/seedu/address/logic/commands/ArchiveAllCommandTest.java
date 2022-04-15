package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL;
import static seedu.address.model.Model.PREDICATE_SHOW_ARCHIVED_ONLY;
import static seedu.address.testutil.TypicalEntries.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ArchiveAllCommandTest {
    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ArchiveAllCommand(), model, ArchiveAllCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        expectedModel.getAddressBook().getCompanyList().forEach(entry -> entry.setArchived(true));
        expectedModel.updateCurrentlyDisplayedList(PREDICATE_SHOW_ALL);
        expectedModel.updateCurrentlyDisplayedList(PREDICATE_SHOW_ARCHIVED_ONLY);

        assertCommandSuccess(new ArchiveAllCommand(), model, ArchiveAllCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
