package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ExitCommandParser implements Parser<ExitCommand> {
    @Override
    public ExitCommand parse(String args) throws ParseException {
        if (args.trim().isEmpty()) {
            return new ExitCommand();
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExitCommand.MESSAGE_USAGE));
        }
    }
}
