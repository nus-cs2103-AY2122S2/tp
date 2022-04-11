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
 * {@code CancelResetCommand}.
 */
public class CancelResetCommandTest {
    private final Model model = new ModelManager(getTypicalRecipeBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalRecipeBook(), new UserPrefs());

    @Test
    public void execute_showsCancellationMessage_success() {
        CancelResetCommand command = new CancelResetCommand();
        String expectedMessage = "Reset has been cancelled!";
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals_test() {
        ResetCommand reset = new ResetCommand();
        CancelResetCommand r1 = new CancelResetCommand();
        CancelResetCommand r2 = new CancelResetCommand();
        assertEquals(r1, r1);
        assertEquals(r1, r2);
        assertNotEquals(r1, reset);
    }

}
