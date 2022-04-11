package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecipes.AGLIO_OLIO;
import static seedu.address.testutil.TypicalRecipes.BEEF_TACO;
import static seedu.address.testutil.TypicalRecipes.FRIED_RICE;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.recipe.exceptions.DuplicateRecipeException;
import seedu.address.model.recipe.exceptions.RecipeNotFoundException;
import seedu.address.testutil.RecipeBuilder;

class UniqueRecipeListTest {
    private UniqueRecipeList uniqueRecipeList = new UniqueRecipeList();
    private final Recipe editedAglioOlio = new RecipeBuilder(AGLIO_OLIO)
            .withTags("extra")
            .withTags("tags")
            .build();

    @BeforeEach
    public void init() {
        uniqueRecipeList = new UniqueRecipeList();
    }

    @Test
    public void contains_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.contains(null));
    }

    @Test
    public void contains_recipeNotInList_returnsFalse() {
        assertFalse(uniqueRecipeList.contains(AGLIO_OLIO));
    }

    @Test
    public void contains_recipeInList_returnsTrue() {
        uniqueRecipeList.add(AGLIO_OLIO);

        assertTrue(uniqueRecipeList.contains(AGLIO_OLIO));
    }

    @Test
    public void contains_recipeWithSameName_returnsTrue() {
        uniqueRecipeList.add(AGLIO_OLIO);

        assertTrue(uniqueRecipeList.contains(editedAglioOlio));
    }

    @Test
    public void add_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.add(null));
    }

    @Test
    public void add_duplicateRecipe_throwsDuplicateRecipeException() {
        uniqueRecipeList.add(AGLIO_OLIO);

        assertThrows(DuplicateRecipeException.class, () -> uniqueRecipeList.add(AGLIO_OLIO));
    }

    @Test
    public void add_recipeWithSameName_throwsDuplicateRecipeException() {
        uniqueRecipeList.add(AGLIO_OLIO);

        assertThrows(DuplicateRecipeException.class, () -> uniqueRecipeList.add(editedAglioOlio));
    }

    @Test
    public void setRecipe_nullRecipeParameter_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.setRecipe(null, null));
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.setRecipe(null, AGLIO_OLIO));
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.setRecipe(AGLIO_OLIO, null));
    }

    @Test
    public void setRecipe_inputRecipeNotFound_throwsRecipeNotFoundException() {
        assertThrows(RecipeNotFoundException.class, () -> uniqueRecipeList.setRecipe(AGLIO_OLIO, AGLIO_OLIO));
    }

    @Test
    public void setRecipe_listContainsRecipe_throwsDuplicateRecipeException() {
        uniqueRecipeList.add(AGLIO_OLIO);
        uniqueRecipeList.add(FRIED_RICE);

        assertThrows(DuplicateRecipeException.class, () -> uniqueRecipeList.setRecipe(AGLIO_OLIO, FRIED_RICE));
    }

    @Test
    public void setRecipe_inputNotInList_success() {
        uniqueRecipeList.add(AGLIO_OLIO);
        uniqueRecipeList.add(FRIED_RICE);
        uniqueRecipeList.setRecipe(FRIED_RICE, BEEF_TACO);

        UniqueRecipeList expectedList = new UniqueRecipeList();
        expectedList.add(AGLIO_OLIO);
        expectedList.add(BEEF_TACO);

        assertEquals(expectedList, uniqueRecipeList);
    }

    @Test
    public void remove_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.remove(null));
    }

    @Test
    public void remove_recipeDoesNotExist_throwsRecipeNotFoundException() {
        assertThrows(RecipeNotFoundException.class, () -> uniqueRecipeList.remove(AGLIO_OLIO));
    }

    @Test
    public void remove_existingRecipe_success() {
        uniqueRecipeList.add(AGLIO_OLIO);
        uniqueRecipeList.remove(AGLIO_OLIO);

        assertEquals(uniqueRecipeList, new UniqueRecipeList());
    }

    @Test
    public void setRecipes_nullUniqueRecipeList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.setRecipes((UniqueRecipeList) null));
    }

    @Test
    public void setRecipes_replaceListWithInputUniqueRecipeList() {
        uniqueRecipeList.add(AGLIO_OLIO);

        UniqueRecipeList expectedList = new UniqueRecipeList();
        expectedList.add(FRIED_RICE);
        uniqueRecipeList.setRecipes(expectedList);

        assertEquals(expectedList, uniqueRecipeList);
    }

    @Test
    public void setRecipes_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.setRecipes((List<Recipe>) null));
    }

    @Test
    public void setRecipe_list_replaceListWithInputList() {
        uniqueRecipeList.add(AGLIO_OLIO);
        uniqueRecipeList.setRecipes(List.of(FRIED_RICE));

        UniqueRecipeList expectedList = new UniqueRecipeList();
        expectedList.add(FRIED_RICE);

        assertEquals(expectedList, uniqueRecipeList);
    }

    @Test
    public void setRecipe_listWithDuplicates_throwsDuplicateRecipeException() {
        assertThrows(DuplicateRecipeException.class, () ->
                uniqueRecipeList.setRecipes(List.of(AGLIO_OLIO, AGLIO_OLIO)));
    }

    @Test
    public void asUnmodifiableObservableList_modify_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueRecipeList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void iterator() {
        final UniqueRecipeList list = new UniqueRecipeList();

        list.add(AGLIO_OLIO);
        assertTrue(list.iterator().hasNext());

        list.remove(AGLIO_OLIO);
        assertFalse(list.iterator().hasNext());
        assertEquals(list, new UniqueRecipeList());

        assertThrows(NoSuchElementException.class, () -> list.iterator().next());
    }

    @Test
    public void equals() {
        final UniqueRecipeList list1 = new UniqueRecipeList();
        final UniqueRecipeList list2 = new UniqueRecipeList();
        final UniqueRecipeList list1Copy = new UniqueRecipeList();

        list1.add(AGLIO_OLIO);
        list2.add(FRIED_RICE);
        list1Copy.setRecipes(list1);

        assertNotEquals(list1, list1.asUnmodifiableObservableList().get(0));
        assertEquals(list1, list1);
        assertEquals(list1, list1Copy);
        assertNotEquals(list1, list2);
    }

    @Test
    public void hashCode_test() {
        final UniqueRecipeList list1 = new UniqueRecipeList();
        final UniqueRecipeList list2 = new UniqueRecipeList();
        final UniqueRecipeList list1Copy = new UniqueRecipeList();

        list1.add(AGLIO_OLIO);
        list2.add(FRIED_RICE);
        list1Copy.setRecipes(list1);

        assertEquals(list1.hashCode(), list1Copy.hashCode());
        assertNotEquals(list1.hashCode(), list2.hashCode());
    }
}
