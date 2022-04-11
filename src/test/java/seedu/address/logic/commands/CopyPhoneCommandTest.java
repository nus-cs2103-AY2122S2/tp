package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandUnExecuteSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.awt.HeadlessException;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code CopyPhoneCommand}.
 */
public class CopyPhoneCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        try {
            Person personToCopy = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
            CopyPhoneCommand copyPhoneCommand = new CopyPhoneCommand(INDEX_FIRST_PERSON);

            String expectedMessage = String.format(CopyPhoneCommand.MESSAGE_COPY_PERSON_SUCCESS,
                    personToCopy.getPhone());

            ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

            assertCommandSuccess(copyPhoneCommand, model, expectedMessage, expectedModel);
        } catch (HeadlessException error) {
            assertTrue(error instanceof HeadlessException);
        }
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        try {
            Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
            CopyPhoneCommand copyPhoneCommand = new CopyPhoneCommand(outOfBoundIndex);

            assertCommandFailure(copyPhoneCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (HeadlessException error) {
            assertTrue(error instanceof HeadlessException);
        }
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        try {
            Person personToCopy = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
            CopyPhoneCommand copyPhoneCommand = new CopyPhoneCommand(INDEX_FIRST_PERSON);

            String expectedMessage = String.format(CopyPhoneCommand.MESSAGE_COPY_PERSON_SUCCESS,
                    personToCopy.getPhone());

            Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

            assertCommandSuccess(copyPhoneCommand, model, expectedMessage, expectedModel);
        } catch (HeadlessException error) {
            assertTrue(error instanceof HeadlessException);
        }

    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        try {
            showPersonAtIndex(model, INDEX_FIRST_PERSON);

            Index outOfBoundIndex = INDEX_SECOND_PERSON;
            // ensures that outOfBoundIndex is still in bounds of address book list
            assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

            CopyPhoneCommand copyPhoneCommand = new CopyPhoneCommand(outOfBoundIndex);

            assertCommandFailure(copyPhoneCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (HeadlessException error) {
            assertTrue(error instanceof HeadlessException);
        }
    }

    @Test
    public void unExecute_copy_successful() {
        try {
            Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
            Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
            CopyPhoneCommand copyFirstCommand = new CopyPhoneCommand(INDEX_FIRST_PERSON);

            assertCommandUnExecuteSuccess(copyFirstCommand, model, expectedModel);
        } catch (HeadlessException error) {
            assertTrue(error instanceof HeadlessException);
        }

    }

    @Test
    public void equals() {
        CopyPhoneCommand copyFirstCommand = new CopyPhoneCommand(INDEX_FIRST_PERSON);
        CopyPhoneCommand copySecondCommand = new CopyPhoneCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(copyFirstCommand.equals(copyFirstCommand));

        // same values -> returns true
        CopyPhoneCommand copyFirstCommandCopy = new CopyPhoneCommand(INDEX_FIRST_PERSON);
        assertTrue(copyFirstCommand.equals(copyFirstCommandCopy));

        // different types -> returns false
        assertFalse(copyFirstCommand.equals(1));

        // null -> returns false
        assertFalse(copyFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(copyFirstCommand.equals(copySecondCommand));
    }

}
