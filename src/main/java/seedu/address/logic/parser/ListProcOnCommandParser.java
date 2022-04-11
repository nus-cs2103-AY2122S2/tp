package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.datewithouttime.DateWithoutTime;
import seedu.address.logic.commands.ListProcOnCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListProcOnCommandParser implements Parser<ListProcOnCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListProcOnCommand
     * and returns a ListProcOnCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListProcOnCommand parse(String args) throws ParseException {
        try {
            DateWithoutTime date = ParserUtil.parseDateWithoutTime(args);
            return new ListProcOnCommand(date);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DateWithoutTime.MESSAGE_CONSTRAINTS), pe);
        }
    }
}
