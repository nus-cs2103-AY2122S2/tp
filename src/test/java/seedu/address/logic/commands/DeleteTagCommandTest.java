package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TAG;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TAG;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteTagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Tag tagToDelete = model.getTagList().get(INDEX_FIRST_TAG.getZeroBased());
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_TAG);

        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_DELETE_TAG_SUCCESS, tagToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        // delete tag
        expectedModel.deleteTag(tagToDelete);
        // delete tag from each person
        for (Person currPerson : expectedModel.getAddressBook().getPersonList()) {
            Set<Tag> tempTags = currPerson.getTags();
            Set<Tag> tagCopy = new HashSet<>(tempTags);
            tagCopy.removeIf(t -> t.isSameTag(tagToDelete));
            Person newPerson = new Person(currPerson.getName(), currPerson.getPhone(),
                    currPerson.getEmail(), currPerson.getAddress(), tagCopy, currPerson.getCourse(),
                    currPerson.getMatricCard(), currPerson.getTelegram());
            expectedModel.setPerson(currPerson, newPerson);
        }
        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getTagList().size() + 1);
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(outOfBoundIndex);

        assertCommandFailure(deleteTagCommand, model, Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteTagCommand deleteFirstTagCommand = new DeleteTagCommand(INDEX_FIRST_TAG);
        DeleteTagCommand deleteSecondTagCommand = new DeleteTagCommand(INDEX_SECOND_TAG);

        // same object -> returns true
        assertTrue(deleteFirstTagCommand.equals(deleteFirstTagCommand));

        // same values -> returns true
        DeleteTagCommand deleteFirstTagCommandCopy = new DeleteTagCommand(INDEX_FIRST_TAG);
        assertTrue(deleteFirstTagCommand.equals(deleteFirstTagCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstTagCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstTagCommand.equals(null));

        // different tag -> returns false
        assertFalse(deleteFirstTagCommand.equals(deleteSecondTagCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTag(Model model) {
        assertTrue(model.getTagList().isEmpty());
    }
}
