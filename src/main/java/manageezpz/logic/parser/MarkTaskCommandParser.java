package manageezpz.logic.parser;

import manageezpz.commons.core.index.Index;
import manageezpz.logic.commands.MarkTaskCommand;
import manageezpz.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkTaskCommand object
 */
public class MarkTaskCommandParser implements Parser<MarkTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkTaskCommand
     * and returns a MarkTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkTaskCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new MarkTaskCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage() + "\n\n" + MarkTaskCommand.MESSAGE_USAGE, pe);
        }
    }
}
