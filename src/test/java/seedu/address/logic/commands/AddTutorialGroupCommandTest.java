package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_CS2101_G08;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.AddTutorialGroupDescriptorBuilder.VALID_TUTORIAL_GROUP_DESCRIPTOR_AMY;
import static seedu.address.testutil.AddTutorialGroupDescriptorBuilder.VALID_TUTORIAL_GROUP_DESCRIPTOR_BOB;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTutorialGroupCommand.AddTutorialGroupDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.AddTutorialGroupDescriptorBuilder;

public class AddTutorialGroupCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidStudentIndex_failure() {
        // index out of bounds
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());

        AddTutorialGroupCommand addTutorialGroupCommand = new AddTutorialGroupCommand(outOfBoundIndex,
                new AddTutorialGroupDescriptorBuilder().withTutorialGroup(VALID_TUTORIAL_GROUP_CS2101_G08).build());

        assertCommandFailure(addTutorialGroupCommand, model, AddTutorialGroupCommand.MESSAGE_INDEX_OUT_OF_RANGE);
    }

    @Test
    public void equals() {
        final AddTutorialGroupCommand standardCommand =
                new AddTutorialGroupCommand(INDEX_FIRST_STUDENT, VALID_TUTORIAL_GROUP_DESCRIPTOR_AMY);

        // same values -> return true
        AddTutorialGroupDescriptor copyDescriptor = VALID_TUTORIAL_GROUP_DESCRIPTOR_AMY;
        AddTutorialGroupCommand commandWithSameValues =
                new AddTutorialGroupCommand(INDEX_FIRST_STUDENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different index -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new AddTutorialGroupCommand(INDEX_FIRST_STUDENT,
                VALID_TUTORIAL_GROUP_DESCRIPTOR_BOB)));
    }
}
