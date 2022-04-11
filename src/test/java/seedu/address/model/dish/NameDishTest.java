package seedu.address.model.dish;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameDishTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NameDish(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new NameDish(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> NameDish.isValidName(null));

        // invalid name
        assertFalse(NameDish.isValidName("")); // empty string
        assertFalse(NameDish.isValidName(" ")); // spaces only
        assertFalse(NameDish.isValidName("^")); // only non-alphanumeric characters
        assertFalse(NameDish.isValidName("rice*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(NameDish.isValidName("peter jack")); // alphabets only
        assertTrue(NameDish.isValidName("12345")); // numbers only
        assertTrue(NameDish.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(NameDish.isValidName("Capital Tan")); // with capital letters
        assertTrue(NameDish.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
