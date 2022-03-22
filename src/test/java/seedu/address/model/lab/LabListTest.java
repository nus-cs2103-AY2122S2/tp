package seedu.address.model.lab;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


class LabListTest {

    @Test
    public void contains_nullLab_throwsNullPointerException() {
        LabList labs = new LabList();
        assertThrows(NullPointerException.class, () -> labs.contains(null));
    }
}
