package seedu.address.logic.commands.person.notecommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.NOTE_FIRST_INDEX;
import static seedu.address.testutil.TypicalIndexes.NOTE_SECOND_INDEX;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.person.notecommands.DeleteStrengthCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.StrategyBoard;
import seedu.address.model.UserPrefs;
import seedu.address.model.note.Note;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code DeleteStrengthCommand}.
 */
public class DeleteStrengthCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
            new StrategyBoard(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteStrengthCommand(null, null));
    }

    @Test
    public void execute_validNoteIndexUnfilteredList_success() throws Exception {
        Person personToDeleteStrengthFrom = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        List<Note> newStrengths = new ArrayList<>(personToDeleteStrengthFrom.getStrengths());
        newStrengths.remove(NOTE_FIRST_INDEX.getZeroBased());
        Person personWithModifiedStrength = new Person(personToDeleteStrengthFrom.getName(),
                personToDeleteStrengthFrom.getPhone(),
                personToDeleteStrengthFrom.getEmail(), personToDeleteStrengthFrom.getAddress(),
                personToDeleteStrengthFrom.getTags(), newStrengths,
                personToDeleteStrengthFrom.getWeaknesses(), personToDeleteStrengthFrom.getMiscellaneous());

        DeleteStrengthCommand deleteStrengthCommand = new DeleteStrengthCommand(INDEX_SECOND_PERSON, NOTE_FIRST_INDEX);

        String expectedMessage = String.format(DeleteStrengthCommand.MESSAGE_SUCCESS, personWithModifiedStrength);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(),
                model.getTaskBook(), new StrategyBoard(), new UserPrefs());
        deleteStrengthCommand.execute(expectedModel);
        assertCommandSuccess(deleteStrengthCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNoteIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundNoteIndex = Index.fromOneBased(model.getFilteredPersonList().get(
                INDEX_SECOND_PERSON.getZeroBased()).getStrengths().size() + 1);
        DeleteStrengthCommand deleteStrengthCommand = new DeleteStrengthCommand(INDEX_SECOND_PERSON,
                outOfBoundNoteIndex);

        assertCommandFailure(deleteStrengthCommand, model, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundNoteIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteStrengthCommand deleteStrengthCommand = new DeleteStrengthCommand(outOfBoundNoteIndex, NOTE_FIRST_INDEX);

        assertCommandFailure(deleteStrengthCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteStrengthCommand deleteStrengthFirstCommand =
                new DeleteStrengthCommand(INDEX_SECOND_PERSON, NOTE_FIRST_INDEX);
        DeleteStrengthCommand deleteStrengthSecondCommand =
                new DeleteStrengthCommand(INDEX_SECOND_PERSON, NOTE_SECOND_INDEX);

        // same object -> returns true
        assertTrue(deleteStrengthFirstCommand.equals(deleteStrengthFirstCommand));

        // same values -> returns true
        DeleteStrengthCommand deleteStrengthFirstCommandCopy =
                new DeleteStrengthCommand(INDEX_SECOND_PERSON, NOTE_FIRST_INDEX);
        assertTrue(deleteStrengthFirstCommand.equals(deleteStrengthFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteStrengthFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteStrengthFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteStrengthFirstCommand.equals(deleteStrengthSecondCommand));
    }
}
