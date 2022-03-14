package seedu.address.model.person.lab;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.LabAlreadyGradedException;
import seedu.address.model.person.exceptions.LabAlreadySubmittedException;
import seedu.address.model.person.exceptions.LabNotSubmittedException;


public class LabTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Lab(null));
    }

    @Test
    public void constructor_invalidLab_throwsIllegalArgumentException() {
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
        assertFalse(Lab.isValidLab("1a")); // invalid lab number

        // valid lab
        assertTrue(Lab.isValidLab("1")); // valid integer
        assertTrue(Lab.isValidLab("2")); // valid integer
        assertTrue(Lab.isValidLab("123456789")); // valid integer
    }

    @Test
    public void submitLab_alreadySubmitted_throwsLabAlreadySubmittedException() {
        Lab stub = new Lab("1");
        stub = stub.submitLab();
        assertThrows(LabAlreadySubmittedException.class, stub::submitLab);
    }

    @Test
    public void gradeLab_alreadyGraded_throwsLabAlreadyGradedException() {
        Lab stub = new Lab("1");
        stub = stub.submitLab();
        stub = stub.gradeLab();
        assertThrows(LabAlreadyGradedException.class, stub::gradeLab);
    }

    @Test
    public void gradeLab_notYetSubmitted_throwsLabNotSubmittedException() {
        Lab stub = new Lab("1");
        assertThrows(LabNotSubmittedException.class, stub::gradeLab);
    }

    @Test
    public void equals_success() {
        Lab lab1 = (new Lab("1")).of("SUBMITTED");
        Lab lab1copy = (new Lab("1")).of("SUBMITTED");
        assertTrue(lab1.equals(lab1copy));
    }

}
