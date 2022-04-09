package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_THEME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SwitchThemeCommand;
import seedu.address.model.theme.DarkTheme;
import seedu.address.model.theme.LightTheme;
import seedu.address.model.theme.Theme;

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
