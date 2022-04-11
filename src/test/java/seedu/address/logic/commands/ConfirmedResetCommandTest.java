package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalRecipes.getTypicalRecipeBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyRecipeBook;
import seedu.address.model.RecipeBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.util.SampleDataUtil;

/**
 * Contains integration tests (interaction with the Model) and unit tests for.
 * {@code ConfirmedResetCommand}.
 */
public class ConfirmedResetCommandTest {
    private final Model model = new ModelManager(getTypicalRecipeBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalRecipeBook(), new UserPrefs());

    @Test
    public void execute_recipeBookReset_success() {
        ConfirmedResetCommand command = new ConfirmedResetCommand();
        ReadOnlyRecipeBook readOnlyBeforeReset = model.getRecipeBook();
        RecipeBook beforeReset = new RecipeBook(readOnlyBeforeReset);

        expectedModel.setRecipeBook(SampleDataUtil.getSampleRecipeBook());
        String expectedMessage = "Recipe book has been reset!";

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getRecipeBook(), expectedModel.getRecipeBook());
        assertNotEquals(beforeReset, new RecipeBook(model.getRecipeBook()));
    }

    @Test
    public void equals_test() {
        ResetCommand reset = new ResetCommand();
        ConfirmedResetCommand r1 = new ConfirmedResetCommand();
        ConfirmedResetCommand r2 = new ConfirmedResetCommand();
        assertEquals(r1, r1);
        assertEquals(r1, r2);
        assertNotEquals(r1, reset);
    }

}
