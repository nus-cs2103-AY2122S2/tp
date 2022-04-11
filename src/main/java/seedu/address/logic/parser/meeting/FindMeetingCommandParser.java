package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.meeting.FindMeetingCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.MeetingContainsAllKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindMeetingCommand object.
 */
public class FindMeetingCommandParser implements Parser<FindMeetingCommand> {

    /**
     * Parses the give {@code String} of arguments in the context of the FindMeetingCommand and
     * returns a FindMeetingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public FindMeetingCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMeetingCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindMeetingCommand(new MeetingContainsAllKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
