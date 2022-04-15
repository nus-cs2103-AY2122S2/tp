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
import seedu.address.logic.commands.person.notecommands.DeleteWeaknessCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.StrategyBoard;
import seedu.address.model.UserPrefs;
import seedu.address.model.note.Note;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code DeleteWeaknessCommand}.
 */
public class DeleteWeaknessCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
            new StrategyBoard(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteWeaknessCommand(null, null));
    }

    @Test
    public void execute_validNoteIndexUnfilteredList_success() throws Exception {
        Person personToDeleteWeaknessFrom = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        List<Note> newWeaknesses = new ArrayList<>(personToDeleteWeaknessFrom.getWeaknesses());
        newWeaknesses.remove(NOTE_FIRST_INDEX.getZeroBased());
        Person personWithModifiedWeakness =
                new Person(personToDeleteWeaknessFrom.getName(), personToDeleteWeaknessFrom.getPhone(),
                personToDeleteWeaknessFrom.getEmail(), personToDeleteWeaknessFrom.getAddress(),
                personToDeleteWeaknessFrom.getTags(), personToDeleteWeaknessFrom.getStrengths(), newWeaknesses,
                personToDeleteWeaknessFrom.getMiscellaneous());

        DeleteWeaknessCommand deleteWeaknessCommand = new DeleteWeaknessCommand(INDEX_SECOND_PERSON, NOTE_FIRST_INDEX);

        String expectedMessage = String.format(DeleteWeaknessCommand.MESSAGE_SUCCESS, personWithModifiedWeakness);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(),
                model.getTaskBook(), new StrategyBoard(), new UserPrefs());
        deleteWeaknessCommand.execute(expectedModel);
        assertCommandSuccess(deleteWeaknessCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNoteIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundNoteIndex = Index.fromOneBased(model.getFilteredPersonList().get(
                INDEX_SECOND_PERSON.getZeroBased()).getWeaknesses().size() + 1);
        DeleteWeaknessCommand deleteWeaknessCommand =
                new DeleteWeaknessCommand(INDEX_SECOND_PERSON, outOfBoundNoteIndex);

        assertCommandFailure(deleteWeaknessCommand, model, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundNoteIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteWeaknessCommand deleteWeaknessCommand = new DeleteWeaknessCommand(outOfBoundNoteIndex, NOTE_FIRST_INDEX);

        assertCommandFailure(deleteWeaknessCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteWeaknessCommand deleteWeaknessFirstCommand =
                new DeleteWeaknessCommand(INDEX_SECOND_PERSON, NOTE_FIRST_INDEX);
        DeleteWeaknessCommand deleteWeaknessSecondCommand =
                new DeleteWeaknessCommand(INDEX_SECOND_PERSON, NOTE_SECOND_INDEX);

        // same object -> returns true
        assertTrue(deleteWeaknessFirstCommand.equals(deleteWeaknessFirstCommand));

        // same values -> returns true
        DeleteWeaknessCommand deleteWeaknessFirstCommandCopy =
                new DeleteWeaknessCommand(INDEX_SECOND_PERSON, NOTE_FIRST_INDEX);
        assertTrue(deleteWeaknessFirstCommand.equals(deleteWeaknessFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteWeaknessFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteWeaknessFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteWeaknessFirstCommand.equals(deleteWeaknessSecondCommand));
    }
}
