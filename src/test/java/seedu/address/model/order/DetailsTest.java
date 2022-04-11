package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DetailsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Details(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidDetails = " ";
        assertThrows(IllegalArgumentException.class, () -> new Details(invalidDetails));
    }

    @Test
    public void isValidDetail() {
        // null details
        assertThrows(NullPointerException.class, () -> Details.isValidDetails(null));

        // invalid details
        assertFalse(Details.isValidDetails("")); // empty string
        assertFalse(Details.isValidDetails(" ")); // only whitespace
        assertFalse(Details.isValidDetails(":")); // only colon
        assertFalse(Details.isValidDetails("1:   ")); // only passing whitespace
        assertFalse(Details.isValidDetails("chocolatecake: 1")); // quantity and item in wrong order
        assertFalse(Details.isValidDetails("one: chocolatecake")); // quantity not in numbers
        assertFalse(Details.isValidDetails("1twothree : chocolate cake")); // alphanumeric characters in quantity
        assertFalse(Details.isValidDetails(
                "1: chocolate cake ?? 1 of each *")); // non-alphabet and numeric characters present in order item
        assertFalse(Details.isValidDetails("1: choco 2: cake")); // passing in multiple details at the same time
        assertFalse(Details.isValidDetails("1:     ")); // all blank spaces

        // valid details
        assertTrue(Details.isValidDetails("1: chocolatecake")); // expected format
        assertTrue(Details.isValidDetails("   1 : chocolate cake   ")); // whitespaces in-front of quantity
        assertTrue(Details.isValidDetails("1      :      chocolate cake")); // white spaces between quantity and item
        assertTrue(Details.isValidDetails("1:chocolatecake")); // no whitespaces in-between
        assertTrue(Details.isValidDetails("1: chOcOlateCake")); // uppercase characters
        assertTrue(Details.isValidDetails("1: a  very    big  cake")); // irregular amount of whitespaces in-between
    }

}
