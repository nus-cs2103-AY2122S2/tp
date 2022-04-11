package seedu.address.model.lab;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.lab.exceptions.DuplicateLabException;
import seedu.address.model.lab.exceptions.LabNotFoundException;


class LabListTest {

    private final LabList labs = new LabList();

    @Test
    public void contains_nullLab_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> labs.contains(null));
    }

    @Test
    public void contains_labInList_success() {
        labs.add(new Lab("1"));
        assertTrue(labs.contains(new Lab("1")));
    }

    @Test
    public void contains_labNotInList_failure() {
        labs.add(new Lab("1"));
        assertFalse(labs.contains(new Lab("2")));
    }

    @Test
    public void contains_listEmpty_failure() {
        assertTrue(labs.isEmpty());
        assertFalse(labs.contains(new Lab("2")));
    }

    @Test
    public void getLab_labInList_success() {
        Lab l = new Lab("1");
        labs.add(l);
        assertEquals(l, labs.getLab(l));
        assertEquals(l, labs.getLab(1));
    }

    @Test
    public void getLab_labNotInList_throwsLabNotFoundException() {
        labs.add(new Lab("1"));
        assertThrows(LabNotFoundException.class, () -> labs.getLab(new Lab("2")));
        assertThrows(LabNotFoundException.class, () -> labs.getLab(2));
    }

    @Test
    public void getLab_listEmpty_throwsLabNotFoundException() {
        assertTrue(labs.isEmpty());
        assertThrows(LabNotFoundException.class, () -> labs.getLab(new Lab("2")));
        assertThrows(LabNotFoundException.class, () -> labs.getLab(2));
    }

    @Test
    public void add_nullLab_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> labs.add(null));
    }

    @Test
    public void add_validLab_success() {
        assertDoesNotThrow(() -> labs.add(new Lab("1")));
    }

    @Test
    public void add_duplicateLab_throwsDuplicateLabException() {
        labs.add(new Lab("1"));
        assertThrows(DuplicateLabException.class, () -> labs.add(new Lab("1")));
    }

    @Test
    public void setLab_nullTargetLab_throwsNullPointerException() {
        labs.add(new Lab("1"));
        assertThrows(NullPointerException.class, () -> labs.setLab(null, new Lab("2")));
    }

    @Test
    public void setLab_nullEditedLab_throwsNullPointerException() {
        labs.add(new Lab("1"));
        assertThrows(NullPointerException.class, () -> labs.setLab(new Lab("1"), null));
    }

    @Test
    public void setLab_targetLabNotInList_throwsLabNotFoundException() {
        labs.add(new Lab("1").of(LabStatus.SUBMITTED));
        assertThrows(LabNotFoundException.class, () -> labs.setLab(new Lab("1"), new Lab("2")));
    }

    @Test
    public void setLab_targetLabSameAsEdited_throwsDuplicateLabException() {
        labs.add(new Lab("1"));
        assertThrows(DuplicateLabException.class, () -> labs.setLab(new Lab("1"), new Lab("1")));
    }

    @Test
    public void setLab_targetLabInList_success() {
        labs.add(new Lab("1"));
        labs.setLab(new Lab("1"), new Lab("2"));
        assertEquals(new Lab("2"), labs.getLab(2));
        assertThrows(LabNotFoundException.class, () -> labs.getLab(1));
    }

    @Test
    public void remove_nullLab_throwsNullPointerException() {
        labs.add(new Lab("1"));
        assertThrows(NullPointerException.class, () -> labs.remove((Lab) null));
        assertThrows(NullPointerException.class, () -> labs.remove((Index) null));
    }

    @Test
    public void remove_indexOutOfBounds_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> labs.remove(Index.fromOneBased(1)));
    }

    @Test
    public void remove_labDoesNotExist_throwsLabNotFoundException() {
        assertThrows(LabNotFoundException.class, () -> labs.remove(new Lab("2")));
    }

    @Test
    public void remove_existingLab_success() {
        labs.add(new Lab("1"));
        labs.remove(new Lab("1"));
        LabList expectedLabList = new LabList();
        assertEquals(expectedLabList, labs);
        labs.add(new Lab("1"));
        labs.remove(Index.fromOneBased(1));
        assertEquals(expectedLabList, labs);
    }

    @Test
    public void setLabs_nullLabList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> labs.setLabs((LabList) null));
    }

    @Test
    public void setLabs_labList_replacesOwnListWithProvidedLabList() {
        labs.add(new Lab("1"));
        LabList expectedLabList = new LabList();
        expectedLabList.add(new Lab("2").of(LabStatus.SUBMITTED));
        labs.setLabs(expectedLabList);
        assertEquals(expectedLabList, labs);
    }

    @Test
    public void setLabs_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> labs.setLabs((List<Lab>) null));
    }

    @Test
    public void setLabs_list_replacesOwnListWithProvidedList() {
        List<Lab> labList = Arrays.asList(new Lab("1"), new Lab("2"));
        labs.setLabs(labList);
        LabList expectedLabList = new LabList();
        expectedLabList.add(new Lab("2"));
        expectedLabList.add(new Lab("1"));
        assertEquals(expectedLabList, labs);
    }

    @Test
    public void setLabs_listWithDuplicateLabs_throwsDuplicateLabException() {
        List<Lab> listWithDuplicateLabs = Arrays.asList(new Lab("1"), new Lab("1"));
        assertThrows(DuplicateLabException.class, () -> labs.setLabs(listWithDuplicateLabs));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        labs.add(new Lab("1"));
        assertThrows(UnsupportedOperationException.class, () -> labs.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void equals_sameList_success() {
        LabList labs1 = new LabList();
        LabList labs2 = new LabList();
        List<Lab> labList = Arrays.asList(new Lab("1"), new Lab("2"));
        labs1.setLabs(labList);
        labs2.setLabs(labList);
        assertEquals(labs1, labs2);
    }

    @Test
    public void equals_differentLists_failure() {
        LabList labs1 = new LabList();
        LabList labs2 = new LabList();
        List<Lab> labList = Arrays.asList(new Lab("1"), new Lab("2"));
        labs1.setLabs(labList);
        labs2.add(new Lab("1"));
        assertNotEquals(labs1, labs2);
    }

    @Test
    public void isEmpty_emptyList_success() {
        assertTrue(labs.isEmpty());
    }

    @Test
    public void isEmpty_nonEmptyList_success() {
        labs.add(new Lab("1"));
        assertFalse(labs.isEmpty());
    }

    @Test
    public void alignLabs_nullLabList_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> labs.alignLabs(null));
    }

    @Test
    public void alignLabs_differentLabList_success() {
        labs.add(new Lab("1").of(new LabMark("10")));
        labs.add(new Lab("2").of("SUBMITTED"));
        labs.add(new Lab("3"));
        labs.add(new Lab("5"));
        labs.add(new Lab("19"));

        LabList toAlignLabList = new LabList();
        toAlignLabList.add(new Lab("1").of("SUBMITTED"));
        toAlignLabList.add(new Lab("2"));
        toAlignLabList.add(new Lab("4"));

        LabList expectedLabList = new LabList();
        expectedLabList.add(new Lab("1").of(new LabMark("10")));
        expectedLabList.add(new Lab("2").of("SUBMITTED"));
        expectedLabList.add(new Lab("4"));

        labs.alignLabs(toAlignLabList);
        assertEquals(expectedLabList, labs);
    }
}
