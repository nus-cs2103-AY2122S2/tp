package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ClearCommentCommand object
 */
public class ClearCommentCommandParser implements Parser<ClearCommentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ClearCommentCommand
     * and returns a ClearCommentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearCommentCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ClearCommentCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommentCommand.MESSAGE_USAGE), pe);
        }
    }
}
