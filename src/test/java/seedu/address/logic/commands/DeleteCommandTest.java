package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showRecipeAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_RECIPE;
import static seedu.address.testutil.TypicalRecipes.AGLIO_OLIO;
import static seedu.address.testutil.TypicalRecipes.getTypicalRecipeBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Recipe;

/**
* Contains integration tests (interaction with the Model) and unit tests for
* {@code DeleteCommand}.
*/
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalRecipeBook(), new UserPrefs());

    @BeforeEach
    public void init() {
        model = new ModelManager(getTypicalRecipeBook(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Recipe recipeToDelete = model.getFilteredRecipeList().get(INDEX_FIRST_RECIPE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_RECIPE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_RECIPE_SUCCESS, recipeToDelete.getName());

        ModelManager expectedModel = new ModelManager(model.getRecipeBook(), new UserPrefs());
        expectedModel.deleteRecipe(recipeToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validNameUnfilteredList_success() {
        Index indexOfRecipe = Index.fromZeroBased(model.getFilteredRecipeList().indexOf(AGLIO_OLIO));
        Recipe recipeToDelete = model.getFilteredRecipeList().get(indexOfRecipe.getZeroBased());

        DeleteCommand deleteCommand = new DeleteCommand(recipeToDelete.getName());
        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_RECIPE_SUCCESS, recipeToDelete.getName());

        ModelManager expectedModel = new ModelManager(model.getRecipeBook(), new UserPrefs());
        expectedModel.deleteRecipe(recipeToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_invalidNameUnfilteredList_success() {
        Name nameOfRecipeToDelete = new Name("fjsndksf");
        DeleteCommand deleteCommand = new DeleteCommand(nameOfRecipeToDelete);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_DELETE_RECIPE_NOT_EXIST);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRecipeList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_RECIPE_INDEX);
    }


    @Test
    public void execute_validIndexFilteredList_success() {
        showRecipeAtIndex(model, INDEX_FIRST_RECIPE);

        Recipe recipeToDelete = model.getFilteredRecipeList().get(INDEX_FIRST_RECIPE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_RECIPE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_RECIPE_SUCCESS, recipeToDelete.getName());

        Model expectedModel = new ModelManager(model.getRecipeBook(), new UserPrefs());
        expectedModel.deleteRecipe(recipeToDelete);
        showNoRecipe(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showRecipeAtIndex(model, INDEX_FIRST_RECIPE);

        Index outOfBoundIndex = INDEX_SECOND_RECIPE;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getRecipeBook().getRecipeList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_RECIPE_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_RECIPE);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_RECIPE);
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(deleteFirstCommand.getToDeleteIndex());
        DeleteCommand deleteNameCommand = new DeleteCommand(AGLIO_OLIO.getName());
        DeleteCommand deleteNameCommandCopy = new DeleteCommand(deleteNameCommand.getToDeleteName());

        assertNotEquals(deleteFirstCommand, deleteFirstCommand.getToDeleteIndex());
        assertNotEquals(deleteNameCommand, deleteNameCommand.getToDeleteName());
        assertEquals(deleteFirstCommand, deleteFirstCommand);
        assertEquals(deleteNameCommand, deleteNameCommand);
        assertEquals(deleteNameCommand, deleteNameCommandCopy);
        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
        assertNotEquals(deleteFirstCommand, deleteNameCommand);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoRecipe(Model model) {
        model.updateFilteredRecipeList(p -> false);

        assertTrue(model.getFilteredRecipeList().isEmpty());
    }
}
