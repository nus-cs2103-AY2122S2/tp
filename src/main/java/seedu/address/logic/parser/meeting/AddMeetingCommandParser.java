package seedu.address.logic.parser.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PARAMETERS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATETIME;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meeting.AddMeetingCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.Meeting;

/**
 * Parses input arguments and creates a new AddMeetingCommand object
 */
public class AddMeetingCommandParser implements Parser<AddMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddMeetingCommand
     * and returns an AddMeetingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_START_DATETIME, PREFIX_END_DATETIME, PREFIX_LABEL);

        if (!arePrefixesPresent(argMultimap, PREFIX_START_DATETIME, PREFIX_END_DATETIME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddMeetingCommand.MESSAGE_USAGE),
                    pe);
        }

        LocalDateTime startDateTime;
        LocalDateTime endDateTime;
        try {
            startDateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_START_DATETIME).get());
            endDateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_END_DATETIME).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    "Reason:\n" + pe.getMessage() + "\n" + AddMeetingCommand.MESSAGE_USAGE));
        }

        String label;
        if (argMultimap.getValue(PREFIX_LABEL).isPresent()) {
            label = argMultimap.getValue(PREFIX_LABEL).get();
        } else {
            label = "";
        }

        if (!Meeting.isValidMeeting(startDateTime, endDateTime)) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_PARAMETERS,
                    AddMeetingCommand.MESSAGE_INVALID_DATETIME));
        }

        return new AddMeetingCommand(index, startDateTime, endDateTime, label);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
