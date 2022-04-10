package seedu.trackermon.model.show;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackermon.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for {@code Name}.
 */
public class NameTest {

    /**
     * Tests constructor of {@code Name} in the event of null input.
     */
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    /**
     * Tests constructor of {@code Name} in the event of invalid input.
     */
    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    /**
     * Tests isValidName method of {@code Name}.
     */
    @Test
    public void test_isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // valid name
        String validString500Characters = "qwrnjbqwrkbqwkrjbqkjwrbjqkwbrkjqbkmasdbkmbdajkbdjkabsdjkbaskjdbakj"
                + "sbdjkasbsdjkbqasjkdbjkwabdjkbwqjkdbqwjkbdqwjkbdjkqwbdjkasdmadnmaasndnjkldnwqlkndqk"
                + "mdlqwknqwdjlbnklqdwnklqwjdklqwndklnqwkldnqwklndklqwndklqwwqenoqwieoqweionnnnnnnnnnnn"
                + "nnnnnnnnnnndadadadqwrnjbqwrkbqwkrjbqkjwrbjqkwbrkjqbkmasdbkmbdajkbdjkabsdjkbaskjdbakj"
                + "sbdjkasbsdjkbqasjkdbjkwabdjkbwqjkdbqwjkbdqwjkbdjkqwbdjkasdmdnmasndnjkldnwqlkndqkmdlq"
                + "wknqwdjlbnklqdwnklqwjdklqwndklnqwkldnqwklndklqwndklqwwqenosadasdadasdasdsafqwrwrafaw"
                + "rfwfraweadasdfas";
        System.out.println(validString500Characters.length());
        String invalidString501 = validString500Characters + "a";

        //invalid name
        assertFalse(Name.isValidName(invalidString501)); // string too long
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("you*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidName(validString500Characters));
        assertTrue(Name.isValidName("You")); // alphabets only
        assertTrue(Name.isValidName("007")); // numbers only
        assertTrue(Name.isValidName("Peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("YoU")); // with capital letters
        assertTrue(Name.isValidName("The Time I Got Bang Down by Truck kun")); // long names
    }

    /**
     * Tests toString method of {@code Name}.
     */
    @Test
    void testToString() {
        Name testName = new Name("Another");
        assertEquals("Another", testName.name);
    }

    /**
     * Tests equals method of {@code Name}.
     */
    @Test
    void testEquals() {
        Name testName = new Name("Another");
        assertTrue(testName.equals(testName));
    }

    /**
     * Tests hashcode method of {@code Name}.
     */
    @Test
    void testHashCode() {
        Name testName = new Name("Another");
        assertEquals("Another".hashCode(), testName.hashCode());
    }

    /**
     * Tests compareTo method of {@code Name}.
     */
    @Test
    void testCompareTo() {
        Name testName = new Name("Another");
        assertEquals(0, testName.compareTo(new Name("ANOTHER")));
    }
}
