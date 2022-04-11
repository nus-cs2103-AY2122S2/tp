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
 * {@code ArchiveCommand}.
 */
public class ArchiveCommandTest {

    private Model model;

    @BeforeEach
    public void fixPersonsList() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.showPersonList(Model.PREDICATE_SHOW_UNARCHIVED_ONLY);
    }

    @Test
    public void execute_validIndexUnfilteredPersonList_success() throws CommandException {
        Person personToArchive = model.getFilteredPersonList().get(INDEX_FIRST_ENTRY.getZeroBased());
        ArchiveCommand archiveCommand = new ArchiveCommand(INDEX_FIRST_ENTRY);

        String expectedMessage = String.format(ArchiveCommand.MESSAGE_ARCHIVE_ENTRY_SUCCESS, personToArchive);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        showUnarchivedPersons(expectedModel);
        expectedModel.archiveEntry(INDEX_FIRST_ENTRY.getZeroBased(), true);
        expectedModel.showPersonList(Model.PREDICATE_SHOW_ALL);
        showUnarchivedPersons(expectedModel);

        assertCommandSuccess(archiveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ArchiveCommand archiveCommand = new ArchiveCommand(outOfBoundIndex);

        assertCommandFailure(archiveCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredPersonList_success() throws CommandException {
        showPersonAtIndex(model, INDEX_FIRST_ENTRY);

        Person personToArchive = model.getFilteredPersonList().get(INDEX_FIRST_ENTRY.getZeroBased());
        ArchiveCommand archiveCommand = new ArchiveCommand(INDEX_FIRST_ENTRY);

        String expectedMessage = String.format(ArchiveCommand.MESSAGE_ARCHIVE_ENTRY_SUCCESS, personToArchive);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        showUnarchivedPersons(expectedModel);
        showPersonAtIndex(expectedModel, INDEX_FIRST_ENTRY);
        expectedModel.archiveEntry(INDEX_FIRST_ENTRY.getZeroBased(), true);
        showUnarchivedPersons(expectedModel);

        assertCommandSuccess(archiveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredPersonList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_ENTRY);

        Index outOfBoundIndex = INDEX_SECOND_ENTRY;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        ArchiveCommand archiveCommand = new ArchiveCommand(outOfBoundIndex);

        assertCommandFailure(archiveCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_alreadyArchivedPerson_throwsCommandException() {
        model.showPersonList(Model.PREDICATE_SHOW_ARCHIVED_ONLY);
        Person archivedPerson = model.getFilteredPersonList().get(0);
        ArchiveCommand archiveCommand = new ArchiveCommand(INDEX_FIRST_ENTRY);

        assertCommandFailure(archiveCommand, model, archivedPerson.getName().toString() + " is already Archived");
    }

    @Test
    public void equals() {
        ArchiveCommand archiveFirstCommand = new ArchiveCommand(INDEX_FIRST_ENTRY);
        ArchiveCommand archiveSecondCommand = new ArchiveCommand(INDEX_SECOND_ENTRY);

        // same object -> returns true
        assertTrue(archiveFirstCommand.equals(archiveFirstCommand));

        // same values -> returns true
        ArchiveCommand archiveFirstCommandCopy = new ArchiveCommand(INDEX_FIRST_ENTRY);
        assertTrue(archiveFirstCommand.equals(archiveFirstCommandCopy));

        // different types -> returns false
        assertFalse(archiveFirstCommand.equals(1));

        // null -> returns false
        assertFalse(archiveFirstCommand.equals(null));

        // different index -> returns false
        assertFalse(archiveFirstCommand.equals(archiveSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show all unarchived persons
     */
    private void showUnarchivedPersons(Model model) {
        model.showPersonList(Model.PREDICATE_SHOW_UNARCHIVED_ONLY);
    }
}
