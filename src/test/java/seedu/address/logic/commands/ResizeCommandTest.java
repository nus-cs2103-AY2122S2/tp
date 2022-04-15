package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ResizeCommand.MESSAGE_RESIZE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Contains integration tests (interaction with the Model) for {@code RedoCommand}.
 */
public class ResizeCommandTest {

    private final Model model = new ModelManager();
    private final Model expectedModel = new ModelManager();

    @Test
    public void execute_resize_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_RESIZE, false,
                false, false, false, true);
        assertCommandSuccess(new ResizeCommand(1.0), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void test_helpCommandWordIsCorrect() {
        assertEquals(ResizeCommand.COMMAND_WORD, "resize");
    }
}
