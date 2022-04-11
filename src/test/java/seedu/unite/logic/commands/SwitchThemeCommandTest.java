package seedu.unite.logic.commands;

import static seedu.unite.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.unite.logic.commands.exceptions.CommandException;
import seedu.unite.model.Model;
import seedu.unite.model.ModelManager;
import seedu.unite.model.UserPrefs;
import seedu.unite.ui.theme.DarkTheme;
import seedu.unite.ui.theme.LightTheme;
import seedu.unite.ui.theme.Theme;

public class SwitchThemeCommandTest {

    private final Model model = new ModelManager();

    @Test
    public void execute_switchesToDarkTheme_success() throws CommandException {
        Theme darkTheme = new DarkTheme();
        SwitchThemeCommand switchThemeCommand = new SwitchThemeCommand(darkTheme);
        ModelManager expectedModel = new ModelManager(model.getUnite(), new UserPrefs());
        CommandResult expectedCommandResult = switchThemeCommand.execute(expectedModel);

        assertCommandSuccess(switchThemeCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_switchesToLightTheme_success() throws CommandException {
        Theme lightTheme = new LightTheme();
        SwitchThemeCommand switchThemeCommand = new SwitchThemeCommand(lightTheme);
        ModelManager expectedModel = new ModelManager(model.getUnite(), new UserPrefs());
        CommandResult expectedCommandResult = switchThemeCommand.execute(expectedModel);

        assertCommandSuccess(switchThemeCommand, model, expectedCommandResult, expectedModel);
    }
}
