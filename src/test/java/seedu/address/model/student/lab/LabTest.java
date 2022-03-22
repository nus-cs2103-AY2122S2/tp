package seedu.address.model.student.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.student.exceptions.DuplicateLabException;

public class LabTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Lab(null));
    }

    @Test
    public void constructor_nonNumericCharacters_throwsIllegalArgumentException() {
        String invalidLabNumber = "a";
        assertThrows(IllegalArgumentException.class, () -> new Lab(invalidLabNumber));
    }

    @Test
    public void constructor_startsWithZero_throwsIllegalArgumentException() {
        String invalidLabNumber = "012";
        assertThrows(IllegalArgumentException.class, () -> new Lab(invalidLabNumber));
    }

    @Test
    public void isValidLab() {
        // null lab
        assertThrows(NullPointerException.class, () -> Lab.isValidLab(null));

        // blank lab
        assertFalse(Lab.isValidLab("")); // empty string
        assertFalse(Lab.isValidLab(" ")); // spaces only

        // invalid lab
        assertFalse(Lab.isValidLab("1a")); // invalid lab number
        assertFalse(Lab.isValidLab("01")); // invalid lab number

        // valid lab
        assertTrue(Lab.isValidLab("1")); // valid integer
        assertTrue(Lab.isValidLab("2")); // valid integer
        assertTrue(Lab.isValidLab("123456789")); // valid integer
    }

    @Test
    public void editLabStatus_sameStatusUnsubmitted_throwDuplicateLabException() {
        Lab stub = new Lab("1");
        assertThrows(DuplicateLabException.class, () -> stub.editLabStatus(LabStatus.UNSUBMITTED));
    }

    @Test
    public void editLabStatus_sameStatusSubmitted_throwDuplicateLabException() {
        Lab stub = new Lab("1");
        stub = stub.editLabStatus(LabStatus.SUBMITTED);
        Lab finalStub = stub;
        assertThrows(DuplicateLabException.class, () -> finalStub.editLabStatus(LabStatus.SUBMITTED));
    }

    @Test
    public void editLabStatus_sameStatusGraded_throwDuplicateLabException() {
        Lab stub = new Lab("1");
        stub = stub.editLabStatus(LabStatus.GRADED);
        Lab finalStub = stub;
        assertThrows(DuplicateLabException.class, () -> finalStub.editLabStatus(LabStatus.GRADED));
    }

    @Test
    public void editLabStatus_changeStatusToSubmitted_success() {
        Lab stub = new Lab("1");
        stub = stub.editLabStatus(LabStatus.SUBMITTED);

        assertEquals(new Lab("1").of(LabStatus.SUBMITTED.name()), stub);
        assertNotEquals(new Lab("1"), stub);
    }

    @Test
    public void editLabStatus_changeStatusToGraded_success() {
        Lab stub = new Lab("1");
        stub = stub.editLabStatus(LabStatus.GRADED);

        assertEquals(new Lab("1").of(LabStatus.GRADED.name()), stub);
        assertNotEquals(new Lab("1"), stub);
    }

    @Test
    public void isSameLab_sameLabNumberAndStatus_success() {
        Lab stub = new Lab("1");
        Lab stub2 = new Lab("1");
        assertTrue(stub.isSameLab(stub2));
    }

    @Test
    public void isSameLab_sameLabNumberDifferentStatus_success() {
        Lab stub = new Lab("1");
        Lab stub2 = new Lab("1");
        stub2.editLabStatus(LabStatus.GRADED);
        assertTrue(stub.isSameLab(stub2));
    }

    @Test
    public void isSameLab_differentLabNumber_failure() {
        Lab stub = new Lab("1");
        Lab stub2 = new Lab("2");
        assertFalse(stub.isSameLab(stub2));
    }

    @Test
    public void equals_success() {
        Lab lab1 = (new Lab("1")).of("SUBMITTED");
        Lab lab1copy = (new Lab("1")).of("SUBMITTED");
        assertTrue(lab1.equals(lab1copy));
    }

    @Test
    public void equals_differentLabNumber_failure() {
        Lab lab1 = (new Lab("1")).of("SUBMITTED");
        Lab lab2 = (new Lab("2")).of("SUBMITTED");
        assertFalse(lab1.equals(lab2));
    }

    @Test
    public void equals_differentLabStatus_failure() {
        Lab lab1 = (new Lab("1")).of("SUBMITTED");
        Lab lab1copy = (new Lab("1")).of("UNSUBMITTED");
        assertFalse(lab1.equals(lab1copy));
    }

}
