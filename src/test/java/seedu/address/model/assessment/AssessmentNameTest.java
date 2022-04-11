package seedu.address.model.assessment;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class AssessmentNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssessmentName(null));
    }

    @Test
    public void constructor_invalidAssessmentName_throwsIllegalArgumentException() {
        String invalidAssessmentName = "";
        assertThrows(IllegalArgumentException.class, () -> new AssessmentName(invalidAssessmentName));
    }

    @Test
    public void isValidAssessmentName() throws IOException {
        assertFalse(AssessmentName.isValidAssessmentName("")); //blank
        assertFalse(AssessmentName.isValidAssessmentName(" ")); //one space
        assertFalse(AssessmentName.isValidAssessmentName("A@B")); //No special characters

        //Valid assessmentNames
        assertTrue(AssessmentName.isValidAssessmentName("Class Participation"));
        assertTrue(AssessmentName.isValidAssessmentName("Oral Presentation 1"));
        assertTrue(AssessmentName.isValidAssessmentName("lab"));
        assertTrue(AssessmentName.isValidAssessmentName("lab1"));
        assertTrue(AssessmentName.isValidAssessmentName("Lab Report"));
        assertTrue(AssessmentName.isValidAssessmentName("1234567890"));
    }
}
