package seedu.address.model.job;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompanyNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompanyName(null));
    }

    @Test
    public void constructor_invalidCompanyName_throwsIllegalArgumentException() {
        String invalidCompanyName = "";
        assertThrows(IllegalArgumentException.class, () -> new CompanyName(invalidCompanyName));
    }

    @Test
    public void isValidCompanyName() {
        // null company name
        assertThrows(NullPointerException.class, () -> CompanyName.isValidCompanyName(null));

        // invalid company name
        assertFalse(CompanyName.isValidCompanyName("")); // empty string
        assertFalse(CompanyName.isValidCompanyName(" ")); // spaces only

        // valid company
        assertTrue(CompanyName.isValidCompanyName("apple")); // alphabets only
        assertTrue(CompanyName.isValidCompanyName("12345")); // numbers only
        assertTrue(CompanyName.isValidCompanyName("forever 21")); // alphanumeric characters
        assertTrue(CompanyName.isValidCompanyName("Paris Baguette")); // with capital letters
        assertTrue(CompanyName.isValidCompanyName("Monsters Inc.")); // with symbols
    }

}
