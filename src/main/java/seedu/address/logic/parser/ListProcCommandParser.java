package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListProcCommand;
import seedu.address.logic.parser.exceptions.ParseException;


public class ListProcCommandParser implements Parser<ListProcCommand> {
    @Override
    public ListProcCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ListProcCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListProcCommand.MESSAGE_USAGE), pe);
        }
    }
}
