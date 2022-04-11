package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_COMPLETION_TIME;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_SERVING_SIZE;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_STEP;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditRecipeDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.RecipeBook;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecipeContainsKeywordPredicate;
import seedu.address.testutil.EditRecipeDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AGLIO_OLIO = "Aglio Olio";
    public static final String VALID_NAME_CHICKEN_CHOP = "Chicken Chop";
    public static final int VALID_COMPLETION_TIME_AGLIO_OLIO = 5;
    public static final int VALID_COMPLETION_TIME_CHICKEN_CHOP = 10;
    public static final int VALID_SERVING_SIZE_AGLIO_OLIO = 1;
    public static final int VALID_SERVING_SIZE_CHICKEN_CHOP = 2;
    public static final String VALID_INGREDIENT_CHICKEN_CHICKEN_CHOP = "Chicken 1";
    public static final String VALID_INGREDIENT_POTATO_CHICKEN_CHOP = "Potato 2";
    public static final List<String> VALID_INGREDIENTS_CHICKEN_CHOP =
            List.of(VALID_INGREDIENT_CHICKEN_CHICKEN_CHOP, VALID_INGREDIENT_POTATO_CHICKEN_CHOP);
    public static final String VALID_INGREDIENT_SPAGHETTI_AGLIO_OLIO = "Spaghetti 1.0";
    public static final String VALID_INGREDIENT_GARLIC_AGLIO_OLIO = "Garlic 3 Cloves";
    public static final List<String> VALID_INGREDIENTS_AGLIO_OLIO =
            List.of(VALID_INGREDIENT_GARLIC_AGLIO_OLIO, VALID_INGREDIENT_SPAGHETTI_AGLIO_OLIO);
    public static final String VALID_STEP_1_CHICKEN_CHOP = "Chop the chickens";
    public static final String VALID_STEP_2_CHICKEN_CHOP = "Cook the chicken slices";
    public static final List<String> VALID_STEPS_CHICKEN_CHOP =
            List.of(VALID_STEP_1_CHICKEN_CHOP, VALID_STEP_2_CHICKEN_CHOP);
    public static final String VALID_STEP_1_AGLIO_OLIO = "Cook the pasta";
    public static final String VALID_STEP_2_AGLIO_OLIO = "Saute the garlic";
    public static final List<String> VALID_STEPS_AGLIO_OLIO =
            List.of(VALID_STEP_1_AGLIO_OLIO, VALID_STEP_2_AGLIO_OLIO);
    public static final String VALID_TAG_AGLIO_OLIO = "italian";
    public static final String VALID_TAG_CHICKEN_CHOP = "western";

    public static final String NAME_DESC_AGLIO_OLIO = " " + PREFIX_NAME + VALID_NAME_AGLIO_OLIO;
    public static final String NAME_DESC_CHICKEN_CHOP = " " + PREFIX_NAME + VALID_NAME_CHICKEN_CHOP;
    public static final String COMPLETION_TIME_DESC_AGLIO_OLIO = " " + PREFIX_COMPLETION_TIME
            + VALID_COMPLETION_TIME_AGLIO_OLIO;
    public static final String COMPLETION_TIME_DESC_CHICKEN_CHOP = " " + PREFIX_COMPLETION_TIME
            + VALID_COMPLETION_TIME_CHICKEN_CHOP;
    public static final String SERVING_SIZE_DESC_AGLIO_OLIO = " " + PREFIX_SERVING_SIZE
            + VALID_SERVING_SIZE_AGLIO_OLIO;
    public static final String SERVING_SIZE_DESC_CHICKEN_CHOP = " " + PREFIX_SERVING_SIZE
            + VALID_SERVING_SIZE_CHICKEN_CHOP;
    public static final String INGREDIENT_DESC_AGLIO_OLIO = " " + PREFIX_INGREDIENT
            + VALID_INGREDIENT_GARLIC_AGLIO_OLIO;
    public static final String INGREDIENT_DESC_CHICKEN_CHOP = " " + PREFIX_INGREDIENT
            + VALID_INGREDIENT_CHICKEN_CHICKEN_CHOP;
    public static final String STEP_DESC_AGLIO_OLIO = " " + PREFIX_STEP + VALID_STEP_1_AGLIO_OLIO;
    public static final String STEP_DESC_CHICKEN_CHOP = " " + PREFIX_STEP + VALID_STEP_1_CHICKEN_CHOP;
    public static final String TAG_DESC_AGLIO_OLIO = " " + PREFIX_TAG + VALID_TAG_AGLIO_OLIO;
    public static final String TAG_DESC_CHICKEN_CHOP = " " + PREFIX_TAG + VALID_TAG_CHICKEN_CHOP;

    // '&' not allowed in names
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&";
    // 'a' not allowed in phones
    public static final String INVALID_COMPLETION_TIME_DESC = " " + PREFIX_COMPLETION_TIME + "911a";
    // ! not allowed in serving size
    public static final String INVALID_SERVING_SIZE_DESC = " " + PREFIX_SERVING_SIZE + "bob!yahoo";
    // empty string not allowed for ingredient
    public static final String INVALID_INGREDIENT_DESC = " " + PREFIX_INGREDIENT;
    // empty string not allowed for steps
    public static final String INVALID_STEP_DESC = " " + PREFIX_STEP;
    // '*' not allowed in tags
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditRecipeDescriptor DESC_AGLIO_OLIO;
    public static final EditRecipeDescriptor DESC_CHICKEN_CHOP;

    static {
        DESC_AGLIO_OLIO = new EditRecipeDescriptorBuilder()
                .withName(VALID_NAME_AGLIO_OLIO)
                .withCompletionTime(VALID_COMPLETION_TIME_AGLIO_OLIO)
                .withServingSize(VALID_SERVING_SIZE_AGLIO_OLIO)
                .withIngredients(VALID_INGREDIENT_SPAGHETTI_AGLIO_OLIO)
                .withSteps(VALID_STEP_1_AGLIO_OLIO)
                .withTags(VALID_TAG_AGLIO_OLIO).build();

        DESC_CHICKEN_CHOP = new EditRecipeDescriptorBuilder()
                .withName(VALID_NAME_CHICKEN_CHOP)
                .withCompletionTime(VALID_COMPLETION_TIME_CHICKEN_CHOP)
                .withServingSize(VALID_SERVING_SIZE_CHICKEN_CHOP)
                .withIngredients(VALID_INGREDIENT_CHICKEN_CHICKEN_CHOP)
                .withSteps(VALID_STEP_1_CHICKEN_CHOP)
                .withTags(VALID_TAG_CHICKEN_CHOP).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command,
                                            Model actualModel,
                                            CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the recipe book, filtered recipe list and selected recipe in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        RecipeBook expectedAddressBook = new RecipeBook(actualModel.getRecipeBook());
        List<Recipe> expectedFilteredList = new ArrayList<>(actualModel.getFilteredRecipeList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getRecipeBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredRecipeList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the recipe at the given {@code targetIndex} in the
     * {@code model}'s recipe book.
     */
    public static void showRecipeAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredRecipeList().size());

        Recipe recipe = model.getFilteredRecipeList().get(targetIndex.getZeroBased());
        final String[] splitName = recipe.getName().fullName.split("\\s+");
        model.updateFilteredRecipeList(new RecipeContainsKeywordPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredRecipeList().size());
    }
}
