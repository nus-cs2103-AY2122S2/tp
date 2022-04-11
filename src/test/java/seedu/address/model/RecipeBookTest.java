package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalRecipes.AGLIO_OLIO;
import static seedu.address.testutil.TypicalRecipes.BEEF_TACO;
import static seedu.address.testutil.TypicalRecipes.FRIED_RICE;
import static seedu.address.testutil.TypicalRecipes.SHOYU_RAMEN;
import static seedu.address.testutil.TypicalRecipes.getTypicalRecipeBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.RecipeBuilder;

class RecipeBookTest {

    private RecipeBook recipeBook;

    @BeforeEach
    public void init() {
        recipeBook = new RecipeBook();
    }

    @Test
    public void constructor() {
        assertEquals(0, recipeBook.getRecipeList().size());
    }

    @Test
    public void resetData_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> recipeBook.removeRecipe(null));
    }

    @Test
    public void resetData_validInput_success() {
        RecipeBook typicalRecipeBook = getTypicalRecipeBook();
        recipeBook.resetData(typicalRecipeBook);

        assertEquals(typicalRecipeBook, recipeBook);
    }

    @Test
    public void hasRecipe_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> recipeBook.hasRecipe(null));
    }

    @Test
    public void hasRecipe_validInput_success() {
        Recipe editedAglioOlio = new RecipeBuilder(AGLIO_OLIO)
                .withTags("extra", "tags", "here")
                .build();

        recipeBook.addRecipe(AGLIO_OLIO);

        assertTrue(recipeBook.hasRecipe(AGLIO_OLIO));
        assertTrue(recipeBook.hasRecipe(editedAglioOlio));
        assertFalse(recipeBook.hasRecipe(FRIED_RICE));
    }

    @Test
    public void getRecipeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> recipeBook.getRecipeList().remove(0));
    }

    @Test
    public void setRecipe_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> recipeBook.setRecipe(null, null));
        assertThrows(NullPointerException.class, () -> recipeBook.setRecipe(null, SHOYU_RAMEN));
        assertThrows(NullPointerException.class, () -> recipeBook.setRecipe(SHOYU_RAMEN, null));
    }

    @Test
    public void setRecipe_validRecipe_success() {
        recipeBook.addRecipe(AGLIO_OLIO);
        recipeBook.addRecipe(FRIED_RICE);
        recipeBook.setRecipe(AGLIO_OLIO, BEEF_TACO);

        RecipeBook expectedRecipeBook = new RecipeBook();
        expectedRecipeBook.addRecipe(BEEF_TACO);
        expectedRecipeBook.addRecipe(FRIED_RICE);

        assertEquals(expectedRecipeBook, recipeBook);
    }

    @Test
    public void removeRecipe_success() {
        recipeBook.addRecipe(AGLIO_OLIO);
        recipeBook.addRecipe(FRIED_RICE);
        recipeBook.removeRecipe(AGLIO_OLIO);

        RecipeBook expectedRecipeBook = new RecipeBook();
        expectedRecipeBook.addRecipe(FRIED_RICE);

        assertEquals(expectedRecipeBook, recipeBook);
    }

    @Test
    public void toString_test() {
        recipeBook.addRecipe(AGLIO_OLIO);
        assertEquals("1 recipe(s)", recipeBook.toString());

        recipeBook.addRecipe(FRIED_RICE);
        assertEquals("2 recipe(s)", recipeBook.toString());
    }

    @Test
    public void equals() {
        recipeBook.addRecipe(AGLIO_OLIO);
        RecipeBook recipeBookCopy = new RecipeBook(recipeBook);
        RecipeBook recipeBook2 = new RecipeBook();
        recipeBook2.addRecipe(FRIED_RICE);

        assertNotEquals(recipeBook, recipeBook.getRecipeList().get(0));
        assertEquals(recipeBook, recipeBook);
        assertEquals(recipeBook, recipeBookCopy);
        assertNotEquals(recipeBook, recipeBook2);
    }

    @Test
    public void hashCode_test() {
        recipeBook.addRecipe(AGLIO_OLIO);
        RecipeBook recipeBookCopy = new RecipeBook(recipeBook);
        RecipeBook recipeBook2 = new RecipeBook();
        recipeBook2.addRecipe(FRIED_RICE);

        assertEquals(recipeBook.hashCode(), recipeBookCopy.hashCode());
        assertNotEquals(recipeBook.hashCode(), recipeBook2.hashCode());
    }
}
