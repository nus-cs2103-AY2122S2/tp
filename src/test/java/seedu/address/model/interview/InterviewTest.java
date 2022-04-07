package seedu.address.model.interview;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalApplicants.ALICE;
import static seedu.address.testutil.TypicalInterviews.QA_ENGINEER_INTERVIEW;
import static seedu.address.testutil.TypicalPositions.SR_FE_SOFTWARE_ENGINEER;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InterviewBuilder;

public class InterviewTest {

    @Test
    public void equals() {
        // same values -> returns true
        Interview qaEngineerCopy = new InterviewBuilder(QA_ENGINEER_INTERVIEW).build();
        assertTrue(QA_ENGINEER_INTERVIEW.equals(qaEngineerCopy));

        // same object -> returns true
        assertTrue(QA_ENGINEER_INTERVIEW.equals(QA_ENGINEER_INTERVIEW));

        // null -> returns false
        assertFalse(QA_ENGINEER_INTERVIEW.equals(null));

        // different type -> returns false
        assertFalse(QA_ENGINEER_INTERVIEW.equals(ALICE));

        // different applicant -> returns false
        Interview editedQaEngineer = new InterviewBuilder(QA_ENGINEER_INTERVIEW).withApplicant(ALICE).build();
        assertFalse(QA_ENGINEER_INTERVIEW.equals(editedQaEngineer));

        // different date -> returns false
        editedQaEngineer = new InterviewBuilder(QA_ENGINEER_INTERVIEW)
                .withDate(LocalDateTime.of(2000, 01, 01, 12, 0)).build();
        assertFalse(QA_ENGINEER_INTERVIEW.equals(editedQaEngineer));

        // different position -> returns false
        editedQaEngineer = new InterviewBuilder(QA_ENGINEER_INTERVIEW).withPosition(SR_FE_SOFTWARE_ENGINEER)
                .build();
        assertFalse(QA_ENGINEER_INTERVIEW.equals(editedQaEngineer));

        // different status -> return false
        Interview qaEngineerCopyPass = new InterviewBuilder(QA_ENGINEER_INTERVIEW).build();
        qaEngineerCopyPass.markAsPassed();
        assertFalse(qaEngineerCopyPass.equals(editedQaEngineer));
    }
}
