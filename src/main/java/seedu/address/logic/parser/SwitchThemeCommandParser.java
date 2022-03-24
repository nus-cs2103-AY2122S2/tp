package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SwitchThemeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.theme.Theme;

public class SwitchThemeCommandParser implements Parser<SwitchThemeCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SwitchThemeCommand
     * and returns an SwitchThemeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SwitchThemeCommand parse(String args) throws ParseException {
        try {
            Theme theme = ParserUtil.parseTheme(args);
            return new SwitchThemeCommand(theme);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwitchThemeCommand.MESSAGE_USAGE), pe);
        }
    }
}
