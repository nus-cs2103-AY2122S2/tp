package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class ServingSizeTest {
    private final ServingSize ss1 = new ServingSize(1);
    private final ServingSize ss2 = new ServingSize(2);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ServingSize(null));
    }

    @Test
    public void constructor_invalidServingSize_throwsIllegalArgumentException() {
        int invalidServingSize = -3;
        assertThrows(IllegalArgumentException.class, () -> new ServingSize(invalidServingSize));
    }

    @Test
    public void toString_test() {
        assertEquals(ss1.toString(), String.valueOf(1));
    }

    @Test
    public void isValidServingSize() {
        assertTrue(ServingSize.isValidServingSize(3));
    }

    @Test
    public void equals_test() {
        ServingSize ss1Copy = new ServingSize(ss1.value);

        assertNotEquals(ss1, ss1.value);
        assertEquals(ss1, ss1);
        assertEquals(ss1, ss1Copy);
        assertNotEquals(ss1, ss2);
    }

    @Test
    public void hashCode_test() {
        ServingSize ss1Copy = new ServingSize(ss1.value);

        assertEquals(ss1.hashCode(), ss1Copy.hashCode());
        assertNotEquals(ss1.hashCode(), ss2.hashCode());
    }
}
