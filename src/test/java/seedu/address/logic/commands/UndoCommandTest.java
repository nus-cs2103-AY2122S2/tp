package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.InsurancePackagesSet;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UndoCommand}.
 */
public class UndoCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new InsurancePackagesSet());

    @Test
    public void execute_undoCommand_throwsCommandException() {
        UndoCommand undoCommand = new UndoCommand();

        assertCommandFailure(undoCommand, model, "Command cannot be undone");
    }

}
