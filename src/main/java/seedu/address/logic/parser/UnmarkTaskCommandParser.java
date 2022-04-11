package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnmarkTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

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
            throw new ParseException(MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }
}
