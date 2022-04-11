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

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.recipe.RecipeContainsKeywordPredicate;

class ModelManagerTest {

    private ModelManager model;
    private RecipeBook recipeBook;

    @BeforeEach
    public void init() {
        model = new ModelManager();
        recipeBook = new RecipeBook();
        recipeBook.addRecipe(AGLIO_OLIO);
        recipeBook.addRecipe(FRIED_RICE);
        recipeBook.addRecipe(BEEF_TACO);

        model.setRecipeBook(recipeBook);
    }

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), model.getUserPrefs());
        assertEquals(new GuiSettings(), model.getGuiSettings());
        assertEquals(recipeBook, model.getRecipeBook());
    }

    @Test
    public void setUserPrefs_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> model.setUserPrefs(null));
    }

    @Test
    public void setRecipeBookUserPrefs_validInput_success() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setRecipeBookFilePath(Paths.get("recipe/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        model.setUserPrefs(userPrefs);

        assertEquals(userPrefs, model.getUserPrefs());

        UserPrefs expectedUserPrefs = new UserPrefs(userPrefs);
        expectedUserPrefs.setRecipeBookFilePath(Paths.get("new/recipe/book/file/path"));

        assertNotEquals(expectedUserPrefs, model.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> model.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validInput_success() {
        GuiSettings expectedGuiSettings = new GuiSettings(1, 2, 3, 4);
        model.setGuiSettings(expectedGuiSettings);

        assertEquals(expectedGuiSettings, model.getGuiSettings());
    }

    @Test
    public void setRecipeBookFilePath_validPath_success() {
        Path expectedPath = Paths.get("recipe/book/file/path");
        model.setRecipeBookFilePath(expectedPath);

        assertEquals(expectedPath, model.getRecipeBookFilePath());
    }

    @Test
    public void setRecipeBookTest() {
        RecipeBook expectedRecipeBook = new RecipeBook(recipeBook);
        model.setRecipeBook(expectedRecipeBook);

        assertEquals(expectedRecipeBook, model.getRecipeBook());
    }

    @Test
    public void hasRecipe_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> model.hasRecipe(null));
    }

    @Test
    public void hasRecipe_validInput_success() {
        assertFalse(model.hasRecipe(SHOYU_RAMEN));
        assertTrue(model.hasRecipe(AGLIO_OLIO));
    }

    @Test
    public void deleteRecipe_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> model.deleteRecipe(null));
    }

    @Test
    public void deleteRecipe_validInput_success() {
        model.deleteRecipe(AGLIO_OLIO);
        RecipeBook expectedRecipeBook = new RecipeBook();
        expectedRecipeBook.setRecipes(List.of(FRIED_RICE, BEEF_TACO));

        assertEquals(expectedRecipeBook, model.getRecipeBook());
    }

    @Test
    public void addRecipe_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> model.addRecipe(null));
    }

    @Test
    public void addRecipe_validInput_success() {
        model.addRecipe(SHOYU_RAMEN);
        RecipeBook expectedRecipeBook = new RecipeBook();
        expectedRecipeBook.setRecipes(List.of(AGLIO_OLIO, FRIED_RICE, BEEF_TACO, SHOYU_RAMEN));

        assertEquals(expectedRecipeBook, model.getRecipeBook());
    }

    @Test
    public void setRecipe_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                model.setRecipe(null, null));
        assertThrows(NullPointerException.class, () ->
                model.setRecipe(null, SHOYU_RAMEN));
        assertThrows(NullPointerException.class, () ->
                model.setRecipe(SHOYU_RAMEN, null));
    }

    @Test
    public void setRecipe_validInput_success() {
        model.setRecipe(AGLIO_OLIO, SHOYU_RAMEN);
        RecipeBook expectedRecipeBook = new RecipeBook();
        expectedRecipeBook.setRecipes(List.of(SHOYU_RAMEN, FRIED_RICE, BEEF_TACO));

        assertEquals(expectedRecipeBook, model.getRecipeBook());
    }

    @Test
    public void updateFilteredRecipeList_success() {
        RecipeContainsKeywordPredicate asianCuisinePredicate =
                new RecipeContainsKeywordPredicate(List.of("asian"));

        model.updateFilteredRecipeList(asianCuisinePredicate);
        assertEquals(1, model.getFilteredRecipeList().size());

        model.addRecipe(SHOYU_RAMEN);
        assertEquals(4, model.getFilteredRecipeList().size());

        model.updateFilteredRecipeList(asianCuisinePredicate);
        assertEquals(2, model.getFilteredRecipeList().size());
    }

    @Test
    public void equals() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setRecipeBookFilePath(Paths.get("recipe/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        ModelManager model2 = new ModelManager(getTypicalRecipeBook(), userPrefs);
        ModelManager modelManagerCopy = new ModelManager(model.getRecipeBook(), model.getUserPrefs());

        assertNotEquals(model, model.getUserPrefs());
        assertEquals(model, model);
        assertEquals(model, modelManagerCopy);
        assertNotEquals(model, model2);
    }

    @Test
    public void hashCode_test() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setRecipeBookFilePath(Paths.get("recipe/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        ModelManager model2 = new ModelManager(getTypicalRecipeBook(), userPrefs);
        ModelManager modelManagerCopy = new ModelManager(model.getRecipeBook(), model.getUserPrefs());

        assertEquals(model.hashCode(), modelManagerCopy.hashCode());
        assertNotEquals(model.hashCode(), model2.hashCode());
    }
}
