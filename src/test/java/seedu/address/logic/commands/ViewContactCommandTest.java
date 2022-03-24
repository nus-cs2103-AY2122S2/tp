package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME_NUS_FINTECH_SOCIETY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.testutil.GroupBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code ViewContactCommand}.
 */
public class ViewContactCommandTest {

    public static final String NON_EXISTENT_GROUP_NAME = "Invalid Group Name";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ViewContactCommand(null));
    }

    @Test
    public void execute_invalidGroup_throwsCommandException() {
        Group groupToView = new GroupBuilder().withGroupName(NON_EXISTENT_GROUP_NAME).build();
        ViewContactCommand command = new ViewContactCommand(groupToView);

        assertCommandFailure(command, model, Messages.MESSAGE_NON_EXISTENT_GROUP);
    }

    @Test
    public void equals() {
        final ViewContactCommand standardCommand = new ViewContactCommand(new GroupBuilder()
                .withGroupName(VALID_GROUP_NAME_NUS_FINTECH_SOCIETY).build());

        // same value --> returns true
        ViewContactCommand commandWithSameValues = new ViewContactCommand(new GroupBuilder()
                .withGroupName(VALID_GROUP_NAME_NUS_FINTECH_SOCIETY).build());

        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object --> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null --> return false
        assertFalse(standardCommand.equals(null));

        // different group -> returns false
        assertFalse(standardCommand.equals(new ViewContactCommand(new GroupBuilder()
                .withGroupName("NUS Data Science Society").build())));
    }
}
