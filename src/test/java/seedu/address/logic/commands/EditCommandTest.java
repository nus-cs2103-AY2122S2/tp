package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETION_TIME_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_CHICKEN_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_POTATO_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SERVING_SIZE_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STEP_1_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showRecipeAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_RECIPE;
import static seedu.address.testutil.TypicalRecipes.getTypicalRecipeBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditRecipeDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.RecipeBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.EditRecipeDescriptorBuilder;
import seedu.address.testutil.RecipeBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private final ReadOnlyUserPrefs roup = new UserPrefs();
    private final RecipeBook recipeBook = getTypicalRecipeBook();
    private Model model = new ModelManager(getTypicalRecipeBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Recipe editedRecipe = new RecipeBuilder().build();
        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder(editedRecipe).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_RECIPE, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe.getName());

        Model expectedModel = new ModelManager(new RecipeBook(model.getRecipeBook()), new UserPrefs());
        expectedModel.setRecipe(model.getFilteredRecipeList().get(0), editedRecipe);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastRecipe = Index.fromOneBased(model.getFilteredRecipeList().size());
        Recipe lastRecipe = model.getFilteredRecipeList().get(indexLastRecipe.getZeroBased());

        RecipeBuilder recipeInList = new RecipeBuilder(lastRecipe);
        Recipe editedRecipe = recipeInList
                .withName(VALID_NAME_CHICKEN_CHOP)
                .withCompletionTime(VALID_COMPLETION_TIME_CHICKEN_CHOP)
                .withServingSize(VALID_SERVING_SIZE_CHICKEN_CHOP)
                .withIngredients(VALID_INGREDIENT_CHICKEN_CHICKEN_CHOP,
                        VALID_INGREDIENT_POTATO_CHICKEN_CHOP)
                .withSteps(VALID_STEP_1_CHICKEN_CHOP)
                .withTags(VALID_TAG_CHICKEN_CHOP).build();

        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder()
                .withName(VALID_NAME_CHICKEN_CHOP)
                .withCompletionTime(VALID_COMPLETION_TIME_CHICKEN_CHOP)
                .withServingSize(VALID_SERVING_SIZE_CHICKEN_CHOP)
                .withIngredients(VALID_INGREDIENT_CHICKEN_CHICKEN_CHOP,
                        VALID_INGREDIENT_POTATO_CHICKEN_CHOP)
                .withSteps(VALID_STEP_1_CHICKEN_CHOP)
                .withTags(VALID_TAG_CHICKEN_CHOP).build();

        EditCommand editCommand = new EditCommand(indexLastRecipe, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe.getName());

        Model expectedModel = new ModelManager(new RecipeBook(model.getRecipeBook()), new UserPrefs());
        expectedModel.setRecipe(lastRecipe, editedRecipe);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_RECIPE, new EditRecipeDescriptor());
        Recipe editedRecipe = model.getFilteredRecipeList().get(INDEX_FIRST_RECIPE.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe.getName());

        Model expectedModel = new ModelManager(new RecipeBook(model.getRecipeBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showRecipeAtIndex(model, INDEX_FIRST_RECIPE);

        Recipe recipeInFilteredList = model.getFilteredRecipeList().get(INDEX_FIRST_RECIPE.getZeroBased());
        Recipe editedRecipe = new RecipeBuilder(recipeInFilteredList)
                .withName(VALID_NAME_AGLIO_OLIO).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_RECIPE,
                new EditRecipeDescriptorBuilder().withName(VALID_NAME_AGLIO_OLIO).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe.getName());

        Model expectedModel = new ModelManager(new RecipeBook(model.getRecipeBook()), new UserPrefs());
        expectedModel.setRecipe(model.getFilteredRecipeList().get(0), editedRecipe);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateRecipeUnfilteredList_failure() {
        Recipe firstRecipe = model.getFilteredRecipeList().get(INDEX_FIRST_RECIPE.getZeroBased());
        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder(firstRecipe).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_RECIPE, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_RECIPE);
    }

    @Test
    public void execute_duplicateRecipeFilteredList_failure() {
        showRecipeAtIndex(model, INDEX_FIRST_RECIPE);

        Recipe recipeInList = model.getRecipeBook().getRecipeList().get(INDEX_SECOND_RECIPE.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_RECIPE,
                new EditRecipeDescriptorBuilder(recipeInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_RECIPE);
    }

    @Test
    public void execute_invalidRecipeIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRecipeList().size() + 1);
        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder().withName(VALID_NAME_CHICKEN_CHOP).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_RECIPE_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidRecipeIndexFilteredList_failure() {
        showRecipeAtIndex(model, INDEX_FIRST_RECIPE);
        Index outOfBoundIndex = INDEX_SECOND_RECIPE;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getRecipeBook().getRecipeList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditRecipeDescriptorBuilder().withName(VALID_NAME_CHICKEN_CHOP).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_RECIPE_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_RECIPE, DESC_AGLIO_OLIO);

        // same values -> returns true
        EditRecipeDescriptor copyDescriptor = new EditRecipeDescriptor(DESC_AGLIO_OLIO);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_RECIPE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        // assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_RECIPE, DESC_AGLIO_OLIO)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_RECIPE, DESC_CHICKEN_CHOP)));
    }
}
