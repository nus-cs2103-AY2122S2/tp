package seedu.address.model.procedure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class CompletionTest {

    @Test
    public void constructor_nullHasCompleted_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Completion(null));
    }

    @Test
    public void constructor_invalidHasCompleted_throwsIllegalArgumentException() {
        String hasCompleted = "";
        assertThrows(IllegalArgumentException.class, () -> new Completion(hasCompleted));
    }

    @Test
    public void isValidHasCompleted() {
        // null Completion
        assertThrows(NullPointerException.class, () -> Completion.isValidHasCompleted(null));

        // invalid Completion
        assertFalse(Completion.isValidHasCompleted("")); // empty string
        assertFalse(Completion.isValidHasCompleted("   ")); // spaces only
        assertFalse(Completion.isValidHasCompleted("12.111")); // with double
        assertFalse(Completion.isValidHasCompleted("-12")); // with negative integer
        assertFalse(Completion.isValidHasCompleted("^$@#")); // only non-alphanumeric characters
        assertFalse(Completion.isValidHasCompleted("true#@#")); // contains non-alphanumeric characters
        assertFalse(Completion.isValidHasCompleted("   true")); // with additional prefix spaces
        assertFalse(Completion.isValidHasCompleted("true  ")); // with additional postfix spaces
        assertFalse(Completion.isValidHasCompleted("tru3e")); // alphanumeric characters
        assertFalse(Completion.isValidHasCompleted("Truee")); // with additional postfix characters
        assertFalse(Completion.isValidHasCompleted("FFalse")); // with additional prefix characters
        assertFalse(Completion.isValidHasCompleted("True")); // use case with capitalised initial
        assertFalse(Completion.isValidHasCompleted("trUe")); // use case with random capitalisation
        assertFalse(Completion.isValidHasCompleted("False")); // use case with capitalised initial
        assertFalse(Completion.isValidHasCompleted("faLsE")); // use case with random capitalisation

        // valid Completion
        assertTrue(Completion.isValidHasCompleted("true")); // normal use case
        assertTrue(Completion.isValidHasCompleted("false")); // normal use case

    }
}
