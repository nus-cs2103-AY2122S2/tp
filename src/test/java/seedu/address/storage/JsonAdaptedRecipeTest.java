package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedRecipe.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecipes.AGLIO_OLIO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.recipe.CompletionTime;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.ServingSize;


public class JsonAdaptedRecipeTest {
    private static final String INVALID_NAME = "";
    private static final Integer INVALID_COMPLETION_TIME = -12;
    private static final String INVALID_STEP = "";
    private static final Integer INVALID_SERVING_SIZE = -5;
    private static final String INVALID_TAG = "";
    private static final JsonAdaptedIngredient INVALID_INGREDIENT_NAME =
            new JsonAdaptedIngredient("  ", "-5", "");
    private static final JsonAdaptedIngredient INVALID_INGREDIENT_QUANTITY =
            new JsonAdaptedIngredient("dummy", "-5", "");
    private static final JsonAdaptedIngredient INVALID_INGREDIENT_QUANTITY_2 =
            new JsonAdaptedIngredient("dummy", "a", "");

    //Extract parameters from Valid Recipe AGLIO_OLIO
    private static final String VALID_NAME = AGLIO_OLIO.getName().toString();
    private static final List<JsonAdaptedIngredient> VALID_INGREDIENTS =
            AGLIO_OLIO.getIngredients().stream().map(JsonAdaptedIngredient::new).collect(Collectors.toList());
    private static final Integer VALID_COMPLETION_TIME = AGLIO_OLIO.getCompletionTime().value;
    private static final List<JsonAdaptedStep> VALID_STEPS =
            AGLIO_OLIO.getSteps().stream().map(JsonAdaptedStep::new).collect(Collectors.toList());
    private static final Integer VALID_SERVING_SIZE = AGLIO_OLIO.getServingSize().value;
    private static final List<JsonAdaptedTag> VALID_TAGS =
            AGLIO_OLIO.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList());


    @Test
    public void toModelType_validRecipeDetails_returnsRecipe() throws Exception {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(AGLIO_OLIO);
        assertEquals(AGLIO_OLIO, recipe.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe =
                new JsonAdaptedRecipe(INVALID_NAME, VALID_INGREDIENTS, VALID_COMPLETION_TIME, VALID_SERVING_SIZE,
                        VALID_STEPS, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe =
                new JsonAdaptedRecipe(null, VALID_INGREDIENTS, VALID_COMPLETION_TIME, VALID_SERVING_SIZE,
                        VALID_STEPS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_invalidIngredientName_throwsIllegalValueException() {
        List<JsonAdaptedIngredient> invalidIngredients = new ArrayList<>(VALID_INGREDIENTS);
        invalidIngredients.add(INVALID_INGREDIENT_NAME);
        JsonAdaptedRecipe recipe =
                new JsonAdaptedRecipe(VALID_NAME, invalidIngredients, VALID_COMPLETION_TIME, VALID_SERVING_SIZE,
                        VALID_STEPS, VALID_TAGS);
        assertThrows(IllegalValueException.class, recipe::toModelType);
    }

    @Test
    public void toModelType_invalidIngredientQuantity_throwsIllegalValueException() {
        List<JsonAdaptedIngredient> invalidIngredients = new ArrayList<>(VALID_INGREDIENTS);
        invalidIngredients.add(INVALID_INGREDIENT_QUANTITY);
        JsonAdaptedRecipe recipe =
                new JsonAdaptedRecipe(VALID_NAME, invalidIngredients, VALID_COMPLETION_TIME, VALID_SERVING_SIZE,
                        VALID_STEPS, VALID_TAGS);
        assertThrows(IllegalValueException.class, recipe::toModelType);
    }

    @Test
    public void toModelType_invalidIngredientNonNumericQuantity_throwsIllegalValueException() {
        List<JsonAdaptedIngredient> invalidIngredients = new ArrayList<>(VALID_INGREDIENTS);
        invalidIngredients.add(INVALID_INGREDIENT_QUANTITY_2);
        JsonAdaptedRecipe recipe =
                new JsonAdaptedRecipe(VALID_NAME, invalidIngredients, VALID_COMPLETION_TIME, VALID_SERVING_SIZE,
                        VALID_STEPS, VALID_TAGS);
        assertThrows(IllegalValueException.class, recipe::toModelType);
    }

    @Test
    public void toModelType_invalidCompletionTime_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe =
                new JsonAdaptedRecipe(VALID_NAME, VALID_INGREDIENTS, INVALID_COMPLETION_TIME, VALID_SERVING_SIZE,
                        VALID_STEPS, VALID_TAGS);
        String expectedMessage = CompletionTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_nullCompletionTime_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe =
                new JsonAdaptedRecipe(VALID_NAME, VALID_INGREDIENTS, null, VALID_SERVING_SIZE,
                        VALID_STEPS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                CompletionTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_invalidServingSize_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe =
                new JsonAdaptedRecipe(VALID_NAME, VALID_INGREDIENTS, VALID_COMPLETION_TIME, INVALID_SERVING_SIZE,
                        VALID_STEPS, VALID_TAGS);
        String expectedMessage = ServingSize.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_nullServingSize_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe =
                new JsonAdaptedRecipe(VALID_NAME, VALID_INGREDIENTS, VALID_COMPLETION_TIME, null,
                        VALID_STEPS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                ServingSize.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_invalidSteps_throwsIllegalValueException() {
        List<JsonAdaptedStep> invalidSteps = new ArrayList<>(VALID_STEPS);
        invalidSteps.add(new JsonAdaptedStep(INVALID_STEP));
        JsonAdaptedRecipe recipe =
                new JsonAdaptedRecipe(VALID_NAME, VALID_INGREDIENTS, VALID_COMPLETION_TIME, VALID_SERVING_SIZE,
                        invalidSteps, VALID_TAGS);
        assertThrows(IllegalValueException.class, recipe::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedRecipe recipe =
                new JsonAdaptedRecipe(VALID_NAME, VALID_INGREDIENTS, VALID_COMPLETION_TIME, VALID_SERVING_SIZE,
                        VALID_STEPS, invalidTags);
        assertThrows(IllegalValueException.class, recipe::toModelType);
    }
}
