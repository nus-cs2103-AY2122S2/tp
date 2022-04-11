package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_THEME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ThemeCommand;


public class ThemeCommandParserTest {
    private ThemeCommandParser parser = new ThemeCommandParser();

    @Test
    public void parse_changeTheme_success() {
        // change to dark theme
        assertParseSuccess(parser, ThemeCommand.COMMAND_WORD + " " + PREFIX_THEME + "dark",
                new ThemeCommand(true, false));

        // change to light theme
        assertParseSuccess(parser, ThemeCommand.COMMAND_WORD + " " + PREFIX_THEME + "light",
                new ThemeCommand(false, true));
    }

    @Test
    public void parse_invalidTheme_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ThemeCommand.MESSAGE_EDIT_THEME_INVALID);

        // invalid theme
        assertParseFailure(parser, ThemeCommand.COMMAND_WORD + " " + PREFIX_THEME + " pink",
                expectedMessage);
    }

    @Test
    public void parse_missingTheme_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE);

        // missing prefix
        assertParseFailure(parser, ThemeCommand.COMMAND_WORD + " pink",
                expectedMessage);
    }
}
