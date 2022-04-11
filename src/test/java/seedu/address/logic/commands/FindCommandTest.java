package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_FIND_RETURNS_NO_RECIPES_FOUND;
import static seedu.address.commons.core.Messages.MESSAGE_RECIPES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestUtil.filteredListEquality;
import static seedu.address.testutil.TypicalRecipes.AGLIO_OLIO;
import static seedu.address.testutil.TypicalRecipes.DUCK_RICE;
import static seedu.address.testutil.TypicalRecipes.FRIED_RICE;
import static seedu.address.testutil.TypicalRecipes.SHOYU_RAMEN;
import static seedu.address.testutil.TypicalRecipes.SUSHI;
import static seedu.address.testutil.TypicalRecipes.getTypicalRecipeBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecipeContainsKeywordPredicate;
import seedu.address.testutil.TypicalRecipes;

/** These tests heavily relies on {@link TypicalRecipes#getTypicalRecipeBook()} */
class FindCommandTest {
    private final Model model = new ModelManager(getTypicalRecipeBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalRecipeBook(), new UserPrefs());

    /** Duplicate from FindCommandParserTest */
    @Test
    public void execute_zeroKeywords_throwsException() {
        RecipeContainsKeywordPredicate pred = new RecipeContainsKeywordPredicate(List.of(" "));
        FindCommand cmd = new FindCommand(pred);
        String expectedMessage = "String value cannot be empty";

        assertThrows(IllegalArgumentException.class, () -> expectedModel.updateFilteredRecipeList(pred),
                expectedMessage);
    }

    @Test
    public void execute_singleKeyword_zeroResult() {
        RecipeContainsKeywordPredicate zeroPred = new RecipeContainsKeywordPredicate(List.of("STILL MAINDENLESS?"));
        FindCommand cmd = new FindCommand(zeroPred);
        String expectedMessage = String.format(MESSAGE_FIND_RETURNS_NO_RECIPES_FOUND, zeroPred);

        expectedModel.updateFilteredRecipeList(zeroPred);
        assertCommandSuccess(cmd, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredRecipeList().isEmpty());
    }

    @Test
    public void execute_singleKeyword_singleResult() {
        RecipeContainsKeywordPredicate singlePred =
                new RecipeContainsKeywordPredicate(List.of(AGLIO_OLIO.getName().fullName));
        FindCommand cmd = new FindCommand(singlePred);
        String expectedMessage = String.format(MESSAGE_RECIPES_LISTED_OVERVIEW, 1);

        expectedModel.updateFilteredRecipeList(singlePred);
        assertCommandSuccess(cmd, model, expectedMessage, expectedModel);
        assertEquals(List.of(AGLIO_OLIO), model.getFilteredRecipeList());
    }

    @Test
    public void execute_singleKeyword_multiResult() {
        RecipeContainsKeywordPredicate singlePred = new RecipeContainsKeywordPredicate(List.of("asian"));
        FindCommand cmd = new FindCommand(singlePred);
        String expectedMessage = String.format(MESSAGE_RECIPES_LISTED_OVERVIEW, 4);
        List<Recipe> expectedResults = List.of(FRIED_RICE, DUCK_RICE, SUSHI, SHOYU_RAMEN);

        expectedModel.updateFilteredRecipeList(singlePred);
        assertCommandSuccess(cmd, model, expectedMessage, expectedModel);

        List<Recipe> modelResults = model.getFilteredRecipeList();
        assertTrue(filteredListEquality(modelResults, expectedResults));
    }

    @Test
    public void execute_multiKeyword_singleResult() {
        RecipeContainsKeywordPredicate multiPred =
                new RecipeContainsKeywordPredicate(List.of(AGLIO_OLIO.getName().fullName, "MAINDENLESS??"));
        FindCommand cmd = new FindCommand(multiPred);
        String expectedMessage = String.format(MESSAGE_RECIPES_LISTED_OVERVIEW, 1);

        expectedModel.updateFilteredRecipeList(multiPred);
        assertCommandSuccess(cmd, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredRecipeList().size(), 1);
    }

    @Test
    public void execute_multiKeyword_multiResult() {
        RecipeContainsKeywordPredicate multiPred =
                new RecipeContainsKeywordPredicate(List.of("asian", AGLIO_OLIO.getName().fullName, "STILL MAIDENLESS"));
        FindCommand cmd = new FindCommand(multiPred);
        String expectedMessage = String.format(MESSAGE_RECIPES_LISTED_OVERVIEW, 5);
        List<Recipe> expectedResults = List.of(AGLIO_OLIO, FRIED_RICE, DUCK_RICE, SUSHI, SHOYU_RAMEN);

        expectedModel.updateFilteredRecipeList(multiPred);
        assertCommandSuccess(cmd, model, expectedMessage, expectedModel);
        List<Recipe> modelResults = model.getFilteredRecipeList();
        assertTrue(filteredListEquality(modelResults, expectedResults));
    }

    @Test
    public void equals_test() {
        RecipeContainsKeywordPredicate pred1 = new RecipeContainsKeywordPredicate(List.of("test-01"));
        RecipeContainsKeywordPredicate pred1Copy = new RecipeContainsKeywordPredicate(pred1.getKeywords());
        RecipeContainsKeywordPredicate pred2 = new RecipeContainsKeywordPredicate(List.of("test-02"));
        FindCommand fc1 = new FindCommand(pred1);
        FindCommand fc1Copy = new FindCommand(pred1Copy);
        FindCommand fc2 = new FindCommand(pred2);

        assertNotEquals(fc1, fc1.getRecipePredicate());
        assertEquals(fc1, fc1);
        assertEquals(fc1, fc1Copy);
        assertNotEquals(fc1, fc2);
    }

    @Test
    public void hashCode_test() {
        RecipeContainsKeywordPredicate pred1 = new RecipeContainsKeywordPredicate(List.of("test-01"));
        RecipeContainsKeywordPredicate pred1Copy = new RecipeContainsKeywordPredicate(pred1.getKeywords());
        RecipeContainsKeywordPredicate pred2 = new RecipeContainsKeywordPredicate(List.of("test-02"));
        FindCommand fc1 = new FindCommand(pred1);
        FindCommand fc1Copy = new FindCommand(pred1Copy);
        FindCommand fc2 = new FindCommand(pred2);

        assertEquals(fc1.hashCode(), fc1Copy.hashCode());
        assertNotEquals(fc1.hashCode(), fc2.hashCode());
    }
}
