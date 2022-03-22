package seedu.address.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.lab.Lab;
import seedu.address.model.student.exceptions.LabNotFoundException;

class MasterLabListTest {

    @Test
    public void removeLab_nullLab_throwsNullPointerException() {
        MasterLabList ml = new MasterLabList();
        ml.add(new Lab("1"));
        assertThrows(NullPointerException.class, () -> ml.removeLab(null));
    }

    @Test
    public void removeLab_labInList_success() {
        MasterLabList ml = new MasterLabList();
        ml.add(new Lab("1"));
        assertEquals(Index.fromZeroBased(0), ml.removeLab(new Lab("1")));
    }

    @Test
    public void removeLab_labNotInList_throwsLabNotFoundException() {
        MasterLabList ml = new MasterLabList();
        ml.add(new Lab("1"));
        assertThrows(LabNotFoundException.class, () -> ml.removeLab(new Lab("2")));
    }

    @Test
    public void removeLab_labEmpty_throwsLabNotFoundException() {
        MasterLabList ml = new MasterLabList();
        assertThrows(LabNotFoundException.class, () -> ml.removeLab(new Lab("2")));
    }
}