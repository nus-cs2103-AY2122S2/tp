package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedStudentResult.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudentResults.E0123456;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assessment.FullMark;
import seedu.address.model.assessment.Score;
import seedu.address.model.assessment.StudentResult;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetId;

public class JsonAdaptedStudentResultTest {
    private static final String INVALID_NAME = "J@hn";
    private static final String INVALID_NUSNETID = "e012345c";
    private static final String INVALID_SCORE_NEGATIVE = "-1";
    private static final String INVALID_SCORE_OVERFLOW = "1001";

    private static final String VALID_NAME = E0123456.getStudentName().toString();
    private static final String VALID_NUSNETID = E0123456.getStudentId().toString();
    private static final String VALID_SCORE = E0123456.getScore().toString();

    private static final FullMark fullmark = new FullMark("1000");

    @Test
    public void toModelType_validStudentResultDetails_returnsStudentResult() throws Exception {
        JsonAdaptedStudentResult studentResult = new JsonAdaptedStudentResult(E0123456);
        StudentResult studentResultModel = studentResult.toModelType(fullmark);
        assertEquals(E0123456.getStudentName(), studentResultModel.getStudentName());
        assertEquals(E0123456.getStudentId(), studentResultModel.getStudentId());
        assertEquals(E0123456.getScore(), studentResultModel.getScore());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudentResult studentResult =
                new JsonAdaptedStudentResult(INVALID_NAME, VALID_NUSNETID, VALID_SCORE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> studentResult.toModelType(fullmark));
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudentResult studentResult =
                new JsonAdaptedStudentResult(null, VALID_NUSNETID, VALID_SCORE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> studentResult.toModelType(fullmark));
    }

    @Test
    public void toModelType_invalidNusNetId_throwsIllegalValueException() {
        JsonAdaptedStudentResult studentResult =
                new JsonAdaptedStudentResult(VALID_NAME, INVALID_NUSNETID, VALID_SCORE);
        String expectedMessage = NusNetId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> studentResult.toModelType(fullmark));
    }

    @Test
    public void toModelType_nullNusNetId_throwsIllegalValueException() {
        JsonAdaptedStudentResult studentResult = new JsonAdaptedStudentResult(VALID_NAME, null, VALID_SCORE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, NusNetId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> studentResult.toModelType(fullmark));
    }

    @Test
    public void toModelType_invalidScoreNegative_throwsIllegalValueException() {
        JsonAdaptedStudentResult studentResult =
                new JsonAdaptedStudentResult(VALID_NAME, VALID_NUSNETID, INVALID_SCORE_NEGATIVE);
        String expectedMessage = Score.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> studentResult.toModelType(fullmark));
    }

    @Test
    public void toModelType_invalidScoreOverflow_throwsIllegalValueException() {
        JsonAdaptedStudentResult studentResult =
                new JsonAdaptedStudentResult(VALID_NAME, VALID_NUSNETID, INVALID_SCORE_OVERFLOW);
        String expectedMessage = Score.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> studentResult.toModelType(fullmark));
    }

    @Test
    public void toModelType_nullScore_throwsIllegalValueException() {
        JsonAdaptedStudentResult studentResult = new JsonAdaptedStudentResult(VALID_NAME, VALID_NUSNETID, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Score.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> studentResult.toModelType(fullmark));
    }
}
