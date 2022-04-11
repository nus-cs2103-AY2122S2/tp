package seedu.address.logic.parser.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATETIME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meeting.EditMeetingCommand;
import seedu.address.logic.commands.meeting.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditMeetingCommand object
 */
public class EditMeetingCommandParser implements Parser<EditMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_START_DATETIME, PREFIX_END_DATETIME, PREFIX_LABEL);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditMeetingCommand.MESSAGE_USAGE), pe);
        }

        EditMeetingDescriptor editMeetingDescriptor = new EditMeetingDescriptor();
        if (argMultimap.getValue(PREFIX_START_DATETIME).isPresent()) {
            editMeetingDescriptor.setStartDateTime(
                    ParserUtil.parseDateTime(
                            argMultimap.getValue(PREFIX_START_DATETIME).get()));
        }
        if (argMultimap.getValue(PREFIX_END_DATETIME).isPresent()) {
            editMeetingDescriptor.setEndDateTime(
                    ParserUtil.parseDateTime(
                            argMultimap.getValue(PREFIX_END_DATETIME).get()));
        }
        if (argMultimap.getValue(PREFIX_LABEL).isPresent()) {
            String label;
            if (argMultimap.getValue(PREFIX_LABEL).isPresent()) {
                label = argMultimap.getValue(PREFIX_LABEL).get();
            } else {
                label = "";
            }
            editMeetingDescriptor.setLabel(label);
        }

        if (!editMeetingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditMeetingCommand.MESSAGE_NOT_EDITED);
        }

        return new EditMeetingCommand(index, editMeetingDescriptor);
    }
}
