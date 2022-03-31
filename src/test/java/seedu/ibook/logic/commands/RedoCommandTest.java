package seedu.ibook.logic.commands;

import static seedu.ibook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.ibook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_A;
import static seedu.ibook.testutil.TypicalProducts.getTypicalIBook;

import org.junit.jupiter.api.Test;

import seedu.ibook.model.Model;
import seedu.ibook.model.ModelManager;
import seedu.ibook.model.UserPrefs;

public class RedoCommandTest {

    @Test
    public void execute_emptyIBook_throwsAtLatestStateException() {
        Model model = new ModelManager();

        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_LATEST_STATE);
    }

    @Test
    public void execute_nonEmptyIBookWithNoChanges_throwsAtLatestStateException() {
        Model model = new ModelManager(getTypicalIBook(), new UserPrefs());

        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_LATEST_STATE);
    }

    @Test
    public void execute_nonEmptyIBookWithUndoneChanges_success() {
        Model model = new ModelManager(getTypicalIBook(), new UserPrefs());
        model.prepareIBookForChanges();
        model.addProduct(PRODUCT_A);
        model.saveIBookChanges();

        Model expectedModel = new ModelManager(model.getIBook(), new UserPrefs());
        model.undoIBook();

        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_REDO_SUCCESS, expectedModel);
    }
}
