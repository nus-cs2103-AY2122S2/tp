package seedu.unite.logic.parser;

import static seedu.unite.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.unite.logic.commands.ClearCommand;
import seedu.unite.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ClearCommand object
 */
public class ClearCommandParser implements Parser<ClearCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ClearCommand
     * and returns an ClearCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearCommand parse(String args) throws ParseException {
        if (args.equals(ClearCommand.COMMAND_WORD)) {
            return new ClearCommand();
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ClearCommand.MESSAGE_USAGE));
        }
    }
}
