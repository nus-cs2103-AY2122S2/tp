package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.FocusCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class FocusCommandParser implements Parser<FocusCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FocusCommand
     * and returns a FocusCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FocusCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new FocusCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FocusCommand.MESSAGE_USAGE), pe);
        }
    }

}
