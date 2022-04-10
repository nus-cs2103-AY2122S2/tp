package woofareyou.model.pet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static woofareyou.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class OwnerNameTest {
    public static final String OWNER_NAME_1 = "Lau Low";
    public static final String OWNER_NAME_2 = "Zack";
    public static final String OWNER_NAME_3 = "Adam";
    public static final String OWNER_NAME_4 = "Ah Tan";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OwnerName(null));
    }

    @Test
    public void constructor_invalidOwnerName_throwsIllegalArgumentException() {
        String invalidOwnerName = "";
        assertThrows(IllegalArgumentException.class, () -> new OwnerName(invalidOwnerName));
    }

    @Test
    public void isValidOwnerName() {
        // null ownerName
        assertThrows(NullPointerException.class, () -> OwnerName.isValidOwnerName(null));

        // invalid ownerName
        assertFalse(OwnerName.isValidOwnerName("")); // empty string
        assertFalse(OwnerName.isValidOwnerName(" ")); // spaces only
        assertFalse(OwnerName.isValidOwnerName("^")); // only non-alphanumeric characters
        assertFalse(OwnerName.isValidOwnerName("peter*")); // contains non-alphanumeric characters
        assertFalse(OwnerName.isValidOwnerName("12345")); // numbers only
        assertFalse(OwnerName.isValidOwnerName("peter the 2nd")); // alphanumeric characters


        // valid ownerName
        assertTrue(OwnerName.isValidOwnerName("p")); // single character
        assertTrue(OwnerName.isValidOwnerName("peter jack")); // alphabets only
        assertTrue(OwnerName.isValidOwnerName("Capital Tan")); // with capital letters
        assertTrue(OwnerName.isValidOwnerName("David Roger Jackson Ray Jr")); // long names
    }

    @Test
    public void compareTo() {

        assertTrue(OWNER_NAME_1.compareTo(OWNER_NAME_1) == 0);
        assertTrue(OWNER_NAME_2.compareTo(OWNER_NAME_2) == 0);
        assertTrue(OWNER_NAME_3.compareTo(OWNER_NAME_3) == 0);

        assertTrue(OWNER_NAME_1.compareTo(OWNER_NAME_2) < 0);
        assertTrue(OWNER_NAME_2.compareTo(OWNER_NAME_3) > 0);
        assertTrue(OWNER_NAME_3.compareTo(OWNER_NAME_1) < 0);

        assertFalse(OWNER_NAME_1.compareTo(OWNER_NAME_2) > 0);
        assertFalse(OWNER_NAME_2.compareTo(OWNER_NAME_3) < 0);
        assertFalse(OWNER_NAME_3.compareTo(OWNER_NAME_1) > 0);

        assertFalse(OWNER_NAME_4.compareTo(OWNER_NAME_3) == 0);
        assertTrue(OWNER_NAME_4.compareTo(OWNER_NAME_3) > 0);
        assertTrue(OWNER_NAME_3.compareTo(OWNER_NAME_4) < 0);
    }
}
