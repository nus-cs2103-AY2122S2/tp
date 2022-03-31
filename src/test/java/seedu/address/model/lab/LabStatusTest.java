package seedu.address.model.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.lab.LabStatus.SUBMITTED_DESCRIPTION;
import static seedu.address.model.lab.LabStatus.UNSUBMITTED_DESCRIPTION;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.lab.exceptions.IllegalLabStateException;

class LabStatusTest {

    @Test
    public void toLabStatus_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> LabStatus.toLabStatus(null));
    }

    @Test
    public void toLabStatus_validInput_success() {
        assertEquals(LabStatus.UNSUBMITTED, LabStatus.toLabStatus("UNSUBMITTED"));
        assertEquals(LabStatus.SUBMITTED, LabStatus.toLabStatus("SUBMITTED"));
        assertEquals(LabStatus.GRADED, LabStatus.toLabStatus("GRADED"));
    }

    @Test
    public void toLabStatus_invalidInput_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> LabStatus.toLabStatus("test"));
    }

    @Test
    public void describe_validInput_success() {
        assertEquals(LabStatus.UNSUBMITTED.describe(), UNSUBMITTED_DESCRIPTION);
        assertEquals(LabStatus.SUBMITTED.describe(), SUBMITTED_DESCRIPTION);
        assertThrows(IllegalLabStateException.class, LabStatus.GRADED::describe);
    }

}
