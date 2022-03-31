package seedu.address.model.position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PositionNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PositionName(null));
    }

    @Test
    public void constructor_invalidPositioName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new PositionName(""));
        assertThrows(IllegalArgumentException.class, () -> new PositionName(" "));
    }

    @Test
    public void isValidPositionName() {
        // null name
        assertThrows(NullPointerException.class, () -> PositionName.isValidPositionName(null));

        // invalid name
        assertFalse(PositionName.isValidPositionName("")); // empty string
        assertFalse(PositionName.isValidPositionName(" ")); // only space
        assertFalse(PositionName.isValidPositionName(" software engineer")); // start with space
        assertFalse(PositionName.isValidPositionName("^#$#")); // only non-alphanumeric characters
        assertFalse(PositionName.isValidPositionName("*    ")); // special characters and spaces

        // valid name
        assertTrue(PositionName.isValidPositionName("software engineer")); // alphabets only
        assertTrue(PositionName.isValidPositionName("5000")); // numbers only
        assertTrue(PositionName.isValidPositionName("Quality Assurance Engineer")); // with capital letters
        assertTrue(PositionName.isValidPositionName("Software Engineer - 2IC")); // special chars with alphanumeric
        assertTrue(PositionName.isValidPositionName("!software eng")); // start with special character
    }
}
