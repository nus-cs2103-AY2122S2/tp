package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalEntries.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ENTRY;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.entry.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UnarchiveCommand}.
 */
public class UnarchiveCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @BeforeEach
    public void fixPersonsList() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        showArchivedPersons(model);
    }

    @Test
    public void execute_validIndexUnfilteredPersonList_success() throws CommandException {
        Person personToArchive = model.getFilteredPersonList().get(INDEX_FIRST_ENTRY.getZeroBased());
        UnarchiveCommand unarchiveCommand = new UnarchiveCommand(INDEX_FIRST_ENTRY);

        String expectedMessage = String.format(UnarchiveCommand.MESSAGE_UNARCHIVE_ENTRY_SUCCESS, personToArchive);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        showArchivedPersons(expectedModel);
        expectedModel.archiveEntry(INDEX_FIRST_ENTRY.getZeroBased(), false);
        expectedModel.showPersonList(Model.PREDICATE_SHOW_ALL);
        showArchivedPersons(expectedModel);

        assertCommandSuccess(unarchiveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        UnarchiveCommand unarchiveCommand = new UnarchiveCommand(outOfBoundIndex);

        assertCommandFailure(unarchiveCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredPersonList_success() throws CommandException {
        showPersonAtIndex(model, INDEX_FIRST_ENTRY);

        Person personToArchive = model.getFilteredPersonList().get(INDEX_FIRST_ENTRY.getZeroBased());
        UnarchiveCommand unarchiveCommand = new UnarchiveCommand(INDEX_FIRST_ENTRY);

        String expectedMessage = String.format(UnarchiveCommand.MESSAGE_UNARCHIVE_ENTRY_SUCCESS, personToArchive);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        showArchivedPersons(expectedModel);
        showPersonAtIndex(expectedModel, INDEX_FIRST_ENTRY);
        expectedModel.archiveEntry(INDEX_FIRST_ENTRY.getZeroBased(), false);
        showArchivedPersons(expectedModel);

        assertCommandSuccess(unarchiveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredPersonList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_ENTRY);

        Index outOfBoundIndex = INDEX_SECOND_ENTRY;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        UnarchiveCommand unarchiveCommand = new UnarchiveCommand(outOfBoundIndex);

        assertCommandFailure(unarchiveCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_alreadyUnarchivedPerson_throwsCommandException() {
        model.showPersonList(Model.PREDICATE_SHOW_UNARCHIVED_ONLY);
        Person unarchivedPerson = model.getFilteredPersonList().get(0);
        UnarchiveCommand unarchiveCommand = new UnarchiveCommand(INDEX_FIRST_ENTRY);

        assertCommandFailure(unarchiveCommand, model,
                unarchivedPerson.getName().toString() + " is already not Archived");
    }

    @Test
    public void equals() {
        UnarchiveCommand unarchiveFirstCommand = new UnarchiveCommand(INDEX_FIRST_ENTRY);
        UnarchiveCommand unarchiveSecondCommand = new UnarchiveCommand(INDEX_SECOND_ENTRY);

        // same object -> returns true
        assertTrue(unarchiveFirstCommand.equals(unarchiveFirstCommand));

        // same values -> returns true
        UnarchiveCommand archiveFirstCommandCopy = new UnarchiveCommand(INDEX_FIRST_ENTRY);
        assertTrue(unarchiveFirstCommand.equals(archiveFirstCommandCopy));

        // different types -> returns false
        assertFalse(unarchiveFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unarchiveFirstCommand.equals(null));

        // different index -> returns false
        assertFalse(unarchiveFirstCommand.equals(unarchiveSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show all unarchived persons
     */
    private void showArchivedPersons(Model model) {
        model.showPersonList(Model.PREDICATE_SHOW_ARCHIVED_ONLY);
    }
}
