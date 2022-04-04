package manageezpz.logic.parser;

import manageezpz.commons.core.index.Index;
import manageezpz.logic.commands.UnmarkTaskCommand;
import manageezpz.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnmarkTaskCommand object
 */
public class UnmarkTaskCommandParser implements Parser<UnmarkTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnmarkTaskCommand
     * and returns a UnmarkTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnmarkTaskCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnmarkTaskCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage() + "\n\n" + UnmarkTaskCommand.MESSAGE_USAGE, pe);
        }
    }
}
