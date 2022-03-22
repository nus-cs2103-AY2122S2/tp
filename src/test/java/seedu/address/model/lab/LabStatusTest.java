package seedu.address.model.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

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

}
