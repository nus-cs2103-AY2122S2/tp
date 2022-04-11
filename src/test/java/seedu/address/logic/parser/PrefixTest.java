package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class PrefixTest {

    private final Prefix name = new Prefix("-n");
    private final Prefix index = new Prefix("-x");

    @Test
    public void equals_test() {
        Prefix nameCopy = new Prefix(name.getPrefix());

        assertEquals(name.toString(), "-n");

        assertNotEquals(name, name.getPrefix());
        assertEquals(name, name);
        assertEquals(name, nameCopy);
        assertNotEquals(name, index);
    }

    @Test
    public void equalityCheck() {
        Prefix nameCopy = new Prefix(name.getPrefix());

        assertEquals(name.hashCode(), nameCopy.hashCode());
        assertNotEquals(name.hashCode(), index.hashCode());
    }

}
