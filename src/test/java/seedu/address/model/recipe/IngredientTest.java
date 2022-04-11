package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class IngredientTest {

    private final Ingredient i1 = new Ingredient("popcorn chicken", 3.2, "kg");
    private final Ingredient i2 = new Ingredient("popcorn", 2.2);
    private final Ingredient i1Copy = new Ingredient(i1.ingredientName, i1.quantity, i1.quantifier);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Ingredient(null, 0, null));
    }

    @Test
    public void constructor_invalidEmptyIngredientName_throwsIllegalArgumentException() {
        String invalidIngredientName = "";
        double validIngredientQty = 5.5;
        String validIngredientQuantifier = "g";
        assertThrows(IllegalArgumentException.class, () ->
                new Ingredient(invalidIngredientName, validIngredientQty, validIngredientQuantifier));
    }

    @Test
    public void constructor_invalidIngredientQty_throwsIllegalArgumentException() {
        String validIngredientName = "Spaghetti";
        double invalidIngredientQty = 0;
        String validIngredientQuantifier = "g";
        assertThrows(IllegalArgumentException.class, () ->
                new Ingredient(validIngredientName, invalidIngredientQty, validIngredientQuantifier));
    }

    @Test
    public void constructor_nullIngredientQuantifier_isValid() {
        String validIngredientName = "Spaghetti";
        double validIngredientQty = 5.5;
        String nullIngredientQuantifier = null;
        assertEquals("Spaghetti 5.5",
                new Ingredient(validIngredientName, validIngredientQty, nullIngredientQuantifier).toString());
    }

    @Test
    public void constructor_invalidNullIngredientName_throwsIllegalArgumentException() {
        String nullIngredientName = null;
        double validIngredientQty = 5.5;
        String validIngredientQuantifier = "g";
        assertThrows(NullPointerException.class, () ->
                new Ingredient(nullIngredientName, validIngredientQty, validIngredientQuantifier));
    }


    @Test
    public void constructor_invalidNegativeIngredientQty_throwsIllegalArgumentException() {
        String invalidIngredientName = "Spaghetti";
        double invalidIngredientQty = -12.4;
        String validIngredientQuantifier = "g";
        assertThrows(IllegalArgumentException.class, () ->
                new Ingredient(invalidIngredientName, invalidIngredientQty, validIngredientQuantifier));
    }

    @Test
    public void isValidIngredient() {
        // null ingredient name, qty, quantifier
        assertThrows(NullPointerException.class, () -> Ingredient.isValidIngredientName(null));
        // assertThrows(NullPointerException.class, () -> Ingredient.isValidQuantity(null));

        // invalid ingredient name
        assertFalse(Ingredient.isValidIngredientName("")); // empty string
        assertFalse(Ingredient.isValidIngredientName(" ")); // spaces only
        assertFalse(Ingredient.isValidIngredientName("       ")); // lots of spaces only

        // invalid ingredient qty
        // assertFalse(Ingredient.isValidQuantity("")); // empty string
        assertFalse(Ingredient.isValidIngredientName(" ")); // spaces only
        assertFalse(Ingredient.isValidIngredientName("       ")); // lots of spaces only
        assertFalse(Ingredient.isValidIngredientName("-1")); // negative value only
        assertFalse(Ingredient.isValidIngredientName("-24.23")); // negative double only


        // valid ingredient name
        assertTrue(Ingredient.isValidIngredientName("Spaghetti")); // 1 word ingredient
        assertTrue(Ingredient.isValidIngredientName("Raw tomatoes")); // 2 word ingredient
        assertTrue(Ingredient.isValidIngredientName("Organic brown rice")); // 3 word ingredient

        // valid ingredient qty
        assertTrue(Ingredient.isValidQuantity(5)); // 1 digit int
        assertTrue(Ingredient.isValidQuantity(100)); // 3 digit int
        assertTrue(Ingredient.isValidQuantity(2.5)); // 1 decimal place double
        assertTrue(Ingredient.isValidQuantity(2.75)); // 2 decimal place double

    }

    @Test
    public void toString_test() {
        String expectedValue1 = "popcorn chicken " + "3.2 " + "kg";
        String expectedValue2 = "popcorn " + "2.2";

        assertEquals(expectedValue1, i1.toString());
        assertEquals(expectedValue2, i2.toString());
    }
    @Test
    public void equals() {
        assertNotEquals(i1, i1.ingredientName);
        assertEquals(i1, i1);
        assertEquals(i1, i1Copy);
        assertNotEquals(i1, i2);
    }

    @Test
    public void hashCode_test() {
        assertEquals(i1.hashCode(), i1Copy.hashCode());
        assertNotEquals(i1.hashCode(), i2.hashCode());
    }
}
