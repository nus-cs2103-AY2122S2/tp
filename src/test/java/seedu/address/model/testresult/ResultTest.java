package seedu.address.model.testresult;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ResultTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Result(null));
    }

    @Test
    public void constructor_invalidResult_throwsIllegalArgumentException() {
        String invalidResult = "";
        assertThrows(IllegalArgumentException.class, () -> new Result(invalidResult));
    }

    @Test
    public void isValidResult() {
        // null result
        assertThrows(NullPointerException.class, () -> Result.isValidResult(null));

        // invalid result
        assertFalse(Result.isValidResult("")); // empty string
        assertFalse(Result.isValidResult(" ")); // spaces only

        // valid result
        assertTrue(Result.isValidResult("Broken bone"));
        assertTrue(Result.isValidResult("-")); // one character
        assertTrue(Result.isValidResult(
            "PA and lateral views of chest reveals no evidence of active pleural abnormality.")); // long result
    }
}
