package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CopyEmailCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class CopyEmailCommandParser implements Parser<CopyEmailCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CopyEmailCommand
     * and returns a CopyEmailCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CopyEmailCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new CopyEmailCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyEmailCommand.MESSAGE_USAGE), pe);
        }
    }
}
