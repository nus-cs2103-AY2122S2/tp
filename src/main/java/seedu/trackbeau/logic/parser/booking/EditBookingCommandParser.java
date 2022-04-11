package seedu.trackbeau.logic.parser.booking;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_CUSTOMERINDEX;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_FEEDBACK;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_SERVICEINDEX;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_STARTTIME;

import seedu.trackbeau.commons.core.index.Index;
import seedu.trackbeau.logic.commands.booking.EditBookingCommand;
import seedu.trackbeau.logic.commands.booking.EditBookingCommand.EditBookingDescriptor;
import seedu.trackbeau.logic.parser.ArgumentMultimap;
import seedu.trackbeau.logic.parser.ArgumentTokenizer;
import seedu.trackbeau.logic.parser.Parser;
import seedu.trackbeau.logic.parser.ParserUtil;
import seedu.trackbeau.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditBookingCommand object
 */
public class EditBookingCommandParser implements Parser<EditBookingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditBookingCommand
     * and returns an EditBookingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditBookingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CUSTOMERINDEX, PREFIX_SERVICEINDEX, PREFIX_STARTTIME,
                        PREFIX_FEEDBACK);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {

            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditBookingCommand.MESSAGE_USAGE), pe);
        }

        EditBookingDescriptor editBookingDescriptor = new EditBookingDescriptor();
        if (argMultimap.getValue(PREFIX_CUSTOMERINDEX).isPresent()) {
            editBookingDescriptor.setCustomerIndex(ParserUtil.parseIndex(argMultimap
                    .getValue(PREFIX_CUSTOMERINDEX).get()));
        }
        if (argMultimap.getValue(PREFIX_SERVICEINDEX).isPresent()) {
            editBookingDescriptor.setServiceIndex(ParserUtil.parseIndex(argMultimap
                    .getValue(PREFIX_SERVICEINDEX).get()));
        }
        if (argMultimap.getValue(PREFIX_STARTTIME).isPresent()) {
            editBookingDescriptor.setBookingDateTime(ParserUtil.parseStartTime(argMultimap
                    .getValue(PREFIX_STARTTIME).get()));
        }
        if (argMultimap.getValue(PREFIX_FEEDBACK).isPresent()) {
            editBookingDescriptor.setFeedback(ParserUtil.parseFeedback(argMultimap
                    .getValue(PREFIX_FEEDBACK).get()));
        }

        if (!editBookingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditBookingCommand.MESSAGE_NOT_EDITED);
        }

        return new EditBookingCommand(index, editBookingDescriptor);
    }
}
