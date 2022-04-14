package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_emptyAddressBook_success() {
        model = new ModelManager();
        expectedModel = new ModelManager();

        ClearCommand clearCommand = new ClearCommand();
        CommandResult expectedCommandResult =
                new CommandResult(ClearCommand.MESSAGE_REQUEST_USER_CONFIRMATION, true);

        assertCommandSuccess(clearCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        ClearCommand clearCommand = new ClearCommand();
        CommandResult expectedCommandResult =
            new CommandResult(ClearCommand.MESSAGE_REQUEST_USER_CONFIRMATION, true);

        assertCommandSuccess(clearCommand, model, expectedCommandResult, expectedModel);
    }
}
