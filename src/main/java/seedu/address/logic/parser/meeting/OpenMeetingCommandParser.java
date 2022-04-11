package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meeting.OpenMeetingCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new OpenMeetingCommand object.
 */
public class OpenMeetingCommandParser implements Parser<OpenMeetingCommand> {

    /**
     * Parses the given string {@code String} of arguments in the context of the OpenMeetingCommand
     * and returns a OpenMeetingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public OpenMeetingCommand parse(String args) throws ParseException {
        try {
            final Index index = ParserUtil.parseIndex(args);
            return new OpenMeetingCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, OpenMeetingCommand.MESSAGE_USAGE), pe);
        }
    }
}
