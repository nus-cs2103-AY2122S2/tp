package seedu.address.logic.commands;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.NUS_FINTECH_SOCIETY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.testutil.GroupBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeassignCommand}.
 */
public class DeassignCommandTest {

    @Test
    public void constructor_nullGroupPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeassignCommand(null, null));
    }

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeassignCommand(null,
                new GroupBuilder(NUS_FINTECH_SOCIETY).build()));
    }

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeassignCommand(Index.fromOneBased(1), null));
    }
}
