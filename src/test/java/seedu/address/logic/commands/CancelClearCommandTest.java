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
 * {@code CancelClearCommand}.
 */
public class CancelClearCommandTest {
    private final Model model = new ModelManager(getTypicalRecipeBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalRecipeBook(), new UserPrefs());

    @Test
    public void execute_showsCancellationMessage_success() {
        CancelClearCommand command = new CancelClearCommand();
        String expectedMessage = "Clear has been cancelled!";
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals_test() {
        ClearCommand clear = new ClearCommand();
        CancelClearCommand c1 = new CancelClearCommand();
        CancelClearCommand c2 = new CancelClearCommand();
        assertEquals(c1, c1);
        assertEquals(c1, c2);
        assertNotEquals(c1, clear);
    }

}
