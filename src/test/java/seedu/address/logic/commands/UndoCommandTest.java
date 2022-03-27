package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertUndoRedoCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

class UndoCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_deleteCommandUndone_success() {

        Person personToDelete = model.getDisplayPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(UndoCommand.MESSAGE_UNDO_SUCCESS);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        model.deletePerson(personToDelete);
        model.commitAddressBook();

        UndoCommand undoCommand = new UndoCommand();

        assertUndoRedoCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_noCommandsToUndo_throwsCommandException() {

        String expectedMessage = String.format(UndoCommand.MESSAGE_UNDO_FAILED);

        UndoCommand undoCommand = new UndoCommand();

        assertCommandFailure(undoCommand, model, expectedMessage);
    }

    @Test
    void testEquals() {
        UndoCommand undoCommand = new UndoCommand();
        assertTrue(undoCommand.equals(undoCommand));
        assertTrue(undoCommand.equals(new UndoCommand()));
    }
}
