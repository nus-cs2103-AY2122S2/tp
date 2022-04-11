package seedu.address.model.assessment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_FULL_MARK_OP2;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_SCORE_OP1;
import static seedu.address.logic.commands.StudentTestUtil.VALID_STUDENT_ID_AARON;
import static seedu.address.testutil.TypicalStudentResults.AARON_RESULT_FOR_OP1;
import static seedu.address.testutil.TypicalStudentResults.BILL_RESULT_FOR_OP2;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentResultBuilder;

public class StudentResultTest {
    @Test
    public void isSameStudentResult() {
        // same object -> returns true
        assertTrue(AARON_RESULT_FOR_OP1.isSameStudentResult(AARON_RESULT_FOR_OP1));

        // null -> returns false
        assertFalse(AARON_RESULT_FOR_OP1.isSameStudentResult(null));

        // same studentId, different score -> returns true
        StudentResult editedBillResultForOp2 = new StudentResultBuilder(BILL_RESULT_FOR_OP2)
                .withScore(VALID_SCORE_OP1, VALID_FULL_MARK_OP2).build();
        assertTrue(editedBillResultForOp2.isSameStudentResult(BILL_RESULT_FOR_OP2));

        // different student id, same score -> returns false
        editedBillResultForOp2 = new StudentResultBuilder(BILL_RESULT_FOR_OP2)
                .withNusNetId(VALID_STUDENT_ID_AARON).build();
        assertFalse(editedBillResultForOp2.isSameStudentResult(BILL_RESULT_FOR_OP2));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(AARON_RESULT_FOR_OP1.equals(AARON_RESULT_FOR_OP1));

        // same values -> returns true
        StudentResult aaronResultForOp1Copy = new StudentResultBuilder(AARON_RESULT_FOR_OP1).build();
        assertTrue(aaronResultForOp1Copy.equals(AARON_RESULT_FOR_OP1));

        // null -> returns false
        assertFalse(AARON_RESULT_FOR_OP1.equals(null));

        // same student id, different score -> returns false
        StudentResult editedBillResultForOp2 = new StudentResultBuilder(BILL_RESULT_FOR_OP2)
                .withScore(VALID_SCORE_OP1, VALID_FULL_MARK_OP2).build();
        assertFalse(editedBillResultForOp2.equals(BILL_RESULT_FOR_OP2));

        // different student id, same score -> returns false
        editedBillResultForOp2 = new StudentResultBuilder(BILL_RESULT_FOR_OP2)
                .withNusNetId(VALID_STUDENT_ID_AARON).build();
        assertFalse(editedBillResultForOp2.equals(BILL_RESULT_FOR_OP2));
    }
}
