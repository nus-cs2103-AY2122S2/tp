package woofareyou.logic.parser;

import woofareyou.commons.core.Messages;
import woofareyou.logic.commands.SortCommand;
import woofareyou.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        Boolean isValidSortParameter = SortUtil.isValidSortParameter(trimmedArgs);
        if (!isValidSortParameter) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        return new SortCommand(trimmedArgs);
    }
}
