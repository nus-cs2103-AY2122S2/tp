package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ThemeCommandTest {
    private Model model = new ModelManager();

    @Test
    public void execute_toDark_success() throws CommandException {
        ThemeCommand themeCommand = new ThemeCommand(true, false);
        String res = themeCommand.execute(model).getFeedbackToUser();
        assertEquals(res, ThemeCommand.MESSAGE_EDIT_THEME_DARK);
    }

    @Test
    public void execute_toLight_success() throws CommandException {
        ThemeCommand themeCommand = new ThemeCommand(false, true);
        String res = themeCommand.execute(model).getFeedbackToUser();
        assertEquals(res, ThemeCommand.MESSAGE_EDIT_THEME_LIGHT);
    }
}
