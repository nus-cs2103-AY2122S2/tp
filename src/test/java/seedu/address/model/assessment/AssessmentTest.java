package seedu.address.model.assessment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssessments.CS2030_LAB1_WITH_ATTEMPT;
import static seedu.address.testutil.TypicalAssessments.CS2030_LAB2_WITH_SOME_ATTEMPT;
import static seedu.address.testutil.TypicalAssessments.CS2101_PARTICIPATION_WITH_ATTEMPT;
import static seedu.address.testutil.TypicalAssessments.CS2103T_NO_STUDENT_PARTICIPATION_NO_ATTEMPT;
import static seedu.address.testutil.TypicalAssessments.CS2103T_PARTICIPATION_NO_ATTEMPT;
import static seedu.address.testutil.TypicalAssessments.GRADE1;
import static seedu.address.testutil.TypicalModules.CS2101;
import static seedu.address.testutil.TypicalModules.CS2105;

import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AssessmentBuilder;
import seedu.address.testutil.TypicalAssessments;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalStudents;

public class AssessmentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Assessment assessment = new AssessmentBuilder(CS2101_PARTICIPATION_WITH_ATTEMPT).build();
        assertThrows(UnsupportedOperationException.class, () ->
                assessment.getAttempts().remove(TypicalStudents.BENSON));
    }

    @Test
    public void isSameAssessment() {
        // same object -> returns true
        assertTrue(CS2103T_NO_STUDENT_PARTICIPATION_NO_ATTEMPT
                .isSameAssessment(CS2103T_NO_STUDENT_PARTICIPATION_NO_ATTEMPT));

        // null -> returns false
        assertFalse(CS2103T_NO_STUDENT_PARTICIPATION_NO_ATTEMPT.isSameAssessment(null));

        // different Assessment Name and simpleName all other attributes same -> returns false
        Assessment editedAssessment = new AssessmentBuilder(CS2103T_NO_STUDENT_PARTICIPATION_NO_ATTEMPT)
                .withAssessmentName("Participation").withSimpleName("pa").build();
        assertFalse(CS2103T_NO_STUDENT_PARTICIPATION_NO_ATTEMPT.isSameAssessment(editedAssessment));

        // different Module -> returns false
        editedAssessment = new AssessmentBuilder(CS2103T_NO_STUDENT_PARTICIPATION_NO_ATTEMPT)
                .withTaModule(TypicalModules.getModule(CS2101)).build();
        assertFalse(CS2103T_NO_STUDENT_PARTICIPATION_NO_ATTEMPT.isSameAssessment(editedAssessment));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Assessment assessmentCopy = new AssessmentBuilder(CS2103T_PARTICIPATION_NO_ATTEMPT).build();
        assertTrue(CS2103T_PARTICIPATION_NO_ATTEMPT.equals(assessmentCopy));

        // same object -> returns true
        assertTrue(CS2030_LAB1_WITH_ATTEMPT.equals(CS2030_LAB1_WITH_ATTEMPT));

        // null -> returns false
        assertFalse(CS2030_LAB2_WITH_SOME_ATTEMPT.equals(null));

        // different type -> returns false
        assertFalse(CS2030_LAB2_WITH_SOME_ATTEMPT.equals(5));

        // different assessment -> returns false
        assertFalse(CS2030_LAB2_WITH_SOME_ATTEMPT.equals(CS2030_LAB1_WITH_ATTEMPT));

        // different name -> returns false
        Assessment editedAssessment = new AssessmentBuilder(CS2103T_NO_STUDENT_PARTICIPATION_NO_ATTEMPT)
                .withAssessmentName("Participation").build();
        assertFalse(CS2103T_NO_STUDENT_PARTICIPATION_NO_ATTEMPT.equals(editedAssessment));

        // name differs in case, all other attributes same -> returns false
        editedAssessment = new AssessmentBuilder(CS2103T_NO_STUDENT_PARTICIPATION_NO_ATTEMPT)
                .withAssessmentName(TypicalAssessments.PARTICIPATION.toLowerCase()).build();
        assertFalse(CS2103T_NO_STUDENT_PARTICIPATION_NO_ATTEMPT.equals(editedAssessment));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = TypicalAssessments.PARTICIPATION + " ";
        editedAssessment = new AssessmentBuilder(CS2103T_NO_STUDENT_PARTICIPATION_NO_ATTEMPT)
                .withAssessmentName(nameWithTrailingSpaces).build();
        assertFalse(CS2103T_NO_STUDENT_PARTICIPATION_NO_ATTEMPT.equals(editedAssessment));

        // different module -> returns false
        editedAssessment = new AssessmentBuilder(CS2103T_NO_STUDENT_PARTICIPATION_NO_ATTEMPT)
                .withTaModule(TypicalModules.getModule(CS2105)).build();
        assertFalse(CS2103T_NO_STUDENT_PARTICIPATION_NO_ATTEMPT.equals(editedAssessment));

        // different students -> returns false
        editedAssessment = new AssessmentBuilder(CS2101_PARTICIPATION_WITH_ATTEMPT)
                .withAttempts(Map.of(TypicalStudents.ALICE, GRADE1)).build();
        assertFalse(CS2101_PARTICIPATION_WITH_ATTEMPT.equals(editedAssessment));

    }
}
