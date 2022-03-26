package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME_NUS_DATA_SCIENCE_SOCIETY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME_NUS_FINTECH_SOCIETY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_GROUP;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.testutil.GroupBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteGroupCommand}.
 */
public class DeleteGroupCommandTest {

    public static final String NON_EXISTENT_GROUP_NAME = "Invalid Group Name";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteGroupCommand(null));
    }

    @Test
    public void execute_validGroupUnfilteredList_success() {
        Group groupToDelete = model.getFilteredGroupList().get(INDEX_FIRST_GROUP.getZeroBased());
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(groupToDelete);

        String expectedMessage = String.format(DeleteGroupCommand.MESSAGE_DELETE_GROUP_SUCCESS, groupToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteGroup(groupToDelete);

        assertCommandSuccess(deleteGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidGroup_throwsCommandException() {
        Group groupToDelete = new GroupBuilder().withGroupName(NON_EXISTENT_GROUP_NAME).build();
        ViewTaskCommand command = new ViewTaskCommand(groupToDelete);

        assertCommandFailure(command, model, Messages.MESSAGE_NON_EXISTENT_GROUP);
    }

    @Test
    public void equals() {
        final DeleteGroupCommand standardCommand = new DeleteGroupCommand(new GroupBuilder()
                .withGroupName(VALID_GROUP_NAME_NUS_FINTECH_SOCIETY).build());

        // same value --> returns true
        DeleteGroupCommand commandWithSameValues = new DeleteGroupCommand(new GroupBuilder()
                .withGroupName(VALID_GROUP_NAME_NUS_FINTECH_SOCIETY).build());

        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object --> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null --> return false
        assertFalse(standardCommand.equals(null));

        // different group -> returns false
        assertFalse(standardCommand.equals(new DeleteGroupCommand(new GroupBuilder()
                .withGroupName(VALID_GROUP_NAME_NUS_DATA_SCIENCE_SOCIETY).build())));
    }
}
