package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.testutil.GroupBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeassignCommand}.
 */
public class DeassignCommandTest {

    public static final String NON_EXISTENT_GROUP_NAME = "Invalid Group Name";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullGroupPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeassignCommand(null, null));
    }

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeassignCommand(null,
                new GroupBuilder().build()));
    }

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeassignCommand(Index.fromOneBased(1), null));
    }

    @Test
    public void execute_invalidGroup_throwsCommandException() {
        Group groupToDeassign = new GroupBuilder().withGroupName(NON_EXISTENT_GROUP_NAME).build();
        AssignCommand command = new AssignCommand(INDEX_FIRST_PERSON, groupToDeassign);

        assertCommandFailure(command, model, DeassignCommand.MESSAGE_GROUP_DOES_NOT_EXIST);
    }

}
