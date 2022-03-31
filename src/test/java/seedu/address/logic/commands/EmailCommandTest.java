package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.EmailCommand.SHOWING_EMAIL_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class EmailCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_email_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_EMAIL_MESSAGE, false,
                false, false, true, false);
        assertCommandSuccess(new EmailCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void test_helpCommandWordIsCorrect() {
        assertTrue(EmailCommand.COMMAND_WORD.equals("email"));
    }
}
