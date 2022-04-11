package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_RECIPE_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_RECIPE_INDEX_OR_NAME;
import static seedu.address.logic.commands.CommandTestUtil.COMPLETION_TIME_DESC_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.COMPLETION_TIME_DESC_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.INGREDIENT_DESC_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.INGREDIENT_DESC_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPLETION_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SERVING_SIZE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STEP_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.SERVING_SIZE_DESC_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.SERVING_SIZE_DESC_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.STEP_DESC_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.STEP_DESC_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETION_TIME_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETION_TIME_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_CHICKEN_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_GARLIC_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SERVING_SIZE_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SERVING_SIZE_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STEP_1_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STEP_1_CHICKEN_CHOP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_AGLIO_OLIO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CHICKEN_CHOP;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.RecipeBookSyntax.PREFIX_TAG;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_RECIPE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditRecipeDescriptor;
import seedu.address.model.recipe.CompletionTime;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.ServingSize;
import seedu.address.model.recipe.Step;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditRecipeDescriptorBuilder;


public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;
    private static final String indexPreface = " " + PREFIX_INDEX;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index/name specified
        assertParseFailure(parser,
                COMPLETION_TIME_DESC_CHICKEN_CHOP,
                MESSAGE_MISSING_RECIPE_INDEX_OR_NAME);

        // no field specified
        assertParseFailure(parser, VALID_NAME_AGLIO_OLIO, EditCommand.MESSAGE_NOT_EDITED);

        // no index/name and no field specified
        assertParseFailure(parser, "", MESSAGE_MISSING_RECIPE_INDEX_OR_NAME);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, " -x -5" + NAME_DESC_AGLIO_OLIO, MESSAGE_INVALID_RECIPE_INDEX);

        // zero index
        assertParseFailure(parser, " -x 0" + NAME_DESC_AGLIO_OLIO, MESSAGE_INVALID_RECIPE_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, " " + NAME_DESC_AGLIO_OLIO, MESSAGE_MISSING_RECIPE_INDEX_OR_NAME);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, " -g 1" + NAME_DESC_AGLIO_OLIO, MESSAGE_MISSING_RECIPE_INDEX_OR_NAME);
    }

    @Test
    public void parse_invalidValue_failure() {
        Index targetIndex = INDEX_THIRD_RECIPE;
        String userInput = indexPreface + targetIndex.getOneBased();

        // invalid name
        assertParseFailure(parser,
                userInput + INVALID_NAME_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // invalid completion time
        assertParseFailure(parser,
                userInput + INVALID_COMPLETION_TIME_DESC,
                CompletionTime.MESSAGE_CONSTRAINTS);

        // invalid serving size
        assertParseFailure(parser,
                userInput + INVALID_SERVING_SIZE_DESC,
                ServingSize.MESSAGE_CONSTRAINTS);

        // invalid ingredient
        // assertParseFailure(parser, "1" + INVALID_INGREDIENT_DESC, Ingredient.MESSAGE_CONSTRAINTS);

        // invalid step
        assertParseFailure(parser,
                userInput + INVALID_STEP_DESC,
                Step.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser,
                userInput + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS);

        // invalid completion time followed by valid serving size
        assertParseFailure(parser,
                userInput + INVALID_COMPLETION_TIME_DESC + SERVING_SIZE_DESC_AGLIO_OLIO,
                CompletionTime.MESSAGE_CONSTRAINTS);

        // valid completion time followed by invalid completion time. The test case
        // for invalid completion time followed by valid completion time is tested at
        // {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser,
                userInput + COMPLETION_TIME_DESC_CHICKEN_CHOP + INVALID_COMPLETION_TIME_DESC,
                CompletionTime.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser,
                userInput + TAG_DESC_AGLIO_OLIO + TAG_DESC_CHICKEN_CHOP + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
                userInput + TAG_DESC_AGLIO_OLIO + TAG_EMPTY + TAG_DESC_CHICKEN_CHOP,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
                userInput + TAG_EMPTY + TAG_DESC_AGLIO_OLIO + TAG_DESC_CHICKEN_CHOP,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser,
                userInput
                + INVALID_NAME_DESC
                + INVALID_COMPLETION_TIME_DESC
                + INVALID_SERVING_SIZE_DESC
                + INVALID_STEP_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_THIRD_RECIPE;

        String userInput = indexPreface + targetIndex.getOneBased()
                + COMPLETION_TIME_DESC_CHICKEN_CHOP
                + TAG_DESC_CHICKEN_CHOP
                + SERVING_SIZE_DESC_AGLIO_OLIO
                + STEP_DESC_AGLIO_OLIO
                + NAME_DESC_AGLIO_OLIO
                + TAG_DESC_AGLIO_OLIO
                + TAG_DESC_CHICKEN_CHOP;

        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder()
                .withName(VALID_NAME_AGLIO_OLIO)
                .withCompletionTime(VALID_COMPLETION_TIME_CHICKEN_CHOP)
                .withServingSize(VALID_SERVING_SIZE_AGLIO_OLIO)
                .withSteps(VALID_STEP_1_AGLIO_OLIO)
                .withTags(VALID_TAG_AGLIO_OLIO, VALID_TAG_CHICKEN_CHOP).build();

        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_THIRD_RECIPE;

        String userInput = indexPreface + targetIndex.getOneBased()
                + SERVING_SIZE_DESC_AGLIO_OLIO
                + COMPLETION_TIME_DESC_CHICKEN_CHOP;

        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder()
                .withServingSize(VALID_SERVING_SIZE_AGLIO_OLIO)
                .withCompletionTime(VALID_COMPLETION_TIME_CHICKEN_CHOP).build();

        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_recipeIndexOneFieldSpecified_success() {
        Index targetIndex = INDEX_THIRD_RECIPE;

        // name
        String userInput = indexPreface + targetIndex.getOneBased() + NAME_DESC_AGLIO_OLIO;
        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder()
                .withName(VALID_NAME_AGLIO_OLIO).build();
        System.out.println(userInput);
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // completion time
        userInput = indexPreface + targetIndex.getOneBased() + COMPLETION_TIME_DESC_AGLIO_OLIO;
        descriptor = new EditRecipeDescriptorBuilder()
                .withCompletionTime(VALID_COMPLETION_TIME_AGLIO_OLIO).build();
        System.out.println(userInput);
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // serving size
        userInput = indexPreface + targetIndex.getOneBased() + SERVING_SIZE_DESC_AGLIO_OLIO;
        descriptor = new EditRecipeDescriptorBuilder()
                .withServingSize(VALID_SERVING_SIZE_AGLIO_OLIO).build();
        System.out.println(userInput);
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // // ingredient
        userInput = indexPreface + targetIndex.getOneBased() + INGREDIENT_DESC_AGLIO_OLIO;
        descriptor = new EditRecipeDescriptorBuilder()
                .withIngredients(VALID_INGREDIENT_GARLIC_AGLIO_OLIO).build();
        System.out.println(userInput);
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // step
        userInput = indexPreface + targetIndex.getOneBased() + STEP_DESC_AGLIO_OLIO;
        descriptor = new EditRecipeDescriptorBuilder()
                .withSteps(VALID_STEP_1_AGLIO_OLIO).build();
        System.out.println(userInput);
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = indexPreface + targetIndex.getOneBased() + TAG_DESC_AGLIO_OLIO;
        descriptor = new EditRecipeDescriptorBuilder()
                .withTags(VALID_TAG_AGLIO_OLIO).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_recipeNameOneFieldSpecified_success() {
        String indexPreface = " Aglio Olio";

        // name
        Name targetName = new Name(VALID_NAME_AGLIO_OLIO);
        String userInput = indexPreface + NAME_DESC_CHICKEN_CHOP;
        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder()
                .withName(VALID_NAME_CHICKEN_CHOP).build();
        System.out.println(userInput);
        EditCommand expectedCommand = new EditCommand(targetName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // completion time
        userInput = indexPreface + COMPLETION_TIME_DESC_AGLIO_OLIO;
        descriptor = new EditRecipeDescriptorBuilder()
                .withCompletionTime(VALID_COMPLETION_TIME_AGLIO_OLIO).build();
        System.out.println(userInput);
        expectedCommand = new EditCommand(targetName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // serving size
        userInput = indexPreface + SERVING_SIZE_DESC_AGLIO_OLIO;
        descriptor = new EditRecipeDescriptorBuilder()
                .withServingSize(VALID_SERVING_SIZE_AGLIO_OLIO).build();
        System.out.println(userInput);
        expectedCommand = new EditCommand(targetName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // ingredient
        userInput = indexPreface + INGREDIENT_DESC_AGLIO_OLIO;
        descriptor = new EditRecipeDescriptorBuilder()
                .withIngredients(VALID_INGREDIENT_GARLIC_AGLIO_OLIO).build();
        System.out.println(userInput);
        expectedCommand = new EditCommand(targetName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // step
        userInput = indexPreface + STEP_DESC_AGLIO_OLIO;
        descriptor = new EditRecipeDescriptorBuilder()
                .withSteps(VALID_STEP_1_AGLIO_OLIO).build();
        System.out.println(userInput);
        expectedCommand = new EditCommand(targetName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = indexPreface + TAG_DESC_AGLIO_OLIO;
        descriptor = new EditRecipeDescriptorBuilder()
                .withTags(VALID_TAG_AGLIO_OLIO).build();
        expectedCommand = new EditCommand(targetName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_THIRD_RECIPE;

        String userInput = indexPreface + targetIndex.getOneBased()
                + COMPLETION_TIME_DESC_AGLIO_OLIO
                + SERVING_SIZE_DESC_AGLIO_OLIO
                + TAG_DESC_AGLIO_OLIO
                + COMPLETION_TIME_DESC_AGLIO_OLIO
                + SERVING_SIZE_DESC_AGLIO_OLIO
                + TAG_DESC_AGLIO_OLIO
                + COMPLETION_TIME_DESC_CHICKEN_CHOP
                + SERVING_SIZE_DESC_CHICKEN_CHOP
                + STEP_DESC_CHICKEN_CHOP
                + TAG_DESC_CHICKEN_CHOP;

        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder()
                .withCompletionTime(VALID_COMPLETION_TIME_CHICKEN_CHOP)
                .withServingSize(VALID_SERVING_SIZE_CHICKEN_CHOP)
                .withSteps(VALID_STEP_1_CHICKEN_CHOP)
                .withTags(VALID_TAG_AGLIO_OLIO, VALID_TAG_CHICKEN_CHOP)
                .build();

        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_THIRD_RECIPE;

        String userInput = indexPreface + targetIndex.getOneBased()
                + INVALID_COMPLETION_TIME_DESC
                + COMPLETION_TIME_DESC_CHICKEN_CHOP;

        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder()
                .withCompletionTime(VALID_COMPLETION_TIME_CHICKEN_CHOP).build();

        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = indexPreface + targetIndex.getOneBased()
                + INVALID_COMPLETION_TIME_DESC
                + COMPLETION_TIME_DESC_CHICKEN_CHOP
                + SERVING_SIZE_DESC_CHICKEN_CHOP
                + INGREDIENT_DESC_CHICKEN_CHOP;

        descriptor = new EditRecipeDescriptorBuilder()
                .withCompletionTime(VALID_COMPLETION_TIME_CHICKEN_CHOP)
                .withServingSize(VALID_SERVING_SIZE_CHICKEN_CHOP)
                .withIngredients(VALID_INGREDIENT_CHICKEN_CHICKEN_CHOP).build();

        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_RECIPE;

        String userInput = indexPreface + targetIndex.getOneBased() + TAG_EMPTY;
        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder().withTags().build();

        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
