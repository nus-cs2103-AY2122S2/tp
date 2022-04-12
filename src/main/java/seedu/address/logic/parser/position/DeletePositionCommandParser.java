package seedu.address.logic.parser.position;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.position.DeletePositionCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and Creates a new DeletePositionCommand object
 */
public class DeletePositionCommandParser implements Parser<DeletePositionCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeletePositionCommand
     * and returns a DeletePositionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeletePositionCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeletePositionCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePositionCommand.MESSAGE_USAGE), pe);
        }
    }
}
