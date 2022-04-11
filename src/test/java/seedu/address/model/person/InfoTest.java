package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class InfoTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Info(null));
    }

    @Test
    public void constructor_invalidInfo_throwsIllegalArgumentException() {
        String invalidInfo = "";
        assertThrows(IllegalArgumentException.class, () -> new Info(invalidInfo));
    }

    @Test
    void isValidInfo() {
        // null info
        assertThrows(NullPointerException.class, () -> Info.isValidInfo(null));

        // invalid info
        assertFalse(Info.isValidInfo("")); // empty string
        assertFalse(Info.isValidInfo(" ")); // spaces only
        assertFalse(Info.isValidInfo("\n")); // next line only
        assertFalse(Info.isValidInfo("Salary of $5000 \n UnderScheme A")); // alphanumerics with next line

        // valid info
        assertTrue(Info.isValidInfo("interested in investing")); // alphabets only
        assertTrue(Info.isValidInfo("salary - $4500")); // alphanumeric and symbols
        assertTrue(Info.isValidInfo("Scheme A and wants to invest")); // long info
    }
}
