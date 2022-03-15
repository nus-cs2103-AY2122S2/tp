package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;

import static seedu.address.testutil.Assert.assertThrows;

public class AssignCommandTest {

    @Test
    public void constructor_nullGroupPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssignCommand(null, null));
    }
}
