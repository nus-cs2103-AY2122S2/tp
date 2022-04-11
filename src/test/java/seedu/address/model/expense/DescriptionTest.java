package seedu.address.model.expense;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void isValidDescription() {
        // null Description
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid Description
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only
        assertFalse(Description.isValidDescription("^")); // only non-alphanumeric characters
        assertFalse(Description.isValidDescription("taxi*")); // contains non-alphanumeric characters

        // valid Description
        assertTrue(Description.isValidDescription("mcdonalds")); // alphabets only
        assertTrue(Description.isValidDescription("12345")); // numbers only
        assertTrue(Description.isValidDescription("pizza on 3rd")); // alphanumeric characters
        assertTrue(Description.isValidDescription("Encik TAN")); // with capital letters
        assertTrue(Description.isValidDescription("3 plates of chicken rice from the deck")); // long Description
    }


}
