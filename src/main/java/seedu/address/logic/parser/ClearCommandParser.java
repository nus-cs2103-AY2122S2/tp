package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ClearCommandParser implements Parser<ClearCommand> {
    @Override
    public ClearCommand parse(String args) throws ParseException {
        if (args.trim().isEmpty()) {
            return new ClearCommand();
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        }
    }
}
