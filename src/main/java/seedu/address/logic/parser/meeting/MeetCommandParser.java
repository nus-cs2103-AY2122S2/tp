package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGENDA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDEES_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meeting.MeetCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.Agenda;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingPlace;
import seedu.address.model.meeting.MeetingTime;

public class MeetCommandParser implements Parser<MeetCommand> {
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if all prefixes are present in the multimap.
     * @param argMap argument multimap
     * @param prefixes prefixes should contain with/, for/, in/, on/
     * @return true if all prefixes are present in the multimap
     */
    private static boolean checkAllPrefixPresent(ArgumentMultimap argMap, Prefix... prefixes) {
        for (Prefix prefix : prefixes) {
            if (!arePrefixesPresent(argMap, prefix)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Parses a string of argument into a MeetCommand. The information pertaining to a Meeting
     * is read and extracted out by the ParserUtil class and a Meeting object is created
     * from the extracted information.
     * Note that this means it is not allowed for indexes to
     *
     * @param args arguments for a meeting
     * @return MeetCommand
     * @throws ParseException if there are no indexes or agenda in the arguments
     */
    public MeetCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer
                        .tokenize(args,
                                PREFIX_ATTENDEES_INDEX, PREFIX_AGENDA, PREFIX_PLACE,
                                PREFIX_TIME);
        if (!checkAllPrefixPresent(argMultimap, PREFIX_AGENDA, PREFIX_TIME, PREFIX_PLACE,
                PREFIX_ATTENDEES_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MeetCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_ATTENDEES_INDEX).get().equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MeetCommand.INDEX_CANNOT_BE_EMPTY_MESSAGE));
        }

        if (argMultimap.getValue(PREFIX_AGENDA).get().equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MeetCommand.AGENDA_CANNOT_BE_EMPTY_MESSAGE));
        }

        Set<Index> indexes = ParserUtil.parseAttendees(argMultimap.getValue(PREFIX_ATTENDEES_INDEX).get());
        //return a Hashset of indexes and not a set of person since the model is only accessible inside the MeetCommand.
        //Inside the meet command is where the handling of out of bounds exceptions happens.
        Agenda agenda = ParserUtil.parseAgenda(argMultimap.getValue(PREFIX_AGENDA).get());
        MeetingPlace meetingPlace = ParserUtil.parseMeetingPlace(argMultimap.getValue(PREFIX_PLACE).get());
        MeetingTime meetingTime = ParserUtil.parseMeetingTime(argMultimap.getValue(PREFIX_TIME).get());

        Meeting createdMeeting = new Meeting(agenda, meetingPlace, meetingTime, indexes, new HashSet<>());
        return new MeetCommand(createdMeeting);
    }

}
