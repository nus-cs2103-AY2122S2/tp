package seedu.address.model.assessment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_ASSESSMENT_NAME_OP1;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_ASSESSMENT_NAME_OP2;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_FULL_MARK_OP2;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_WEIGHTAGE_OP2;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssessments.OP1;

import org.junit.jupiter.api.Test;

import seedu.address.model.assessment.exceptions.AssessmentNotFoundException;
import seedu.address.model.assessment.exceptions.DuplicateAssessmentException;
import seedu.address.testutil.AssessmentBuilder;

public class UniqueAssessmentListTest {
    private final UniqueAssessmentList assessmentList = new UniqueAssessmentList();

    @Test
    public void contains_nullAssessment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> assessmentList.contains(null));
    }

    @Test
    public void contains_assessmentNotInList_returnsFalse() {
        assertFalse(assessmentList.contains(OP1));
    }

    @Test
    public void contains_assessmentInList_returnsTrue() {
        assessmentList.add(OP1);
        assertTrue(assessmentList.contains(OP1));
    }

    @Test
    public void contains_assessmentWithSameIdentityFields_returnsTrue() {
        Assessment editedOp1 = new AssessmentBuilder(OP1).withFullMark(VALID_FULL_MARK_OP2)
                .withWeightage(VALID_WEIGHTAGE_OP2).build();
        assessmentList.add(editedOp1);
        assertTrue(assessmentList.contains(OP1));
    }

    @Test
    public void containsByName_nullAssessmentName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> assessmentList.containsByName(null));
    }

    @Test
    public void containsByName_assessmentNameOfAssessmentInList_returnsTrue() {
        assessmentList.add(OP1);
        AssessmentName op1Name = new AssessmentName(VALID_ASSESSMENT_NAME_OP1);
        assertTrue(assessmentList.containsByName(op1Name));
    }

    @Test
    public void containsByName_assessmentNameOfAssessmentNotInList_returnsFalse() {
        assessmentList.add(OP1);
        AssessmentName op2Name = new AssessmentName(VALID_ASSESSMENT_NAME_OP2);
        assertFalse(assessmentList.containsByName(op2Name));
    }

    @Test
    public void add_nullAssessment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> assessmentList.add(null));
    }

    @Test
    public void add_duplicateAssessment_throwsDuplicateAssessmentException() {
        assessmentList.add(OP1);
        assertThrows(DuplicateAssessmentException.class, () -> assessmentList.add(OP1));
    }

    @Test
    public void getByName_nullAssessmentName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> assessmentList.getByName(null));
    }

    @Test
    public void getByName_assessmentNameOfAssessmentNotInList_throwsAssessmentNotFoundException() {
        AssessmentName op1Name = new AssessmentName(VALID_ASSESSMENT_NAME_OP1);
        assertThrows(AssessmentNotFoundException.class, () -> assessmentList.getByName(op1Name));
    }

    @Test
    public void getByName_assessmentNameOfAssessmentInList_returnsCorrectAssessment() {
        AssessmentName op1Name = new AssessmentName(VALID_ASSESSMENT_NAME_OP1);
        assessmentList.add(OP1);
        Assessment expectedOp1 = assessmentList.getByName(op1Name);
        assertTrue(expectedOp1.isSameAssessment(OP1));
    }

    @Test
    public void remove_nullAssessment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> assessmentList.remove(null));
    }

    @Test
    public void remove_assessmentNotInList_throwsAssessmentNotFoundException() {
        assertThrows(AssessmentNotFoundException.class, () -> assessmentList.remove(OP1));
    }

    @Test
    public void remove_assessmentInList_removesAssessment() {
        assessmentList.add(OP1);
        assessmentList.remove(OP1);
        UniqueAssessmentList uniqueAssessmentList = new UniqueAssessmentList();
        assertEquals(assessmentList, uniqueAssessmentList);
    }

    @Test
    public void removeByName_nullAssessmentName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> assessmentList.removeByName(null));
    }

    @Test
    public void removeByName_assessmentNameOfAssessmentNotInList_throwsAssessmentNotFoundException() {
        AssessmentName op1Name = new AssessmentName(VALID_ASSESSMENT_NAME_OP1);
        assertThrows(AssessmentNotFoundException.class, () -> assessmentList.removeByName(op1Name));
    }

    @Test
    public void removeByName_assessmentNameOfAssessmentInList_removesAssessment() {
        assessmentList.add(OP1);
        AssessmentName op1Name = new AssessmentName(VALID_ASSESSMENT_NAME_OP1);
        assessmentList.removeByName(op1Name);
        UniqueAssessmentList uniqueAssessmentList = new UniqueAssessmentList();
        assertEquals(assessmentList, uniqueAssessmentList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> assessmentList.asUnmodifiableObservableList().remove(0));
    }
}
