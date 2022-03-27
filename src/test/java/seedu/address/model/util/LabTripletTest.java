package seedu.address.model.util;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.lab.Lab;
import seedu.address.model.student.exceptions.InvalidLabStatusException;

class LabTripletTest {

    @Test
    public void constructor_nullArguments_throwsNullPointerException() {
        // null lab number
        assertThrows(NullPointerException.class, () -> new LabTriplet(null, "SUBMITTED", "Unknown"));
        // null lab status
        assertThrows(NullPointerException.class, () -> new LabTriplet("1", null, "Unknown"));
        // null lab mark
        assertThrows(NullPointerException.class, () -> new LabTriplet("1", "SUBMITTED", null));
    }

    @Test
    public void constructor_validArguments_success() {
        assertDoesNotThrow(() -> new LabTriplet("1", "SUBMITTED", "Unknown"));
    }

    @Test
    public void getLab_validArguments_success() {
        LabTriplet lt = new LabTriplet("1", "SUBMITTED", "Unknown");
        assertEquals(new Lab("1").of("SUBMITTED") , lt.getLab());
    }

    @Test
    public void getLab_invalidLabNumber_throwsIllegalArgumentException() {
        LabTriplet lt = new LabTriplet("a", "SUBMITTED", "Unknown");
        assertThrows(IllegalArgumentException.class, () -> lt.getLab());
    }

    @Test
    public void getLab_invalidLabStatus_throwsIllegalArgumentException() {
        LabTriplet lt = new LabTriplet("1", "invalid", "Unknown");
        assertThrows(IllegalArgumentException.class, () -> lt.getLab());
    }

    @Test
    public void getLab_gradedLabStatusInvalidLabMark_throwsInvalidLabStatusException() {
        LabTriplet lt = new LabTriplet("1", "GRADED", "Unknown");
        assertThrows(InvalidLabStatusException.class, () -> lt.getLab());
    }
}
