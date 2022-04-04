package seedu.address.logic.parser.interview;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.interview.FailInterviewCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class FailInterviewCommandParser implements Parser<FailInterviewCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the PassInterviewCommand
     * and returns a PassInterviewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FailInterviewCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new FailInterviewCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FailInterviewCommand.MESSAGE_USAGE), pe);
        }
    }
}
