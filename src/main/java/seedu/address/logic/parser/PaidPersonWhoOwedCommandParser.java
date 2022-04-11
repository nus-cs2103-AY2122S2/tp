package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PaidPersonWhoOwedCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class PaidPersonWhoOwedCommandParser implements Parser<PaidPersonWhoOwedCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PaidPersonWhoOwedCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new PaidPersonWhoOwedCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PaidPersonWhoOwedCommand.MESSAGE_USAGE), pe);
        }
    }
}
