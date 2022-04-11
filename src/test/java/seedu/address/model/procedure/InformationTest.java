package seedu.address.model.procedure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class InformationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Information(null));
    }

    @Test
    public void constructor_invalidInformation_throwsIllegalArgumentException() {
        String invalidInformation = "";
        assertThrows(IllegalArgumentException.class, () -> new Information(invalidInformation));
    }

    @Test
    public void isValidInformation() {
        // null Information
        assertThrows(NullPointerException.class, () -> Information.isValidInformation(null));

        // invalid Information
        assertFalse(Information.isValidInformation("")); // empty string
        assertFalse(Information.isValidInformation(" ")); // spaces only

        // valid Information
        assertTrue(Information.isValidInformation("^")); // only non-alphanumeric characters
        assertTrue(Information.isValidInformation("fixed, cleaned, replaced router")); // non-alphanumeric characters
        assertTrue(Information.isValidInformation("fix router")); // alphabets only
        assertTrue(Information.isValidInformation("12")); // numbers only
        assertTrue(Information.isValidInformation("fix 1 router")); // alphanumeric characters
        assertTrue(Information.isValidInformation("Fixed One Router")); // with capital letters
        assertTrue(Information.isValidInformation("Fixed one router at the apple store!")); // long Informations

    }
}
