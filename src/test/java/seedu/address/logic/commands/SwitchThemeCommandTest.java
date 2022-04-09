package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.theme.DarkTheme;
import seedu.address.model.theme.LightTheme;
import seedu.address.model.theme.Theme;

public class SwitchThemeCommandTest {

    private final Model model = new ModelManager();

    @Test
    public void execute_switchesToDarkTheme_success() throws CommandException {
        Theme darkTheme = new DarkTheme();
        SwitchThemeCommand switchThemeCommand = new SwitchThemeCommand(darkTheme);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        CommandResult expectedCommandResult = switchThemeCommand.execute(expectedModel);

        assertCommandSuccess(switchThemeCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_switchesToLightTheme_success() throws CommandException {
        Theme lightTheme = new LightTheme();
        SwitchThemeCommand switchThemeCommand = new SwitchThemeCommand(lightTheme);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        CommandResult expectedCommandResult = switchThemeCommand.execute(expectedModel);

        assertCommandSuccess(switchThemeCommand, model, expectedCommandResult, expectedModel);
    }
}
