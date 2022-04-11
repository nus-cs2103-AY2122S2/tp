package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

class UndoCommandTest {
    @Test
    public void execute_undo_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        model.addPerson(ALICE);
        model.commitContent();
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_undo_fail() {
        Model model = new ModelManager();

        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_EARLIEST_VERSION);
    }
}
