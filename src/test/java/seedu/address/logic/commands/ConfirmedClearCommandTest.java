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

/**
 * Contains integration tests (interaction with the Model) and unit tests for.
 * {@code ConfirmedClearCommand}.
 */
public class ConfirmedClearCommandTest {
    private final Model model = new ModelManager(getTypicalRecipeBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalRecipeBook(), new UserPrefs());

    @Test
    public void execute_recipeBookCleared() {
        ConfirmedClearCommand command = new ConfirmedClearCommand();
        ReadOnlyRecipeBook readOnlyBeforeClear = model.getRecipeBook();
        RecipeBook beforeClear = new RecipeBook(readOnlyBeforeClear);
        expectedModel.setRecipeBook(new RecipeBook());
        String expectedMessage = "Recipe book has been cleared!";
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getRecipeBook(), expectedModel.getRecipeBook());
        assertNotEquals(beforeClear, new RecipeBook(model.getRecipeBook()));
    }

    @Test
    public void equals_test() {
        ClearCommand clear = new ClearCommand();
        ConfirmedClearCommand c1 = new ConfirmedClearCommand();
        ConfirmedClearCommand c2 = new ConfirmedClearCommand();
        assertEquals(c1, c1);
        assertEquals(c1, c2);
        assertNotEquals(c1, clear);
    }

}
