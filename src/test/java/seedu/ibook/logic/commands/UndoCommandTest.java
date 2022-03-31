package seedu.ibook.logic.commands;

import static seedu.ibook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.ibook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_A;
import static seedu.ibook.testutil.TypicalProducts.getTypicalIBook;

import org.junit.jupiter.api.Test;

import seedu.ibook.model.Model;
import seedu.ibook.model.ModelManager;
import seedu.ibook.model.UserPrefs;

public class UndoCommandTest {

    @Test
    public void execute_emptyIBook_throwsAtOldestStateException() {
        Model model = new ModelManager();

        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_OLDEST_STATE);
    }

    @Test
    public void execute_nonEmptyIBookWithNoChanges_throwsAtOldestStateException() {
        Model model = new ModelManager(getTypicalIBook(), new UserPrefs());

        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_OLDEST_STATE);
    }

    @Test
    public void execute_nonEmptyIBookWithChanges_success() {
        Model model = new ModelManager(getTypicalIBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalIBook(), new UserPrefs());

        model.prepareIBookForChanges();
        model.addProduct(PRODUCT_A);
        model.saveIBookChanges();

        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_UNDO_SUCCESS, expectedModel);
    }
}
