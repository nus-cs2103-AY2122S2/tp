package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTags.VALID_TAGNAME_COLLEAGUES;
import static seedu.address.testutil.TypicalTags.VALID_TAGNAME_NEIGHBOURS;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code CreateTagCommand}.
 */
public class CreateTagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validTagNameAndTagDoesNotExistInModel_success() {
        CreateTagCommand createTagCommand = new CreateTagCommand(VALID_TAGNAME_NEIGHBOURS);
        String expectedMessage = String.format(CreateTagCommand.MESSAGE_CREATE_TAG_SUCCESS, VALID_TAGNAME_NEIGHBOURS);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addTag(new Tag(VALID_TAGNAME_NEIGHBOURS));
        assertCommandSuccess(createTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sameTagNameDifferentCase_throwsCommandException() {
        Tag newTag = new Tag(VALID_TAGNAME_NEIGHBOURS);
        model.addTag(newTag);
        CreateTagCommand createTagCommand = new CreateTagCommand("frIenDS");
        String expectedMessage = CreateTagCommand.MESSAGE_DUPLICATE_TAG;
        assertCommandFailure(createTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_sameCaseSameTagExistsInModel_throwsCommandException() {
        Tag newTag = new Tag(VALID_TAGNAME_NEIGHBOURS);
        model.addTag(newTag);
        CreateTagCommand createTagCommand = new CreateTagCommand(VALID_TAGNAME_NEIGHBOURS);
        String expectedMessage = CreateTagCommand.MESSAGE_DUPLICATE_TAG;
        assertCommandFailure(createTagCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        CreateTagCommand createTagFirstCommand = new CreateTagCommand(VALID_TAGNAME_NEIGHBOURS);
        CreateTagCommand createTagSecondCommand = new CreateTagCommand(VALID_TAGNAME_COLLEAGUES);
        // same object -> returns true
        assertTrue(createTagFirstCommand.equals(createTagFirstCommand));

        // same values -> returns true
        CreateTagCommand createTagFirstCommandCopy = new CreateTagCommand(VALID_TAGNAME_NEIGHBOURS);
        assertTrue(createTagFirstCommand.equals(createTagFirstCommandCopy));

        // different types -> returns false
        assertFalse(createTagFirstCommand.equals(1));

        // null -> returns false
        assertFalse(createTagFirstCommand.equals(null));

        // different tag -> returns false
        assertFalse(createTagFirstCommand.equals(createTagSecondCommand));
    }

}
