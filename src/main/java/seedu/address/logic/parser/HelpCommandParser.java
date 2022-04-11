package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class HelpCommandParser implements Parser<HelpCommand> {
    @Override
    public HelpCommand parse(String args) throws ParseException {
        if (args.trim().isEmpty()) {
            return new HelpCommand();
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
    }
}
