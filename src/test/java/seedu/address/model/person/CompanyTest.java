package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompanyTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Company(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalid = "";
        assertThrows(IllegalArgumentException.class, () -> new Company(invalid));
    }

    @Test
    public void isValidCompany() {
        // null company
        assertThrows(NullPointerException.class, () -> Company.isValidCompany(null));

        // invalid company
        assertFalse(Company.isValidCompany("")); // empty string
        assertFalse(Company.isValidCompany(" ")); // spaces only
        assertFalse(Company.isValidCompany(" Orange")); //first character blank

        // valid company
        assertTrue(Company.isValidCompany("peter jack")); // alphabets only
        assertTrue(Company.isValidCompany("12345")); // numbers only
        assertTrue(Company.isValidCompany("peter the 2nd")); // alphanumeric characters
        assertTrue(Company.isValidCompany("Capital Tan")); // with capital letters
        assertTrue(Company.isValidCompany("David Roger Jackson Ray Jr 2nd")); // long names
        assertTrue(Company.isValidCompany("@#$%^&*(")); //pure symbols
        assertTrue(Company.isValidCompany("@BakerBoi {c00k1n9 **GRILL777**} !!!!")); //random combination
    }
}
