package seedu.contax.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.Assert.assertThrows;

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

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("work*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("work meeting")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("2nd work meeting")); // alphanumeric characters
        assertTrue(Name.isValidName("IMPORTANT work meeting")); // with capital letters
        assertTrue(Name.isValidName("Very important meeting with some Client 2")); // long names
    }

    @Test
    public void stringConversion() {
        assertEquals("Work Meeting 1", new Name("Work Meeting 1").toString());
        assertEquals("123455", new Name("123455").toString());
        assertEquals("very long name that is very long",
                new Name("very long name that is very long").toString());
    }

    @Test
    public void objectEquality() {
        Name reference1 = new Name("Work Meeting");
        Name reference2 = new Name("Work Meeting 2");

        assertTrue(reference1.equals(new Name("Work Meeting")));
        assertTrue(reference2.equals(new Name("Work Meeting 2")));
        assertTrue(reference1.equals(reference1));

        assertFalse(reference1.equals("some string"));
        assertFalse(reference1.equals(reference2));
        assertFalse(reference1.equals(new Name("Wrong Name")));
        assertFalse(reference1.equals(new Name("work meeting")));
    }

    @Test
    public void hashCodeEquality() {
        Name reference = new Name("Work Meeting");

        assertEquals(reference.hashCode(), new Name("Work Meeting").hashCode());
        assertNotEquals(reference.hashCode(), new Name("Work Meeting 2").hashCode());
    }
}
