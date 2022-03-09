package seedu.contax.logic.commands;

import static seedu.contax.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.contax.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.contax.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.contax.testutil.TypicalPersons.getTypicalTags;
import static seedu.contax.testutil.TypicalTags.getTagOnlyAddressBook;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.Messages;
import seedu.contax.commons.core.index.Index;
import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.Schedule;
import seedu.contax.model.UserPrefs;
import seedu.contax.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteTagCommand}.
 */
public class DeleteTagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new Schedule(), new UserPrefs());

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getTagList().size() + 1);
        DeleteTagCommand deleteCommand = new DeleteTagCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
    }

    @Test
    public void execute_deleteTagNoPersons_success() {
        Model noPersonsModel = new ModelManager(getTagOnlyAddressBook(), new Schedule(), new UserPrefs());
        Tag tagToDelete = noPersonsModel.getAddressBook().getTagList().get(0);
        Index deleteFirst = Index.fromOneBased(1);
        DeleteTagCommand deleteCommand = new DeleteTagCommand(deleteFirst);

        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_DELETE_TAG_SUCCESS, tagToDelete);
        Model expectedModel = new ModelManager(noPersonsModel.getAddressBook(), new Schedule(), new UserPrefs());
        expectedModel.deleteTag(tagToDelete);

        assertCommandSuccess(deleteCommand, noPersonsModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteTagNoPersonsWithTag_success() {
        Tag tagToDelete = getTypicalTags().get(2);
        DeleteTagCommand deleteCommand = new DeleteTagCommand(Index.fromZeroBased(2));

        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_DELETE_TAG_SUCCESS, tagToDelete);
        Model expectedModel = new ModelManager(model.getAddressBook(), new Schedule(), new UserPrefs());
        expectedModel.deleteTag(tagToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    // Checks for cascading deletion
    @Test
    public void execute_deleteTagPersonsWithTag_success() {
        Tag tagToDelete = getTypicalTags().get(0);
        DeleteTagCommand deleteCommand = new DeleteTagCommand(Index.fromZeroBased(0));

        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_DELETE_TAG_SUCCESS, tagToDelete);
        Model expectedModel = new ModelManager(model.getAddressBook(), new Schedule(), new UserPrefs());
        expectedModel.deleteTag(tagToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }
}
