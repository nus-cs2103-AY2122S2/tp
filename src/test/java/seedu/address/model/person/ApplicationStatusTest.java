package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ApplicationStatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ApplicationStatus(null));
    }

    @Test
    public void isValidStatus() {
        assertThrows(NullPointerException.class, () -> ApplicationStatus.isValidStatus(null));

        //wrong format
        assertFalse(ApplicationStatus.isValidStatus(""));
        assertFalse(ApplicationStatus.isValidStatus(" "));
        assertFalse(ApplicationStatus.isValidStatus("accepts"));
        assertFalse(ApplicationStatus.isValidStatus("rejects"));
        assertFalse(ApplicationStatus.isValidStatus("denied"));

        //correct format
        assertTrue(ApplicationStatus.isValidStatus("accepted"));
        assertTrue(ApplicationStatus.isValidStatus("Accepted"));
        assertTrue(ApplicationStatus.isValidStatus("Rejected"));
        assertTrue(ApplicationStatus.isValidStatus("rejected"));
        assertTrue(ApplicationStatus.isValidStatus("Pending"));
        assertTrue(ApplicationStatus.isValidStatus("pending"));
    }

}
