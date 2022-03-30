package seedu.address.logic.parser.interview;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.interview.RejectInterviewCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AcceptInterviewCommand object
 */
public class RejectInterviewCommandParser implements Parser<RejectInterviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RejectInterviewCommand
     * and returns a RejectInterviewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RejectInterviewCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new RejectInterviewCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RejectInterviewCommand.MESSAGE_USAGE), pe);
        }
    }
}
