package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ConfirmClearCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        expectedModel = new ModelManager();
    }

    @Test
    public void execute_emptyAddressBook_success() {
        ConfirmClearCommand confirmClearCommand = new ConfirmClearCommand();
        CommandResult expectedCommandResult =
                new CommandResult(ConfirmClearCommand.MESSAGE_CLEAR_ACKNOWLEDGEMENT);

        assertCommandSuccess(confirmClearCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        ConfirmClearCommand confirmClearCommand = new ConfirmClearCommand();
        CommandResult expectedCommandResult =
                new CommandResult(ConfirmClearCommand.MESSAGE_CLEAR_ACKNOWLEDGEMENT);

        assertCommandSuccess(confirmClearCommand, model, expectedCommandResult, expectedModel);
    }
}
