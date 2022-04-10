package seedu.address.logic.parser.person;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.person.SetUserCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SetUserCommand object
 */
public class SetUserCommandParser implements Parser<SetUserCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SetUserCommand
     * and returns a SetUserCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetUserCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new SetUserCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetUserCommand.MESSAGE_USAGE), pe);
        }
    }

}