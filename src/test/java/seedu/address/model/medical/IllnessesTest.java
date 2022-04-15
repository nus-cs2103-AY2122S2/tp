package seedu.address.model.medical;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IllnessesTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Illnesses(null));
    }

    @Test
    public void constructor_invalidIllnesses_throwsIllegalArgumentException() {
        String invalidIllnesses = "";
        assertThrows(IllegalArgumentException.class, () -> new Illnesses(invalidIllnesses));
    }

    @Test
    public void isValidIllnesses() {
        // null illnesses
        assertThrows(NullPointerException.class, () -> Illnesses.isValidIllnesses(null));

        // invalid illnesses
        assertFalse(Illnesses.isValidIllnesses("")); // empty string
        assertFalse(Illnesses.isValidIllnesses(" ")); // space only

        // valid illnesses
        assertTrue(Illnesses.isValidIllnesses("Mild fever"));
    }
}
