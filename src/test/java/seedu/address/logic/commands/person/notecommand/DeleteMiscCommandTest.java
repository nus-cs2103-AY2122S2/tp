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
import seedu.address.logic.commands.person.notecommands.DeleteMiscCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.StrategyBoard;
import seedu.address.model.UserPrefs;
import seedu.address.model.note.Note;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code DeleteMiscCommand}.
 */
public class DeleteMiscCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
            new StrategyBoard(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteMiscCommand(null, null));
    }

    @Test
    public void execute_validNoteIndexUnfilteredList_success() throws Exception {
        Person personToDeleteMiscFrom = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        List<Note> newMisc = new ArrayList<>(personToDeleteMiscFrom.getMiscellaneous());
        newMisc.remove(NOTE_FIRST_INDEX.getZeroBased());
        Person personWithModifiedMisc = new Person(personToDeleteMiscFrom.getName(), personToDeleteMiscFrom.getPhone(),
                personToDeleteMiscFrom.getEmail(), personToDeleteMiscFrom.getAddress(),
                personToDeleteMiscFrom.getTags(),
                personToDeleteMiscFrom.getStrengths(), personToDeleteMiscFrom.getWeaknesses(), newMisc);

        DeleteMiscCommand deleteMiscCommand = new DeleteMiscCommand(INDEX_SECOND_PERSON, NOTE_FIRST_INDEX);

        String expectedMessage = String.format(DeleteMiscCommand.MESSAGE_SUCCESS, personWithModifiedMisc);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(),
                model.getTaskBook(), new StrategyBoard(), new UserPrefs());
        deleteMiscCommand.execute(expectedModel);
        assertCommandSuccess(deleteMiscCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNoteIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundNoteIndex = Index.fromOneBased(model.getFilteredPersonList().get(
                INDEX_SECOND_PERSON.getZeroBased()).getMiscellaneous().size() + 1);
        DeleteMiscCommand deleteMiscCommand = new DeleteMiscCommand(INDEX_SECOND_PERSON, outOfBoundNoteIndex);

        assertCommandFailure(deleteMiscCommand, model, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundNoteIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteMiscCommand deleteMiscCommand = new DeleteMiscCommand(outOfBoundNoteIndex, NOTE_FIRST_INDEX);

        assertCommandFailure(deleteMiscCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteMiscCommand deleteMiscFirstCommand = new DeleteMiscCommand(INDEX_SECOND_PERSON, NOTE_FIRST_INDEX);
        DeleteMiscCommand deleteMiscSecondCommand = new DeleteMiscCommand(INDEX_SECOND_PERSON, NOTE_SECOND_INDEX);

        // same object -> returns true
        assertTrue(deleteMiscFirstCommand.equals(deleteMiscFirstCommand));

        // same values -> returns true
        DeleteMiscCommand deleteMiscFirstCommandCopy = new DeleteMiscCommand(INDEX_SECOND_PERSON, NOTE_FIRST_INDEX);
        assertTrue(deleteMiscFirstCommand.equals(deleteMiscFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteMiscFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteMiscFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteMiscFirstCommand.equals(deleteMiscSecondCommand));
    }
}
