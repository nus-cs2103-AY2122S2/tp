package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class PrefixTest {

    @Test
    void getPrefix_validPrefix_equals() {
        String emptyString = "";
        String tagString = "t/";
        Prefix emptyPrefix = new Prefix("");
        Prefix tagPrefix = new Prefix(tagString);
        assertEquals(emptyPrefix.getPrefix(), emptyString);
        assertEquals(tagPrefix.getPrefix(), tagString);
    }

    @Test
    void getPrefix_validPrefix_notEquals() {
        String tagString = "t/";
        String addressString = "a/";
        Prefix emptyPrefix = new Prefix("");
        Prefix tagPrefix = new Prefix(tagString);
        assertNotEquals(emptyPrefix.getPrefix(), tagString);
        assertNotEquals(tagPrefix.getPrefix(), addressString);
    }

    @Test
    void hashCode_anyString_equals() {
        String randomInputString = "1234214214";
        String validInputString = "n/";
        Prefix randomStringPrefix = new Prefix(randomInputString);
        assertEquals(randomStringPrefix.hashCode(), randomInputString.hashCode());
        Prefix validStringPrefix = new Prefix(validInputString);
        assertEquals(validStringPrefix.hashCode(), validInputString.hashCode());
    }

    @Test
    void hashCode_anyString_notEquals() {
        String randomString = "1234214214";
        String validString = "a/";
        String randomStringComparison = "123431351";
        String validStringComparison = "n/";
        Prefix randomStringPrefix = new Prefix(randomString);
        assertNotEquals(randomStringPrefix.hashCode(), randomStringComparison.hashCode());
        Prefix validStringPrefix = new Prefix(validString);
        assertNotEquals(validStringPrefix.hashCode(), validStringComparison.hashCode());
    }

    @Test
    void equals_anyString_equals() {
        // Comparison between 2 identical and valid prefix should return true.
        assertEquals(new Prefix("a/"), new Prefix("a/"));
        assertEquals(new Prefix("t/"), new Prefix("t/"));
        assertEquals(new Prefix("n/"), new Prefix("n/"));
        assertEquals(new Prefix("m/"), new Prefix("m/"));
        assertEquals(new Prefix("e/"), new Prefix("e/"));
        assertEquals(new Prefix("p/"), new Prefix("p/"));

        // Comparison between 2 identical but random string values should return true,
        assertEquals(new Prefix("12345"), new Prefix("12345"));
    }

    @Test
    void equals_anyString_notEquals() {
        // Different prefix should not be equal to each other.
        assertNotEquals(new Prefix("a/"), new Prefix("t/"));
        assertNotEquals(new Prefix("t/"), new Prefix("n/"));
        assertNotEquals(new Prefix("n/"), new Prefix("m/"));
        assertNotEquals(new Prefix("m/"), new Prefix("e/"));
        assertNotEquals(new Prefix("e/"), new Prefix("p/"));
        assertNotEquals(new Prefix("p/"), new Prefix("a/"));

        // Different prefix with 2 different string values should not be identical.
        assertNotEquals(new Prefix("1234"), new Prefix("12"));
    }
}
