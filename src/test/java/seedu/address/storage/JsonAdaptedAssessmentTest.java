package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedAssessment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssessments.ASSIGNMENT_1;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.FullMark;
import seedu.address.model.assessment.Weightage;

public class JsonAdaptedAssessmentTest {
    private static final String INVALID_BLANK_ASSESSMENT_NAME = " ";
    private static final String INVALID_ASSESSMENT_NAME = "@##$%^Assessment";
    private static final String INVALID_WEIGHTAGE = "101";
    private static final String INVALID_NEGATIVE_FULLMARK = "-1";
    private static final String INVALID_POSITIVE_FULLMARK = "1001";

    private static final String VALID_ASSESSMENT_NAME = ASSIGNMENT_1.getAssessmentName().toString();
    private static final String VALID_WEIGHTAGE = ASSIGNMENT_1.getWeightage().toString();
    private static final String VALID_FULLMARK = ASSIGNMENT_1.getFullMark().toString();

    @Test
    public void toModelType_validAssessmentDetails_returnsAssessment() throws Exception {
        JsonAdaptedAssessment assessment = new JsonAdaptedAssessment(ASSIGNMENT_1);
        assertEquals(ASSIGNMENT_1, assessment.toModelType());
    }

    @Test
    public void toModelType_invalidFilledAssessmentName_throwsIllegalValueException() {
        JsonAdaptedAssessment assessment = new JsonAdaptedAssessment(INVALID_ASSESSMENT_NAME,
                VALID_WEIGHTAGE, VALID_FULLMARK);
        String expectedMessage = AssessmentName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, assessment::toModelType);
    }

    @Test
    public void toModelType_invalidBlankAssessmentName_throwsIllegalValueException() {
        JsonAdaptedAssessment assessment = new JsonAdaptedAssessment(INVALID_BLANK_ASSESSMENT_NAME,
                VALID_WEIGHTAGE, VALID_FULLMARK);
        String expectedMessage = AssessmentName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, assessment::toModelType);
    }

    @Test
    public void toModelType_nullAssessmentName_throwsIllegalValueException() {
        JsonAdaptedAssessment assessment = new JsonAdaptedAssessment(null,
                VALID_WEIGHTAGE, VALID_FULLMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, AssessmentName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, assessment::toModelType);
    }

    @Test
    public void toModelType_invalidWeightage_throwsIllegalValueException() {
        JsonAdaptedAssessment assessment = new JsonAdaptedAssessment(VALID_ASSESSMENT_NAME,
                INVALID_WEIGHTAGE, VALID_FULLMARK);
        String expectedMessage = Weightage.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, assessment::toModelType);
    }

    @Test
    public void toModelType_nullWeightage_throwsIllegalValueException() {
        JsonAdaptedAssessment assessment = new JsonAdaptedAssessment(VALID_ASSESSMENT_NAME,
                null, VALID_FULLMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Weightage.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, assessment::toModelType);
    }

    @Test
    public void toModelType_invalidNegativeFullMark_throwsIllegalValueException() {
        JsonAdaptedAssessment assessment = new JsonAdaptedAssessment(VALID_ASSESSMENT_NAME,
                VALID_WEIGHTAGE, INVALID_NEGATIVE_FULLMARK);
        String expectedMessage = FullMark.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, assessment::toModelType);
    }

    @Test
    public void toModelType_invalidPositiveFullMark_throwsIllegalValueException() {
        JsonAdaptedAssessment assessment = new JsonAdaptedAssessment(VALID_ASSESSMENT_NAME,
                VALID_WEIGHTAGE, INVALID_POSITIVE_FULLMARK);
        String expectedMessage = FullMark.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, assessment::toModelType);
    }

    @Test
    public void toModelType_nullFullMark_throwsIllegalValueException() {
        JsonAdaptedAssessment assessment = new JsonAdaptedAssessment(VALID_ASSESSMENT_NAME,
                VALID_WEIGHTAGE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, FullMark.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, assessment::toModelType);
    }
}
