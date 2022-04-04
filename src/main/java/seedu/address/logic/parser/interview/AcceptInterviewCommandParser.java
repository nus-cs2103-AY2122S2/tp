package seedu.address.logic.parser.interview;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.interview.AcceptInterviewCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AcceptInterviewCommand object
 */
public class AcceptInterviewCommandParser implements Parser<AcceptInterviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AcceptInterviewCommand
     * and returns a AcceptInterviewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AcceptInterviewCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new AcceptInterviewCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AcceptInterviewCommand.MESSAGE_USAGE), pe);
        }
    }
}
