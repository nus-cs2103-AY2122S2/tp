package seedu.address.model.assessment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_ASSESSMENT_NAME_OP1;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_FULL_MARK_OP1;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_FULL_MARK_OP2;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_SCORE_OP1;
import static seedu.address.logic.commands.StudentTestUtil.VALID_NAME_AARON;
import static seedu.address.logic.commands.StudentTestUtil.VALID_NAME_BILL;
import static seedu.address.logic.commands.StudentTestUtil.VALID_STUDENT_ID_AARON;
import static seedu.address.logic.commands.StudentTestUtil.VALID_STUDENT_ID_BILL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudentResults.AARON_RESULT_FOR_OP1;
import static seedu.address.testutil.TypicalStudentResults.BILL_RESULT_FOR_OP2;

import org.junit.jupiter.api.Test;

import seedu.address.model.assessment.exceptions.DuplicateStudentResultException;
import seedu.address.model.assessment.exceptions.StudentResultNotFoundException;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetId;
import seedu.address.testutil.StudentResultBuilder;

public class AssessmentResultsTest {
    private final AssessmentResults assessmentResults = new AssessmentResults(
            new AssessmentName(VALID_ASSESSMENT_NAME_OP1));

    @Test
    public void contains_nullStudentResult_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> assessmentResults.contains(null));
    }

    @Test
    public void contains_studentResultInList_returnsTrue() {
        assessmentResults.add(AARON_RESULT_FOR_OP1);
        assertTrue(assessmentResults.contains(AARON_RESULT_FOR_OP1));
    }

    @Test
    public void contains_studentResultNotInList_returnsFalse() {
        assertFalse(assessmentResults.contains(AARON_RESULT_FOR_OP1));
    }

    @Test
    public void contains_studentResultWithSameStudentId_returnsTrue() {
        StudentResult editedBillResultForOp2 = new StudentResultBuilder(BILL_RESULT_FOR_OP2)
                .withScore(VALID_SCORE_OP1, VALID_FULL_MARK_OP2).build();
        assessmentResults.add(editedBillResultForOp2);
        assertTrue(assessmentResults.contains(BILL_RESULT_FOR_OP2));
    }

    @Test
    public void add_nullStudentResult_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> assessmentResults.add(null));
    }

    @Test
    public void add_duplicateStudentResult_throwsDuplicateStudentResultException() {
        assessmentResults.add(AARON_RESULT_FOR_OP1);
        assertThrows(DuplicateStudentResultException.class, ()
            -> assessmentResults.add(AARON_RESULT_FOR_OP1));
    }

    @Test
    public void removeByStudentId_nullStudentResult_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> assessmentResults.removeByStudentId(null));
    }

    @Test
    public void removeByStudentId_studentResultInList_removesStudentResult() {
        assessmentResults.add(AARON_RESULT_FOR_OP1);
        assessmentResults.removeByStudentId(new NusNetId(VALID_STUDENT_ID_AARON));
        AssessmentResults aR = new AssessmentResults(new AssessmentName(VALID_ASSESSMENT_NAME_OP1));
        assertEquals(assessmentResults, aR);
    }

    @Test
    public void set_nullParameters_throwsNullPointerException() {
        Score score = new Score(VALID_SCORE_OP1, new FullMark(VALID_FULL_MARK_OP1));
        assertThrows(NullPointerException.class, ()
            -> assessmentResults.set(new Name(VALID_NAME_AARON), null, score)); // null student id

        assertThrows(NullPointerException.class, ()
            -> assessmentResults.set(new Name(VALID_NAME_AARON),
                new NusNetId(VALID_STUDENT_ID_AARON), null)); // null score
    }

    @Test
    public void set_studentIdNotInList_throwsStudentResultNotFoundException() {
        assertThrows(StudentResultNotFoundException.class, ()
            -> assessmentResults.set(new Name(VALID_NAME_AARON), new NusNetId(VALID_STUDENT_ID_AARON),
                new Score(VALID_SCORE_OP1, new FullMark(VALID_FULL_MARK_OP1))));
    }

    @Test
    public void set_editedResultIsSameResult_setsStudentResult() {
        assessmentResults.add(AARON_RESULT_FOR_OP1);
        assessmentResults.set(new Name(VALID_NAME_AARON), new NusNetId(VALID_STUDENT_ID_AARON),
                new Score(VALID_SCORE_OP1, new FullMark(VALID_FULL_MARK_OP1)));
        AssessmentResults aR = new AssessmentResults(new AssessmentName(VALID_ASSESSMENT_NAME_OP1));
        aR.add(AARON_RESULT_FOR_OP1);
        assertEquals(assessmentResults, aR);
    }

    @Test
    public void set_editedResultHasSameStudentId_setsStudentResult() {
        assessmentResults.add(BILL_RESULT_FOR_OP2);
        StudentResult editedBillResultForOp2 = new StudentResultBuilder(BILL_RESULT_FOR_OP2)
                .withScore(VALID_SCORE_OP1, VALID_FULL_MARK_OP2).build();
        assessmentResults.set(new Name(VALID_NAME_BILL), new NusNetId(VALID_STUDENT_ID_BILL),
                new Score(VALID_SCORE_OP1, new FullMark(VALID_FULL_MARK_OP2)));
        AssessmentResults expectedAssessmentResults = new AssessmentResults(
                new AssessmentName(VALID_ASSESSMENT_NAME_OP1));
        expectedAssessmentResults.add(editedBillResultForOp2);
        assertEquals(assessmentResults, expectedAssessmentResults);
    }

}
