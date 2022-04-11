package seedu.address.model.assessment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AssessmentNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssessmentName(null));
    }

    @Test
    public void constructor_invalidAssessmentName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new AssessmentName(invalidName));
    }

    @Test
    public void isValidAssessmentName() {
        // null assessment name
        assertThrows(NullPointerException.class, () -> AssessmentName.isValidAssessmentName(null));

        // invalid assessment names
        assertFalse(AssessmentName.isValidAssessmentName("")); // empty string
        assertFalse(AssessmentName.isValidAssessmentName(" ")); // spaces only
        assertFalse(AssessmentName.isValidAssessmentName("^")); // only non-alphanumeric characters
        assertFalse(AssessmentName.isValidAssessmentName("test*")); // contains non-alphanumeric characters

        // valid assessment names
        assertTrue(AssessmentName.isValidAssessmentName("quiz")); // alphabets only
        assertTrue(AssessmentName.isValidAssessmentName("12345")); // numbers only
        assertTrue(AssessmentName.isValidAssessmentName("quiz 2")); // alphanumeric characters
        assertTrue(AssessmentName.isValidAssessmentName("Quiz")); // with capital letters
        assertTrue(AssessmentName.isValidAssessmentName("Assignment 1 about asymptotic analysis")); // long names
    }
}
