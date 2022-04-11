package seedu.unite.logic.parser;

import static seedu.unite.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.unite.commons.core.Messages.MESSAGE_INVALID_THEME;
import static seedu.unite.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.unite.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.unite.logic.commands.SwitchThemeCommand;
import seedu.unite.ui.theme.DarkTheme;
import seedu.unite.ui.theme.LightTheme;
import seedu.unite.ui.theme.Theme;

public class SwitchThemeCommandParserTest {
    private final SwitchThemeCommandParser parser = new SwitchThemeCommandParser();

    @Test
    public void parse_validTheme_returnsSwitchThemeCommand() {
        String userInputLightTheme = "light";
        Theme lightTheme = new LightTheme();
        SwitchThemeCommand expectedSwitchThemeCommandWithLightTheme = new SwitchThemeCommand(lightTheme);
        assertParseSuccess(parser, userInputLightTheme, expectedSwitchThemeCommandWithLightTheme);

        String userInputDarkTheme = "dark";
        Theme darkTheme = new DarkTheme();
        SwitchThemeCommand expectedSwitchThemeCommandWithDarkTheme = new SwitchThemeCommand(darkTheme);
        assertParseSuccess(parser, userInputDarkTheme, expectedSwitchThemeCommandWithDarkTheme);
    }

    @Test
    public void parse_invalidTheme_throwsParseException() {
        String userInputInvalidTheme = "green";
        assertParseFailure(parser, userInputInvalidTheme, MESSAGE_INVALID_THEME);
    }

    @Test
    public void parse_missingArgs_throwsParseException() {
        String userInputMissingArgs = "";
        assertParseFailure(parser, userInputMissingArgs,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwitchThemeCommand.MESSAGE_USAGE));
    }
}
