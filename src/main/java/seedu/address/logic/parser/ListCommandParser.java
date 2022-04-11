package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.TYPE_INTERVIEW;
import static seedu.address.logic.parser.CliSyntax.TYPE_PERSON;
import static seedu.address.logic.parser.CliSyntax.TYPE_TASK;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListInterviewCommand;
import seedu.address.logic.commands.ListPersonCommand;
import seedu.address.logic.commands.ListTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parse ListCommand
     */
    public ListCommand parse(String args) throws ParseException {
        String type = ArgumentTokenizer.getType(args.trim());
        String removedType = args.trim().substring(3);

        if (removedType.length() != 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        switch (type) {
        case TYPE_PERSON:
            return new ListPersonCommand();
        case TYPE_INTERVIEW:
            return new ListInterviewCommand();
        case TYPE_TASK:
            return new ListTaskCommand();
        default:
            return null;
        }
    }
}
