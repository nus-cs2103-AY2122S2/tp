package seedu.contax.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.contax.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.contax.testutil.TypicalPersons.COLLEAGUES;
import static seedu.contax.testutil.TypicalPersons.FRIENDS;
import static seedu.contax.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.Messages;
import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.EditTagCommand.EditTagDescriptor;
import seedu.contax.model.AddressBook;
import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.Schedule;
import seedu.contax.model.UserPrefs;
import seedu.contax.model.tag.Tag;
import seedu.contax.testutil.EditTagDescriptorBuilder;
import seedu.contax.testutil.TagBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditTagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new Schedule(), new UserPrefs());

    @Test
    public void execute_uniqueTagChange_success() {
        Tag editedTag = new TagBuilder().withName("best friends").build();
        EditTagDescriptor descriptor = new EditTagDescriptorBuilder(editedTag).build();
        EditTagCommand command = new EditTagCommand(Index.fromOneBased(1), descriptor);

        String expectedMessage = String.format(EditTagCommand.MESSAGE_EDIT_TAG_SUCCESS, editedTag);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new Schedule(),
                new UserPrefs());
        expectedModel.setTag(FRIENDS, editedTag);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sameTagChange_success() {
        EditTagDescriptor descriptor = new EditTagDescriptorBuilder(FRIENDS).build();
        EditTagCommand command = new EditTagCommand(Index.fromOneBased(1), descriptor);

        assertCommandFailure(command, model, EditTagCommand.MESSAGE_TAG_EXISTS);
    }

    @Test
    public void execute_newTagExists_throwsCommandException() {
        EditTagDescriptor descriptor = new EditTagDescriptorBuilder(COLLEAGUES).build();
        EditTagCommand command = new EditTagCommand(Index.fromOneBased(1), descriptor);

        assertCommandFailure(command, model, EditTagCommand.MESSAGE_TAG_EXISTS);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        EditTagDescriptor descriptor = new EditTagDescriptorBuilder().withName("does not matter").build();
        Index index = Index.fromOneBased(5);
        EditTagCommand command = new EditTagCommand(index, descriptor);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        EditTagDescriptor descriptor = new EditTagDescriptorBuilder().withName("friends").build();
        EditTagDescriptor descriptor2 = new EditTagDescriptorBuilder().withName("enemies").build();

        Index first = Index.fromOneBased(1);
        Index second = Index.fromOneBased(2);
        EditTagCommand command = new EditTagCommand(first, descriptor);
        EditTagCommand command2 = new EditTagCommand(second, descriptor2);
        EditTagCommand command3 = new EditTagCommand(first, descriptor2);
        EditTagCommand command4 = new EditTagCommand(second, descriptor);

        assertTrue(command.equals(command));
        assertTrue(command.equals(new EditTagCommand(first, descriptor)));

        // Different index and descriptors
        assertFalse(command.equals(command2));

        // Different descriptors
        assertFalse(command.equals(command3));

        // Different index
        assertFalse(command.equals(command4));

        assertFalse(command.equals(null));
        assertFalse(command.equals(descriptor));
        assertFalse(command.equals(0));
    }
}
