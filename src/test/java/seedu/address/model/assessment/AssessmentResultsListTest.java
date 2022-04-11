package seedu.address.model.assessment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_ASSESSMENT_NAME_OP1;
import static seedu.address.logic.commands.StudentTestUtil.VALID_STUDENT_ID_AARON;
import static seedu.address.logic.commands.TutorialTestUtil.VALID_TUTORIAL_NAME_TG1;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudentResults.AARON_RESULT_FOR_OP1;

import org.junit.jupiter.api.Test;

import seedu.address.model.assessment.exceptions.AssessmentNotFoundException;
import seedu.address.model.assessment.exceptions.DuplicateAssessmentException;
import seedu.address.model.person.NusNetId;
import seedu.address.model.tutorial.TutorialName;

public class AssessmentResultsListTest {
    private final AssessmentResults op1AssessmentResults = new AssessmentResults(
            new AssessmentName(VALID_ASSESSMENT_NAME_OP1));
    private final AssessmentResultsList assessmentResultsList = new AssessmentResultsList(
            new TutorialName(VALID_TUTORIAL_NAME_TG1));

    @Test
    public void contains_nullAssessmentResults_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> assessmentResultsList.contains(null));
    }

    @Test
    public void contains_assessmentResultsNotInList_returnsFalse() {
        assertFalse(assessmentResultsList.contains(op1AssessmentResults));
    }

    @Test
    public void contains_assessmentResultsInList_returnsTrue() {
        assessmentResultsList.add(op1AssessmentResults);
        assertTrue(assessmentResultsList.contains(op1AssessmentResults));
    }

    @Test
    public void hasAssessmentResultsByName_assessmentNameInList_returnsTrue() {
        assessmentResultsList.add(op1AssessmentResults);
        assertTrue(assessmentResultsList.hasAssessmentResultsByName(
                new AssessmentName(VALID_ASSESSMENT_NAME_OP1)));
    }

    @Test
    public void hasStudentResult_assessmentNameNotInList_throwsAssessmentNotFoundException() {
        assertThrows(AssessmentNotFoundException.class, ()
            -> assessmentResultsList.hasStudentResult(
                        new AssessmentName(VALID_ASSESSMENT_NAME_OP1), new NusNetId(VALID_STUDENT_ID_AARON)));
    }

    @Test
    public void hasStudentResult_studentIdNotInAssessmentResult_returnsFalse() {
        assessmentResultsList.add(op1AssessmentResults);
        assertFalse(assessmentResultsList.hasStudentResult(
                new AssessmentName(VALID_ASSESSMENT_NAME_OP1), new NusNetId(VALID_STUDENT_ID_AARON)));
    }

    @Test
    public void hasStudentResult_studentIdInAssessmentResult_returnsTrue() {
        op1AssessmentResults.add(AARON_RESULT_FOR_OP1);
        assessmentResultsList.add(op1AssessmentResults);
        assertTrue(assessmentResultsList.hasStudentResult(
                new AssessmentName(VALID_ASSESSMENT_NAME_OP1), new NusNetId(VALID_STUDENT_ID_AARON)));
    }

    @Test
    public void addStudentResult_assessmentNameNotInList_throwsAssessmentNotFoundException() {
        assertThrows(AssessmentNotFoundException.class, ()
            -> assessmentResultsList.addStudentResult(
                new AssessmentName(VALID_ASSESSMENT_NAME_OP1), AARON_RESULT_FOR_OP1));
    }

    @Test
    public void add_duplicateAssessmentResult_throwsDuplicateAssessmentException() {
        assessmentResultsList.add(op1AssessmentResults);
        assertThrows(DuplicateAssessmentException.class, ()
            -> assessmentResultsList.add(op1AssessmentResults));
    }
}
