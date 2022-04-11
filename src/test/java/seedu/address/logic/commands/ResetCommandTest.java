package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalRecipes.getTypicalRecipeBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for.
 * {@code ResetCommand}.
 */
public class ResetCommandTest {
    private final Model model = new ModelManager(getTypicalRecipeBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalRecipeBook(), new UserPrefs());

    @Test
    public void execute_showsRequireConfirmationMessage() {
        ResetCommand command = new ResetCommand();
        String expectedMessage = ResetCommand.MESSAGE_CONFIRMATION_REQUIRED;

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    //Tests for resetCommand equality
    @Test
    public void equals_test() {
        ResetCommand r1 = new ResetCommand();
        ResetCommand r2 = new ResetCommand();
        ConfirmedResetCommand confirmedReset = new ConfirmedResetCommand();
        CancelResetCommand cancelReset = new CancelResetCommand();
        assertEquals(r1, r1);
        assertEquals(r1, r2);
        assertNotEquals(r1, confirmedReset);
        assertNotEquals(r1, cancelReset);
    }

}
