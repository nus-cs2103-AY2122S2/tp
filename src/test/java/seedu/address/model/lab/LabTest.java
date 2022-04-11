package seedu.address.model.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.lab.exceptions.DuplicateLabException;
import seedu.address.model.lab.exceptions.InvalidLabStatusException;

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
    public void isValidLab() {
        // null lab
        assertThrows(NullPointerException.class, () -> Lab.isValidLab(null));

        // blank lab
        assertFalse(Lab.isValidLab("")); // empty string
        assertFalse(Lab.isValidLab(" ")); // spaces only

        // invalid lab
        assertFalse(Lab.isValidLab("1a")); // invalid lab number, includes characters
        assertFalse(Lab.isValidLab("123456789")); // larger than 20
        assertFalse(Lab.isValidLab("21")); // larger than 20
        assertFalse(Lab.isValidLab("2147483648")); // larger than max int value
        assertFalse(Lab.isValidLab("-1")); // includes '-', which is not allowed

        // valid lab
        assertTrue(Lab.isValidLab("1")); // valid integer
        assertTrue(Lab.isValidLab("2")); // valid integer
        assertTrue(Lab.isValidLab("001")); // valid lab number
        assertTrue(Lab.isValidLab("00")); // valid lab number
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
    public void editLabStatus_statusGraded_throwInvalidLabStatusException() {
        Lab stub = new Lab("1");
        assertThrows(InvalidLabStatusException.class, () -> stub.editLabStatus(LabStatus.GRADED));
    }

    @Test
    public void editLabStatus_changeStatusFromUnsubmittedToSubmitted_success() {
        Lab stub = new Lab("1");
        stub = stub.editLabStatus(LabStatus.SUBMITTED);

        assertEquals(new Lab("1").of(LabStatus.SUBMITTED.name()), stub);
        assertNotEquals(new Lab("1"), stub);
    }

    @Test
    public void editLabStatus_changeStatusFromGradedToSubmitted_success() {
        Lab stub = new Lab("1").of(new LabMark("10")); // graded Lab
        stub = stub.editLabStatus(LabStatus.SUBMITTED);

        assertEquals(new Lab("1").of(LabStatus.SUBMITTED.name()), stub);
        assertNotEquals(new Lab("1").of(new LabMark("10")), stub);
    }

    @Test
    public void editLabMark_sameMarksProvided_throwsDuplicateLabException() {
        Lab stub = new Lab("1");
        Lab gradedStub = stub.of(new LabMark("10"));
        assertThrows(DuplicateLabException.class, () -> gradedStub.editLabMark(new LabMark("10")));
    }

    @Test
    public void editLabMark_differentMarksProvided_success() {
        Lab stub = new Lab("1");
        Lab gradedStub = stub.of(new LabMark("10"));
        stub = gradedStub.editLabMark(new LabMark("20"));

        assertEquals(new Lab("1").of(new LabMark("20")), stub);
        assertNotEquals(gradedStub, stub);
    }

    @Test
    public void of_statusSubmitted_success() {
        Lab stub = new Lab("1");
        Lab submittedStub = stub.of(LabStatus.SUBMITTED);

        assertNotEquals(stub, submittedStub);
        assertEquals(submittedStub, new Lab("1").of("SUBMITTED"));
    }

    @Test
    public void of_statusGraded_throwsInvalidLabStatusException() {
        Lab stub = new Lab("1");
        assertThrows(InvalidLabStatusException.class, () -> stub.of(LabStatus.GRADED));
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
        stub2.editLabStatus(LabStatus.SUBMITTED);
        assertTrue(stub.isSameLab(stub2));
    }

    @Test
    public void isSameLab_differentLabNumber_failure() {
        Lab stub = new Lab("1");
        Lab stub2 = new Lab("2");
        assertFalse(stub.isSameLab(stub2));
    }

    @Test
    public void hasSameLabStatus_sameLabNumberSameLabStatus_success() {
        Lab stub1 = new Lab("1").of(LabStatus.SUBMITTED);
        Lab stub2 = new Lab("1").of(LabStatus.SUBMITTED);
        assertTrue(stub1.hasSameLabStatus(stub2));
    }

    @Test
    public void hasSameLabStatus_differentLabNumberSameLabStatus_success() {
        Lab stub1 = new Lab("1").of(LabStatus.SUBMITTED);
        Lab stub2 = new Lab("2").of(LabStatus.SUBMITTED);
        assertTrue(stub1.hasSameLabStatus(stub2));
    }

    @Test
    public void hasSameLabStatus_sameLabNumberDifferentLabStatus_failure() {
        Lab stub1 = new Lab("1").of(LabStatus.UNSUBMITTED);
        Lab stub2 = new Lab("1").of(LabStatus.SUBMITTED);
        assertFalse(stub1.hasSameLabStatus(stub2));
    }

    @Test
    public void hasSameLabStatus_differentLabNumberDifferentLabStatus_failure() {
        Lab stub1 = new Lab("1").of(LabStatus.UNSUBMITTED);
        Lab stub2 = new Lab("2").of(LabStatus.SUBMITTED);
        assertFalse(stub1.hasSameLabStatus(stub2));
    }

    @Test
    public void hasSameLabStatus_differentLabNumberBothGradedWithDifferentLabMark_success() {
        Lab stub1 = new Lab("1").of(new LabMark("20"));
        Lab stub2 = new Lab("2").of(new LabMark("30"));
        assertTrue(stub1.hasSameLabStatus(stub2));
    }

    @Test
    public void hasSameLabStatus_nullInput_throwsNullPointerException() {
        Lab stub = new Lab("1").of(LabStatus.UNSUBMITTED);
        assertThrows(NullPointerException.class, () -> stub.hasSameLabStatus(null));
    }

    @Test
    public void equals_sameLab_success() {
        Lab lab1 = (new Lab("1")).of("SUBMITTED");
        Lab lab2 = (new Lab("1")).of("SUBMITTED");
        assertEquals(lab1, lab2);
    }

    @Test
    public void equals_differentLabNumber_failure() {
        Lab lab1 = (new Lab("1")).of("SUBMITTED");
        Lab lab2 = (new Lab("2")).of("SUBMITTED");
        assertNotEquals(lab1, lab2);
    }

    @Test
    public void equals_differentLabStatus_failure() {
        Lab lab1 = (new Lab("1")).of("SUBMITTED");
        Lab lab2 = (new Lab("1")).of("UNSUBMITTED");
        assertNotEquals(lab1, lab2);
    }

    @Test
    public void equals_differentLabMark_failure() {
        Lab lab1 = (new Lab("1")).of(new LabMark("10"));
        Lab lab2 = (new Lab("1")).of(new LabMark("20"));
        assertNotEquals(lab1, lab2);
    }

    @Test
    public void getDetails_gradedWithMarks() {
        Lab lab1 = new Lab("1").of("GRADED", "12");
        assertEquals(lab1.getDetails(), "Marks: " + lab1.labMark);
    }

    @Test
    public void getDetails_submitted() {
        Lab lab = new Lab("3").of("SUBMITTED");
        assertEquals(lab.getDetails(), lab.labStatus.describe());
    }

    @Test
    public void getDetails_unsubmitted() {
        Lab lab = new Lab("2").of("UNSUBMITTED");
        assertEquals(lab.getDetails(), lab.labStatus.describe());
    }

}
