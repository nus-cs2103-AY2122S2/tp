package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalRecipes.AGLIO_OLIO;
import static seedu.address.testutil.TypicalRecipes.FRIED_RICE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.RecipeBuilder;

class RecipeTest {
    private Recipe validRecipeAglioOlioCopy = new RecipeBuilder(AGLIO_OLIO).build();
    private Recipe validRecipeAglioOlioWithDifferentValues =
            new RecipeBuilder()
                    .withName("Aglio Olio")
                    .withCompletionTime(5)
                    .withIngredients("different 1 kg", "ingredients 2 grams", "here 3")
                    .withServingSize(5)
                    .withSteps("different,steps,here")
                    .withTags("different").withTags("tags").withTags("here")
                    .build();

    @Test
    public void getSearchSet_test() {
        assertEquals(AGLIO_OLIO.getSearchSet(), validRecipeAglioOlioCopy.getSearchSet());
    }

    @Test
    public void isSameRecipe_test() {

        assertFalse(AGLIO_OLIO.isSameRecipe(null));
        assertTrue(AGLIO_OLIO.isSameRecipe(AGLIO_OLIO));
        assertTrue(AGLIO_OLIO.isSameRecipe(validRecipeAglioOlioWithDifferentValues));
        assertFalse(AGLIO_OLIO.isSameRecipe(FRIED_RICE));
    }

    @Test
    public void equals_test() {
        assertFalse(AGLIO_OLIO.equals(AGLIO_OLIO.getName()));
        assertTrue(AGLIO_OLIO.equals(AGLIO_OLIO));
        assertTrue(AGLIO_OLIO.equals(validRecipeAglioOlioCopy));
        assertFalse(AGLIO_OLIO.equals(FRIED_RICE));

        assertEquals(AGLIO_OLIO.hashCode(), validRecipeAglioOlioCopy.hashCode());
        assertNotEquals(AGLIO_OLIO.hashCode(), validRecipeAglioOlioWithDifferentValues.hashCode());
    }

    @Test
    public void toString_test() {
        assertEquals(AGLIO_OLIO.toString(), validRecipeAglioOlioCopy.toString());
        assertNotEquals(AGLIO_OLIO.toString(), validRecipeAglioOlioWithDifferentValues.toString());
    }
}
