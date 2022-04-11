package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tasks.PriorityTaskList;
import seedu.address.testutil.ContactListBuilder;

class RedoCommandTest {
    @Test
    public void execute_redo_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager(new ContactListBuilder().withPerson(ALICE).build(),
                new UserPrefs(), new PriorityTaskList());

        model.addPerson(ALICE);
        model.commitContent();
        model.undoContents();
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_redo_fail() {
        Model model = new ModelManager();

        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_LATEST_VERSION);
    }
}
