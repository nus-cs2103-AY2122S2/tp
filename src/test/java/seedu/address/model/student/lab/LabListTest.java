package seedu.address.model.student.lab;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.lab.LabList;


class LabListTest {

    @Test
    public void contains_nullLab_throwsNullPointerException() {
        LabList labs = new LabList();
        assertThrows(NullPointerException.class, () -> labs.contains(null));
    }
}
