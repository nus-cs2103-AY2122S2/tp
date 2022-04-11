package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

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
        String edgeName = "";
        for (int i = 0; i < Name.CHARACTER_LIMIT; i++) {
            edgeName += "a";
        }
        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName(" peter")); // starts with white spaces
        assertFalse(Name.isValidName(edgeName + "a")); //too long name

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("李白")); // only non-alphanumeric characters
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("Peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd"));
        assertTrue(Name.isValidName(edgeName)); //long name
    }

    @Test
    public void exactEquals() {

        Name validNameAmy = new Name(VALID_NAME_AMY);

        // same object -> returns true
        assertTrue(validNameAmy.exactEquals(validNameAmy));

        // same values -> returns true
        Name validNameAmyCopy = new Name(VALID_NAME_AMY);
        assertTrue(validNameAmy.exactEquals(validNameAmyCopy));

        // different capitalization -> returns false
        Name validNameAmyAllCaps = new Name(VALID_NAME_AMY.toUpperCase());
        assertFalse(validNameAmy.exactEquals(validNameAmyAllCaps));

        // null -> returns false
        assertFalse(validNameAmy.exactEquals(null));

        // different name -> returns false
        Name validNameBob = new Name(VALID_NAME_BOB);
        assertFalse(validNameAmy.exactEquals(validNameBob));
    }

    @Test
    public void equals() {

        Name validNameAmy = new Name(VALID_NAME_AMY);
        Name validNameBob = new Name(VALID_NAME_BOB);

        // same object -> returns true
        assertTrue(validNameAmy.equals(validNameAmy));

        // same values -> returns true
        Name validNameAmyCopy = new Name(VALID_NAME_AMY);
        assertTrue(validNameAmy.equals(validNameAmyCopy));

        // different capitalization -> returns true
        Name validNameAmyAllCaps = new Name(VALID_NAME_AMY.toUpperCase());
        assertTrue(validNameAmy.equals(validNameAmyAllCaps));

        // different types -> returns false
        assertFalse(validNameAmy.equals(1));

        // null -> returns false
        assertFalse(validNameAmy.equals(null));

        // different name -> returns false
        assertFalse(validNameAmy.equals(validNameBob));
    }

}
