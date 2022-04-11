package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class NameTest {
    private final Name n1 = new Name("test 00");
    private final Name n2 = new Name("test 01");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = " ";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void toString_test() {
        assertEquals(n1.toString(), "test 00");
    }

    @Test
    public void isValidName() {
        String validName = "Aglio Olio";
        assertTrue(Name.isValidName(validName));
    }

    @Test
    public void equals_test() {
        Name n1Copy = new Name(n1.fullName);

        assertNotEquals(n1, n1.fullName);
        assertEquals(n1, n1);
        assertEquals(n1, n1Copy);
        assertNotEquals(n1, n2);
    }

    @Test
    public void hashCode_test() {
        Name n1Copy = new Name(n1.fullName);

        assertEquals(n1.hashCode(), n1Copy.hashCode());
        assertNotEquals(n1.hashCode(), n2.hashCode());
    }
}
