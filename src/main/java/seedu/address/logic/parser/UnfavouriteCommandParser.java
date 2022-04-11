package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnfavouriteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UnfavouriteCommandParser implements Parser<UnfavouriteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code FavouriteCommand}
     * and returns a {@code FavouriteCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnfavouriteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnfavouriteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnfavouriteCommand.MESSAGE_USAGE), pe);
        }
    }
}
