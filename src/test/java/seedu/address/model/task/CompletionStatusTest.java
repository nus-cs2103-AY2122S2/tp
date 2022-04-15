package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.model.task.TypicalCompletionStatus.NULL_COMPLETION_STATUS;
import static seedu.address.model.task.TypicalCompletionStatus.getInvalidCompletionStatus;
import static seedu.address.model.task.TypicalCompletionStatus.getValidCompletionStatus;
import static seedu.address.testutil.Assert.assertAllFalse;
import static seedu.address.testutil.Assert.assertAllThrows;
import static seedu.address.testutil.Assert.assertAllTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompletionStatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {

        assertThrows(NullPointerException.class, () -> new CompletionStatus(NULL_COMPLETION_STATUS));
    }

    @Test
    public void constructor_invalidCompletionStatus_throwsIllegalArgumentException() {

        String[] invalidCompletionStatus = getInvalidCompletionStatus();
        assertAllThrows(IllegalArgumentException.class, CompletionStatus::new, invalidCompletionStatus);
    }

    @Test
    public void isValidCompletionStatus() {

        // null completion status
        assertFalse(CompletionStatus.isValidCompletionStatus(NULL_COMPLETION_STATUS));

        // invalid completion status
        assertAllFalse(CompletionStatus::isValidCompletionStatus, getInvalidCompletionStatus());

        // valid completion status
        assertAllTrue(CompletionStatus::isValidCompletionStatus, getValidCompletionStatus());
    }
}
