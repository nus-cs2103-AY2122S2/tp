package seedu.address.logic.commands;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code AssignCommand}.
 */
public class AssignCommandTest {

    @Test
    public void constructor_nullGroupPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssignCommand(null, null));
    }
}
