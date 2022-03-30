package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.InsurancePackagesSet;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UndoCommand}.
 */
public class UndoCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new InsurancePackagesSet());

    @Test
    public void execute_undoCommand_throwsCommandException() {
        UndoCommand undoCommand = new UndoCommand();

        assertCommandFailure(undoCommand, model, "No previous command to undo");
    }

    @Test
    public void execute_undoCommand_success() {
        try {
            Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
            DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
            UndoCommand undoCommand = new UndoCommand();

            String expectedMessage = String.format(UndoCommand.MESSAGE_SUCCESS);
            CommandResult expectedCommandResult = new CommandResult(expectedMessage);

            Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new InsurancePackagesSet());
            expectedModel.deletePerson(personToDelete);
            expectedModel.undoCommand();

            deleteCommand.execute(model);
            CommandResult result = undoCommand.execute(model);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, model);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of undo should not fail.", ce);
        }
    }
}
