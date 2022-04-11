package seedu.trackermon.commons.exceptions;

import static seedu.trackermon.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Contains integration tests for {@code IllegalValueException}.
 */
public class IllegalValueExceptionTest {
    @Test
    public void constructors() {
        assertThrows(IllegalValueException.class, () -> {
            throw new IllegalValueException("Illegal value");
        });
        assertThrows(IllegalValueException.class, () -> {
            throw new IllegalValueException("Illegal Argument", new Exception("Test Exception"));
        });
    }
}
