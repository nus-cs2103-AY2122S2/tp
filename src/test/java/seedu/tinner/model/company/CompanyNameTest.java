package seedu.tinner.model.company;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tinner.testutil.Assert.assertThrows;
import static seedu.tinner.testutil.TypicalCompanies.META;
import static seedu.tinner.testutil.TypicalCompanies.WHATSAPP;

import org.junit.jupiter.api.Test;

public class CompanyNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompanyName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new CompanyName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> CompanyName.isValidName(null));

        // invalid name
        assertFalse(CompanyName.isValidName("")); // empty string
        assertFalse(CompanyName.isValidName(" ")); // spaces only
        assertFalse(CompanyName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(CompanyName.isValidName("peter*")); // contains non-alphanumeric characters
        assertFalse(CompanyName.isValidName("David Roger Jackson Ray Jr 2nd Son")); // long names exceeding max

        // valid name
        assertTrue(CompanyName.isValidName("peter jack")); // alphabets only
        assertTrue(CompanyName.isValidName("12345")); // numbers only
        assertTrue(CompanyName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(CompanyName.isValidName("Capital Tan")); // with capital letters
        assertTrue(CompanyName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names within max
    }

    @Test
    public void equals() {
        // same company name with diff case -> return true
        CompanyName meta = new CompanyName(META.getName().toString().toLowerCase());
        assertTrue(meta.equals(META.getName()));

        // same company name with diff case and spaces-> return true
        CompanyName meta2 = new CompanyName(META.getName().toString().toLowerCase() + " 1 2");
        assertTrue(meta2.equals(new CompanyName(META.getName().toString() + " 1      2   ")));

        // different company name -> return false
        assertFalse(meta.equals(WHATSAPP.getName()));
    }
}
