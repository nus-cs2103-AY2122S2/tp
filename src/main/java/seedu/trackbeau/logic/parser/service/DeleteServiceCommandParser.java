package seedu.trackbeau.logic.parser.service;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.trackbeau.logic.commands.service.DeleteServiceCommand;
import seedu.trackbeau.logic.parser.Parser;
import seedu.trackbeau.logic.parser.ParserUtil;
import seedu.trackbeau.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteServiceCommand object
 */
public class DeleteServiceCommandParser implements Parser<DeleteServiceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteServiceCommand
     * and returns a DeleteServiceCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteServiceCommand parse(String args) throws ParseException {
        try {
            return new DeleteServiceCommand(ParserUtil.parseIndexes(args));
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteServiceCommand.MESSAGE_USAGE), pe);
        }
    }
}
