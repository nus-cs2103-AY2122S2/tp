package seedu.address.model.applicant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;

class NricTest {

    @Test
    public void constructor_invalidNric_throwsIllegalArgumentException() {
        String invalidNric = "";
        assertThrows(IllegalArgumentException.class, () -> new Nric(invalidNric));
    }

    @Test
    void isValidNric() {
        // null nric
        assertThrows(NullPointerException.class, () -> Nric.isValidNric(null));

        // invalid nric
        assertFalse(Nric.isValidNric("")); // empty string
        assertFalse(Nric.isValidNric(" ")); // spaces only
        assertFalse(Nric.isValidNric("112345677")); // only numeric characters
        assertFalse(Nric.isValidNric("s1234567*")); // contains non-alphanumeric characters

        // valid nric
        assertTrue(Name.isValidName("s0978347t")); // all letters lowercase
        assertTrue(Name.isValidName("A1670384y")); // last letter lowercase
        assertTrue(Name.isValidName("G9853957D")); // all letters uppercase
        assertTrue(Name.isValidName("S9901010A")); // all letters uppercase
        assertTrue(Name.isValidName("f6538567Y")); // last letter uppercase
    }

    @Test
    void testEquals() {
        Nric lowercase = new Nric("s1239878e");
        Nric uppercase = new Nric("S1239878E");
        Nric firstLower = new Nric("s1239878E");
        assertEquals(lowercase, uppercase);
        assertEquals(lowercase, firstLower);
        assertEquals(uppercase, firstLower);
    }
}
