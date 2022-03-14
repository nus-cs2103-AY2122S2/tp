package seedu.contax.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for Tag names.
 */
public class NameTest {

    @Test
    public void isValidName(){
        // only alphabets
        assertTrue(Name.isValidName("Friends"));
        // only digits
        assertTrue(Name.isValidName("12345"));
        // Combination of alphabets and digits
        assertTrue(Name.isValidName("L33tC0de"));
        // Combination of alphabets and digits with spacing
        assertTrue(Name.isValidName("CS2103 Group mates"));

        // Empty string
        assertFalse(Name.isValidName(""));

        // Space only
        assertFalse(Name.isValidName(" "));

        // Symbols
        assertFalse(Name.isValidName("&"));

        // Symbols within valid characters
        assertFalse(Name.isValidName("Fr!ends"));
        assertFalse(Name.isValidName("Adversary_"));
        assertFalse(Name.isValidName("!mp0ss!bl3&"));
    }

    @Test
    public void hashCode_test() {
        Name name = new Name("friends");
        Name name2 = new Name("family");

        assertEquals(name.hashCode(), name.hashCode());
        assertEquals(name.hashCode(), new Name("friends").hashCode());

        assertNotEquals(name.hashCode(), name2.hashCode());
    }

    @Test
    public void equals() {
        Name name = new Name("friends");
        Name name2 = new Name("family");

        assertTrue(name.equals(name));
        assertTrue(name.equals(new Name("friends")));

        assertFalse(name.equals(name2));
        assertFalse(name.equals(null));
        assertFalse(name.equals(0));
    }
}
