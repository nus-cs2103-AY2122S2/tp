package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CANCEL_MEETING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TIME;

import java.util.stream.Stream;

import seedu.address.logic.commands.MeetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.MeetingDate;
import seedu.address.model.person.MeetingTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.ScheduledMeeting;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class MeetCommandParser implements Parser<MeetCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MeetCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MEETING_DATE, PREFIX_MEETING_TIME, PREFIX_CANCEL_MEETING);

        boolean isClearPresent = arePrefixesPresent(argMultimap, PREFIX_CANCEL_MEETING);
        boolean isDatePresent = arePrefixesPresent(argMultimap, PREFIX_MEETING_DATE);
        boolean isTimePresent = arePrefixesPresent(argMultimap, PREFIX_MEETING_TIME);

        checkInput(argMultimap, isClearPresent, isDatePresent, isTimePresent);

        Name name;

        try {
            name = ParserUtil.parseName(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeetCommand.MESSAGE_USAGE), pe);
        }

        ScheduledMeeting newMeeting;

        newMeeting = getScheduledMeeting(argMultimap, isClearPresent);

        return new MeetCommand(name, newMeeting);
    }

    /**
     * Creates the ScheduledMeeting object according to input.
     */
    private ScheduledMeeting getScheduledMeeting(ArgumentMultimap argMultimap, boolean isClearPresent)
            throws ParseException {
        ScheduledMeeting newMeeting;
        if (isClearPresent) {
            newMeeting = new ScheduledMeeting();
        } else {
            MeetingDate date = ParserUtil.parseMeetingDate(argMultimap.getValue(PREFIX_MEETING_DATE).get());
            MeetingTime time = ParserUtil.parseMeetingTime(argMultimap.getValue(PREFIX_MEETING_TIME).get());
            newMeeting = new ScheduledMeeting(date, time);
        }
        return newMeeting;
    }

    /**
     * Checks the input against the provided booleans to ensure that input is in the correct format.
     */
    private void checkInput(ArgumentMultimap argMultimap, boolean isClearPresent,
                            boolean isDatePresent, boolean isTimePresent) throws ParseException {
        if (isClearPresent && (isDatePresent || isTimePresent)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeetCommand.MESSAGE_USAGE));
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_MEETING_DATE, PREFIX_MEETING_TIME)
                && !isClearPresent) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeetCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
