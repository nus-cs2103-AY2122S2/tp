package seedu.address.logic.parser.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGENDA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDEES_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meeting.UpdateCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.UpdateMeetingDescriptor;

public class UpdateCommandParser implements Parser<UpdateCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UpdateCommand
     * and returns a UpdateCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ATTENDEES_INDEX, PREFIX_TIME,
                        PREFIX_PLACE, PREFIX_AGENDA);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE), pe);
        }
        UpdateMeetingDescriptor updateMeetingDescriptor = new UpdateMeetingDescriptor();
        if (argMultimap.getValue(PREFIX_ATTENDEES_INDEX).isPresent()) {
            updateMeetingDescriptor.setIndexes(ParserUtil
                    .parseAttendees(argMultimap.getValue(PREFIX_ATTENDEES_INDEX).get()));
        }
        if (argMultimap.getValue(PREFIX_AGENDA).isPresent()) {
            updateMeetingDescriptor.setAgenda(ParserUtil.parseAgenda(argMultimap.getValue(PREFIX_AGENDA).get()));
        }
        if (argMultimap.getValue(PREFIX_TIME).isPresent()) {
            updateMeetingDescriptor.setMeetingTime(ParserUtil
                    .parseMeetingTime(argMultimap.getValue(PREFIX_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_PLACE).isPresent()) {
            updateMeetingDescriptor.setMeetingPlace(ParserUtil
                    .parseMeetingPlace(argMultimap.getValue(PREFIX_PLACE).get()));
        }

        if (!updateMeetingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(UpdateCommand.MESSAGE_NOT_UPDATED);
        }
        return new UpdateCommand(index, updateMeetingDescriptor);
    }
}
