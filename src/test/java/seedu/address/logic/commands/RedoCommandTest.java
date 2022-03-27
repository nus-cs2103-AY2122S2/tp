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

class RedoCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_deleteCommandRedone_success() {

        Person personToDelete = model.getDisplayPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(RedoCommand.MESSAGE_REDO_SUCCESS);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        model.deletePerson(personToDelete);
        model.commitAddressBook();
        model.undoAddressBook();

        RedoCommand redoCommand = new RedoCommand();

        assertUndoRedoCommandSuccess(redoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_noCommandsToRedo_throwsCommandException() {

        String expectedMessage = String.format(RedoCommand.MESSAGE_REDO_FAILED);

        RedoCommand redoCommand = new RedoCommand();

        assertCommandFailure(redoCommand, model, expectedMessage);
    }

    @Test
    void testEquals() {
        RedoCommand redoCommand = new RedoCommand();
        assertTrue(redoCommand.equals(redoCommand));
        assertTrue(redoCommand.equals(new RedoCommand()));
    }
}
