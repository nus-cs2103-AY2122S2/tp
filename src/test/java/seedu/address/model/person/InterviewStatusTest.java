package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InterviewStatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InterviewStatus(null));
    }

    @Test
    public void isValidStatus() {
        assertThrows(NullPointerException.class, () -> InterviewStatus.isValidStatus(null));

        assertFalse(InterviewStatus.isValidStatus(""));
        assertFalse(InterviewStatus.isValidStatus(" "));
        assertFalse(InterviewStatus.isValidStatus("interviews"));
        assertFalse(InterviewStatus.isValidStatus("interviewe"));
        assertFalse(InterviewStatus.isValidStatus("not interview"));
        assertFalse(InterviewStatus.isValidStatus("pend"));


        assertTrue(InterviewStatus.isValidStatus("interviewing"));
        assertTrue(InterviewStatus.isValidStatus("Interviewing"));
        assertTrue(InterviewStatus.isValidStatus("not interviewed"));
        assertTrue(InterviewStatus.isValidStatus("Not Interviewed"));
        assertTrue(InterviewStatus.isValidStatus("Not interviewed"));
        assertTrue(InterviewStatus.isValidStatus("not Interviewed"));
        assertTrue(InterviewStatus.isValidStatus("notinterviewed"));
        assertTrue(InterviewStatus.isValidStatus("NotInterviewed"));
        assertTrue(InterviewStatus.isValidStatus("Notinterviewed"));
        assertTrue(InterviewStatus.isValidStatus("notInterviewed"));
        assertTrue(InterviewStatus.isValidStatus("pending"));
        assertTrue(InterviewStatus.isValidStatus("Pending"));
    }
}
