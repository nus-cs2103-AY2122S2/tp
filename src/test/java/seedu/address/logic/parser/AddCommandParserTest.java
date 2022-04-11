package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COMPLETION_TIME_DESC_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.COMPLETION_TIME_DESC_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.INGREDIENT_DESC_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.INGREDIENT_DESC_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPLETION_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INGREDIENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SERVING_SIZE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STEP_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SERVING_SIZE_DESC_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.SERVING_SIZE_DESC_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.STEP_DESC_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.STEP_DESC_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETION_TIME_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_CHICKEN_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_GARLIC_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SERVING_SIZE_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STEP_1_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STEP_1_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CHICKEN_CHOP;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.recipe.CompletionTime;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.ServingSize;
import seedu.address.model.recipe.Step;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.RecipeBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Recipe expectedRecipe = new RecipeBuilder()
                .withName(VALID_NAME_AGLIO_OLIO)
                .withCompletionTime(VALID_COMPLETION_TIME_AGLIO_OLIO)
                .withServingSize(VALID_SERVING_SIZE_AGLIO_OLIO)
                .withIngredients(VALID_INGREDIENT_GARLIC_AGLIO_OLIO)
                .withSteps(VALID_STEP_1_AGLIO_OLIO)
                .withTags(VALID_TAG_AGLIO_OLIO, VALID_TAG_CHICKEN_CHOP).build();

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE
                        + NAME_DESC_AGLIO_OLIO
                        + COMPLETION_TIME_DESC_AGLIO_OLIO
                        + SERVING_SIZE_DESC_AGLIO_OLIO
                        + INGREDIENT_DESC_AGLIO_OLIO
                        + STEP_DESC_AGLIO_OLIO
                        + TAG_DESC_AGLIO_OLIO
                        + TAG_DESC_CHICKEN_CHOP,
                new AddCommand(expectedRecipe));

        // multiple names - last name accepted
        assertParseSuccess(parser,
                NAME_DESC_CHICKEN_CHOP
                        + NAME_DESC_AGLIO_OLIO
                        + COMPLETION_TIME_DESC_AGLIO_OLIO
                        + SERVING_SIZE_DESC_AGLIO_OLIO
                        + INGREDIENT_DESC_AGLIO_OLIO
                        + STEP_DESC_AGLIO_OLIO
                        + TAG_DESC_AGLIO_OLIO
                        + TAG_DESC_CHICKEN_CHOP,
                new AddCommand(expectedRecipe));

        // multiple completion times - last completion time accepted
        assertParseSuccess(parser,
                NAME_DESC_CHICKEN_CHOP
                        + NAME_DESC_AGLIO_OLIO
                        + COMPLETION_TIME_DESC_CHICKEN_CHOP
                        + COMPLETION_TIME_DESC_AGLIO_OLIO
                        + SERVING_SIZE_DESC_AGLIO_OLIO
                        + INGREDIENT_DESC_AGLIO_OLIO
                        + STEP_DESC_AGLIO_OLIO
                        + TAG_DESC_AGLIO_OLIO
                        + TAG_DESC_CHICKEN_CHOP,
                new AddCommand(expectedRecipe));

        // multiple serving sizes - last serving size accepted
        assertParseSuccess(parser,
                NAME_DESC_CHICKEN_CHOP
                        + NAME_DESC_AGLIO_OLIO
                        + COMPLETION_TIME_DESC_AGLIO_OLIO
                        + SERVING_SIZE_DESC_CHICKEN_CHOP
                        + SERVING_SIZE_DESC_AGLIO_OLIO
                        + INGREDIENT_DESC_AGLIO_OLIO
                        + STEP_DESC_AGLIO_OLIO
                        + TAG_DESC_AGLIO_OLIO
                        + TAG_DESC_CHICKEN_CHOP,
                new AddCommand(expectedRecipe));

        // multiple ingredients - all accepted
        Recipe expectedRecipeMultipleIngredients = new RecipeBuilder(expectedRecipe)
                .withIngredients(VALID_INGREDIENT_GARLIC_AGLIO_OLIO,
                        VALID_INGREDIENT_CHICKEN_CHICKEN_CHOP)
                .build();
        assertParseSuccess(parser,
                NAME_DESC_CHICKEN_CHOP
                        + NAME_DESC_AGLIO_OLIO
                        + COMPLETION_TIME_DESC_AGLIO_OLIO
                        + SERVING_SIZE_DESC_AGLIO_OLIO
                        + INGREDIENT_DESC_AGLIO_OLIO
                        + INGREDIENT_DESC_CHICKEN_CHOP
                        + STEP_DESC_AGLIO_OLIO
                        + TAG_DESC_AGLIO_OLIO
                        + TAG_DESC_CHICKEN_CHOP,
                new AddCommand(expectedRecipeMultipleIngredients));

        // multiple steps - all accepted
        Recipe expectedRecipeMultipleSteps = new RecipeBuilder(expectedRecipe)
                .withSteps(VALID_STEP_1_AGLIO_OLIO, VALID_STEP_1_CHICKEN_CHOP)
                .build();
        assertParseSuccess(parser,
                NAME_DESC_CHICKEN_CHOP
                        + NAME_DESC_AGLIO_OLIO
                        + COMPLETION_TIME_DESC_AGLIO_OLIO
                        + SERVING_SIZE_DESC_AGLIO_OLIO
                        + INGREDIENT_DESC_AGLIO_OLIO
                        + STEP_DESC_AGLIO_OLIO
                        + STEP_DESC_CHICKEN_CHOP
                        + TAG_DESC_AGLIO_OLIO
                        + TAG_DESC_CHICKEN_CHOP,
                new AddCommand(expectedRecipeMultipleSteps));

        // multiple tags - all accepted
        Recipe expectedRecipeMultipleTags = new RecipeBuilder(expectedRecipe)
                .withTags(VALID_TAG_CHICKEN_CHOP, VALID_TAG_AGLIO_OLIO)
                .build();
        assertParseSuccess(parser,
                NAME_DESC_CHICKEN_CHOP
                        + NAME_DESC_AGLIO_OLIO
                        + COMPLETION_TIME_DESC_AGLIO_OLIO
                        + SERVING_SIZE_DESC_AGLIO_OLIO
                        + INGREDIENT_DESC_AGLIO_OLIO
                        + STEP_DESC_AGLIO_OLIO
                        + TAG_DESC_AGLIO_OLIO
                        + TAG_DESC_CHICKEN_CHOP,
                new AddCommand(expectedRecipeMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Recipe expectedRecipe = new RecipeBuilder()
                .withName(VALID_NAME_AGLIO_OLIO)
                .withCompletionTime(VALID_COMPLETION_TIME_AGLIO_OLIO)
                .withServingSize(VALID_SERVING_SIZE_AGLIO_OLIO)
                .withIngredients(VALID_INGREDIENT_GARLIC_AGLIO_OLIO)
                .withSteps(VALID_STEP_1_AGLIO_OLIO)
                .withTags().build();

        assertParseSuccess(parser,
                NAME_DESC_AGLIO_OLIO
                        + COMPLETION_TIME_DESC_AGLIO_OLIO
                        + SERVING_SIZE_DESC_AGLIO_OLIO
                        + INGREDIENT_DESC_AGLIO_OLIO
                        + STEP_DESC_AGLIO_OLIO,
                new AddCommand(expectedRecipe));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser,
                VALID_NAME_AGLIO_OLIO
                        + COMPLETION_TIME_DESC_AGLIO_OLIO
                        + SERVING_SIZE_DESC_AGLIO_OLIO
                        + INGREDIENT_DESC_AGLIO_OLIO
                        + STEP_DESC_AGLIO_OLIO,
                expectedMessage);

        // missing completion time prefix
        assertParseFailure(parser,
                NAME_DESC_AGLIO_OLIO
                        + VALID_COMPLETION_TIME_AGLIO_OLIO
                        + SERVING_SIZE_DESC_AGLIO_OLIO
                        + INGREDIENT_DESC_AGLIO_OLIO
                        + STEP_DESC_AGLIO_OLIO,
                expectedMessage);

        // missing serving size prefix
        assertParseFailure(parser,
                NAME_DESC_AGLIO_OLIO
                        + COMPLETION_TIME_DESC_AGLIO_OLIO
                        + VALID_SERVING_SIZE_AGLIO_OLIO
                        + INGREDIENT_DESC_AGLIO_OLIO
                        + STEP_DESC_AGLIO_OLIO,
                expectedMessage);

        // missing ingredient prefix
        assertParseFailure(parser,
                NAME_DESC_AGLIO_OLIO
                        + COMPLETION_TIME_DESC_AGLIO_OLIO
                        + SERVING_SIZE_DESC_AGLIO_OLIO
                        + VALID_INGREDIENT_GARLIC_AGLIO_OLIO
                        + STEP_DESC_AGLIO_OLIO,
                expectedMessage);

        // missing step prefix
        assertParseFailure(parser,
                NAME_DESC_AGLIO_OLIO
                        + COMPLETION_TIME_DESC_AGLIO_OLIO
                        + SERVING_SIZE_DESC_AGLIO_OLIO
                        + INGREDIENT_DESC_AGLIO_OLIO
                        + VALID_STEP_1_AGLIO_OLIO,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
                VALID_NAME_AGLIO_OLIO
                        + VALID_COMPLETION_TIME_AGLIO_OLIO
                        + VALID_SERVING_SIZE_AGLIO_OLIO
                        + VALID_INGREDIENT_GARLIC_AGLIO_OLIO
                        + VALID_STEP_1_AGLIO_OLIO,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser,
                INVALID_NAME_DESC
                        + COMPLETION_TIME_DESC_AGLIO_OLIO
                        + SERVING_SIZE_DESC_AGLIO_OLIO
                        + INGREDIENT_DESC_AGLIO_OLIO
                        + STEP_DESC_AGLIO_OLIO
                        + TAG_DESC_AGLIO_OLIO
                        + TAG_DESC_CHICKEN_CHOP,
                Name.MESSAGE_CONSTRAINTS);

        // invalid completion time
        assertParseFailure(parser,
                NAME_DESC_AGLIO_OLIO
                        + INVALID_COMPLETION_TIME_DESC
                        + SERVING_SIZE_DESC_AGLIO_OLIO
                        + INGREDIENT_DESC_AGLIO_OLIO
                        + STEP_DESC_AGLIO_OLIO
                        + TAG_DESC_AGLIO_OLIO
                        + TAG_DESC_CHICKEN_CHOP,
                CompletionTime.MESSAGE_CONSTRAINTS);

        // invalid serving size
        assertParseFailure(parser,
                NAME_DESC_AGLIO_OLIO
                        + COMPLETION_TIME_DESC_AGLIO_OLIO
                        + INVALID_SERVING_SIZE_DESC
                        + INGREDIENT_DESC_AGLIO_OLIO
                        + STEP_DESC_AGLIO_OLIO
                        + TAG_DESC_AGLIO_OLIO
                        + TAG_DESC_CHICKEN_CHOP,
                ServingSize.MESSAGE_CONSTRAINTS);

        // invalid ingredient, TODO: add test for invalid qty, invalid quantifier
        assertParseFailure(parser,
                NAME_DESC_AGLIO_OLIO
                        + COMPLETION_TIME_DESC_AGLIO_OLIO
                        + SERVING_SIZE_DESC_AGLIO_OLIO
                        + INVALID_INGREDIENT_DESC
                        + STEP_DESC_AGLIO_OLIO
                        + TAG_DESC_AGLIO_OLIO
                        + TAG_DESC_CHICKEN_CHOP,
                Ingredient.QUANTITY_CONSTRAINTS);

        // invalid step
        assertParseFailure(parser,
                NAME_DESC_AGLIO_OLIO
                        + COMPLETION_TIME_DESC_AGLIO_OLIO
                        + SERVING_SIZE_DESC_AGLIO_OLIO
                        + INGREDIENT_DESC_AGLIO_OLIO
                        + INVALID_STEP_DESC
                        + TAG_DESC_AGLIO_OLIO
                        + TAG_DESC_CHICKEN_CHOP,
                Step.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser,
                NAME_DESC_AGLIO_OLIO
                        + COMPLETION_TIME_DESC_AGLIO_OLIO
                        + SERVING_SIZE_DESC_AGLIO_OLIO
                        + INGREDIENT_DESC_AGLIO_OLIO
                        + STEP_DESC_AGLIO_OLIO
                        + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser,
                INVALID_NAME_DESC
                        + COMPLETION_TIME_DESC_AGLIO_OLIO
                        + SERVING_SIZE_DESC_AGLIO_OLIO
                        + INGREDIENT_DESC_AGLIO_OLIO
                        + STEP_DESC_AGLIO_OLIO
                        + INVALID_TAG_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
                PREAMBLE_NON_EMPTY
                        + NAME_DESC_AGLIO_OLIO
                        + COMPLETION_TIME_DESC_AGLIO_OLIO
                        + SERVING_SIZE_DESC_AGLIO_OLIO
                        + INGREDIENT_DESC_AGLIO_OLIO
                        + STEP_DESC_AGLIO_OLIO
                        + INVALID_TAG_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddCommand.MESSAGE_USAGE));
    }
}
