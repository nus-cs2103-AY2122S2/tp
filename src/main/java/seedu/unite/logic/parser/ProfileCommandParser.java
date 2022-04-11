package seedu.unite.logic.parser;

import static seedu.unite.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.unite.commons.core.index.Index;
import seedu.unite.logic.commands.ProfileCommand;
import seedu.unite.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ProfileCommand object
 */
public class ProfileCommandParser implements Parser<ProfileCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ProfileCommand
     * and returns a ProfileCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ProfileCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ProfileCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ProfileCommand.MESSAGE_USAGE), pe);
        }
    }
}
