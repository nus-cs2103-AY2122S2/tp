package seedu.address.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.lab.Lab;
import seedu.address.model.lab.exceptions.LabNotFoundException;

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

    @Test
    public void getMasterLabList_assertIsCopy_success() {
        MasterLabList ml = new MasterLabList();
        ml.add(new Lab("1"));
        ml.getMasterList().add(new Lab("2"));
        MasterLabList expectedMasterLabList = new MasterLabList();
        expectedMasterLabList.add(new Lab("1"));
        assertEquals(expectedMasterLabList, ml);
    }

    @Test
    public void equals_sameList_success() {
        MasterLabList labs1 = new MasterLabList();
        MasterLabList labs2 = new MasterLabList();
        List<Lab> labList = Arrays.asList(new Lab("1"), new Lab("2"));
        labs1.setLabs(labList);
        labs2.setLabs(labList);
        assertTrue(labs1.equals(labs2));
    }

    @Test
    public void equals_differentLists_failure() {
        MasterLabList labs1 = new MasterLabList();
        MasterLabList labs2 = new MasterLabList();
        List<Lab> labList = Arrays.asList(new Lab("1"), new Lab("2"));
        labs1.setLabs(labList);
        labs2.add(new Lab("1"));
        assertFalse(labs1.equals(labs2));
    }
}
