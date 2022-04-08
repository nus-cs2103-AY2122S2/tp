package seedu.contax.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    private static final String INVALID_NAME = " ";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Name(INVALID_NAME));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName(" test")); // begins with whitespace
        assertFalse(Name.isValidName("work?")); // contains invalid non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("Work Meeting")); // EP: alphabets only
        assertTrue(Name.isValidName("12345")); // EP: numbers only
        assertTrue(Name.isValidName(".,!@#$%&*()+_-=")); // EP: Contains only valid symbols
        assertTrue(Name.isValidName("2nd Work Meeting!")); // EP: Alphanumeric and symbols

        assertTrue(Name.isValidName("Very important meeting with some Client 2!")); // long names
    }

    @Test
    public void stringConversion() {
        assertEquals("A Mix of 3 Types Of Characters!",
                new Name("A Mix of 3 Types Of Characters!").toString());
    }

    @Test
    public void objectEquality() {
        Name reference1 = new Name("Work Meeting");
        Name reference2 = new Name("Work Meeting 2");

        assertTrue(reference1.equals(reference1));
        assertTrue(reference1.equals(new Name("Work Meeting")));

        assertFalse(reference1.equals(null)); // Null
        assertFalse(reference1.equals("some string")); // Different Type
        assertFalse(reference1.equals(1)); // Different Type

        assertFalse(reference1.equals(reference2)); // Different Name
        assertFalse(reference1.equals(new Name("work meeting"))); // Different Case
    }

    @Test
    public void hashCodeEquality() {
        Name reference = new Name("Work Meeting");

        assertEquals(reference.hashCode(), new Name("Work Meeting").hashCode());
        assertNotEquals(reference.hashCode(), new Name("Work Meeting 2").hashCode());
    }
}
