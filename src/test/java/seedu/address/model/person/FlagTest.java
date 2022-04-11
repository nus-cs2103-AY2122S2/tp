package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FlagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters
        assertFalse(Name.isValidName("Peter  Jack")); // contains more than a single space
        assertFalse(Name.isValidName("PeterJack ")); // contains space at the end
        assertFalse(Name.isValidName(" PeterJack")); // contains space at the start

        // valid name
        assertTrue(Name.isValidName("Peter Jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("pEter Jack")); // unconventional name
        assertTrue(Name.isValidName("12A Ab")); // unconventional name with numbers
        assertTrue(Name.isValidName("Peter The 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void containsKeyword() {
        Name testName = new Name("Jonathan Chen Xiao Ming");

        assertFalse(testName.containsKeyword(""));
        assertFalse(testName.containsKeyword("a"));
        assertFalse(testName.containsKeyword("John"));
        assertFalse(testName.containsKeyword("Che"));
        assertFalse(testName.containsKeyword("Johnathan Che"));

        assertTrue(testName.containsKeyword("Jonathan"));
        assertTrue(testName.containsKeyword("jonathan"));
        assertTrue(testName.containsKeyword("joNATHAN"));
        assertTrue(testName.containsKeyword("JoNatHan"));

        assertTrue(testName.containsKeyword("jonathan Chen"));
        assertTrue(testName.containsKeyword("Chen Xiao Ming"));
        assertTrue(testName.containsKeyword("xiao ming"));
    }
}
