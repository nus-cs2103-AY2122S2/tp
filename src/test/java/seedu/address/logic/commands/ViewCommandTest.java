package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showRecipeAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_RECIPE;
import static seedu.address.testutil.TypicalRecipes.AGLIO_OLIO;
import static seedu.address.testutil.TypicalRecipes.FRIED_RICE;
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
* {@code ViewCommand}.
*/
public class ViewCommandTest {

    private Model model = new ModelManager(getTypicalRecipeBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalRecipeBook(), new UserPrefs());

    @BeforeEach
    public void init() {
        model = new ModelManager(getTypicalRecipeBook(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Recipe recipeToView = model.getFilteredRecipeList().get(INDEX_FIRST_RECIPE.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_RECIPE);
        CommandResult expectedCommandResult = new CommandResult(recipeToView);

        assertCommandSuccess(viewCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_validNameUnfilteredList_success() {
        Recipe recipeToView = AGLIO_OLIO;
        ViewCommand viewCommand = new ViewCommand(recipeToView.getName());
        CommandResult expectedCommandResult = new CommandResult(recipeToView);

        assertCommandSuccess(viewCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidNameUnfilteredList_success() {
        Name nameOfRecipeToView = new Name("This recipe name does not exist");
        ViewCommand viewCommand = new ViewCommand(nameOfRecipeToView);
        String errorMessage = String.format(Messages.MESSAGE_RECIPE_NOT_FOUND, nameOfRecipeToView.fullName);

        assertCommandFailure(viewCommand, model, errorMessage);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRecipeList().size() + 1);
        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INDEX_NOT_EXIST);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showRecipeAtIndex(model, INDEX_FIRST_RECIPE);

        Index outOfBoundIndex = INDEX_SECOND_RECIPE;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getRecipeBook().getRecipeList().size());

        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INDEX_NOT_EXIST);
    }

    @Test
    public void execute_nonCaseSensitiveName_success() {
        Recipe recipeToView = AGLIO_OLIO;
        String recipeNameLowerCase = recipeToView.getName().fullName.toLowerCase();

        // Ensures that the String representation of recipe is not the same case as test.
        assertFalse(recipeToView.getName().fullName.equals(recipeNameLowerCase));

        ViewCommand viewCommand = new ViewCommand(new Name(recipeNameLowerCase));
        CommandResult expectedCommandResult = new CommandResult(recipeToView);

        assertCommandSuccess(viewCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        ViewCommand viewFirstCommand = new ViewCommand(INDEX_FIRST_RECIPE);
        ViewCommand viewFirstCommandCopy = new ViewCommand(INDEX_FIRST_RECIPE);
        ViewCommand viewSecondCommand = new ViewCommand(INDEX_SECOND_RECIPE);
        ViewCommand viewNameCommand = new ViewCommand(AGLIO_OLIO.getName());
        ViewCommand viewSecondNameCommand = new ViewCommand(FRIED_RICE.getName());
        ViewCommand viewNameCommandCopy = new ViewCommand(AGLIO_OLIO.getName());

        assertNotEquals(viewFirstCommand, viewSecondCommand);
        assertNotEquals(viewFirstCommand, null);
        assertNotEquals(viewNameCommand, viewSecondNameCommand);
        assertEquals(viewFirstCommand, viewFirstCommandCopy);
        assertEquals(viewFirstCommand, viewFirstCommand);
        assertEquals(viewNameCommand, viewNameCommandCopy);
    }
}
