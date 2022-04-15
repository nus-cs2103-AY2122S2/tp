package seedu.address.logic.parser.strategy;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.strategy.DeletePlayerCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeletePlayerCommand object
 */

public class DeletePlayerCommandParser implements Parser<DeletePlayerCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code DeletePlayerCommand}
     * and returns an {@code DELETEPlayerCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeletePlayerCommand parse(String args) throws ParseException {
        try {
            String[] splitArgs = args.trim().split("\\s+", 1);
            return new DeletePlayerCommand(splitArgs[0]);
        } catch (IndexOutOfBoundsException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePlayerCommand.MESSAGE_USAGE), pe);
        }
    }
}
