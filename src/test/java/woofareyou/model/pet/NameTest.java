package woofareyou.model.pet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static woofareyou.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {
    public static final String PET_NAME_1 = "MOJO";
    public static final String PET_NAME_2 = "PIKA";
    public static final String PET_NAME_3 = "APPLE";
    public static final String PET_NAME_4 = "APPO";

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
        assertFalse(Name.isValidName("12345")); // numbers only
        assertFalse(Name.isValidName("peter the 2nd")); // alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("p")); // single character
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr")); // long names
    }

    @Test
    public void compareTo() {

        assertTrue(PET_NAME_1.compareTo(PET_NAME_1) == 0);
        assertTrue(PET_NAME_2.compareTo(PET_NAME_2) == 0);
        assertTrue(PET_NAME_3.compareTo(PET_NAME_3) == 0);

        assertTrue(PET_NAME_1.compareTo(PET_NAME_2) < 0);
        assertTrue(PET_NAME_2.compareTo(PET_NAME_3) > 0);
        assertTrue(PET_NAME_3.compareTo(PET_NAME_1) < 0);

        assertFalse(PET_NAME_1.compareTo(PET_NAME_2) > 0);
        assertFalse(PET_NAME_2.compareTo(PET_NAME_3) < 0);
        assertFalse(PET_NAME_3.compareTo(PET_NAME_1) > 0);

        assertFalse(PET_NAME_4.compareTo(PET_NAME_3) == 0);
        assertTrue(PET_NAME_4.compareTo(PET_NAME_3) > 0);
        assertTrue(PET_NAME_3.compareTo(PET_NAME_4) < 0);
    }
}
