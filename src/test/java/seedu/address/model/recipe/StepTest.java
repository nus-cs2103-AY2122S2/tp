package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class StepTest {
    private final Step s1 = new Step("chop garlic");
    private final Step s2 = new Step("saute garlic");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Step(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidStep = " ";
        assertThrows(IllegalArgumentException.class, () -> new Step(invalidStep));
    }

    @Test
    public void toString_test() {
        assertEquals(s1.toString(), "chop garlic");
    }

    @Test
    public void isValidName() {
        String validStep = "Saute garlic";
        assertTrue(Name.isValidName(validStep));
    }

    @Test
    public void equals_test() {
        Step s1Copy = new Step(s1.value);

        assertNotEquals(s1, s1.value);
        assertEquals(s1, s1);
        assertEquals(s1, s1Copy);
        assertNotEquals(s1, s2);
    }

    @Test
    public void hashCode_test() {
        Step s1Copy = new Step(s1.value);

        assertEquals(s1.hashCode(), s1Copy.hashCode());
        assertNotEquals(s1.hashCode(), s2.hashCode());
    }
}
