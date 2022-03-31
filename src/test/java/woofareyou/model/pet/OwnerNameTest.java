package woofareyou.model.pet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static woofareyou.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import woofareyou.testutil.Assert;

public class OwnerNameTest {
    public static final String OWNER_NAME_1 = "Lau Low";
    public static final String OWNER_NAME_2 = "Zack";
    public static final String OWNER_NAME_3 = "Adam";
    public static final String OWNER_NAME_4 = "Ah Tan";

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new OwnerName(null));
    }

    @Test
    public void constructor_invalidOwnerName_throwsIllegalArgumentException() {
        String invalidOwnerName = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new OwnerName(invalidOwnerName));
    }

    @Test
    public void isValidOwnerName() {
        // null ownerName
        Assert.assertThrows(NullPointerException.class, () -> OwnerName.isValidOwnerName(null));

        // invalid ownerName
        assertFalse(OwnerName.isValidOwnerName("")); // empty string
        assertFalse(OwnerName.isValidOwnerName(" ")); // spaces only
        assertFalse(OwnerName.isValidOwnerName("^")); // only non-alphanumeric characters
        assertFalse(OwnerName.isValidOwnerName("peter*")); // contains non-alphanumeric characters

        // valid ownerName
        assertTrue(OwnerName.isValidOwnerName("peter jack")); // alphabets only
        assertTrue(OwnerName.isValidOwnerName("12345")); // numbers only
        assertTrue(OwnerName.isValidOwnerName("peter the 2nd")); // alphanumeric characters
        assertTrue(OwnerName.isValidOwnerName("Capital Tan")); // with capital letters
        assertTrue(OwnerName.isValidOwnerName("David Roger Jackson Ray Jr 2nd")); // long names
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
