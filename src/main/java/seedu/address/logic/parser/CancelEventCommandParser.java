package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CancelEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CancelEventCommand object
 */
public class CancelEventCommandParser implements Parser<CancelEventCommand> {

    /**
     * Parses the give {@code String} of argument sin the context of the CancelEventCommand
     * and returns a CancelEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CancelEventCommand parse(String args) throws ParseException {
        try {
            Index[] indexes = ParserUtil.parseIndexes(args);
            return new CancelEventCommand(indexes);
        } catch (ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CancelEventCommand.MESSAGE_USAGE), e);
        }
    }
}
