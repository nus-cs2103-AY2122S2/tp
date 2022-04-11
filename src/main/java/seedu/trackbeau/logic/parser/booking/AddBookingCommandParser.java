package seedu.trackbeau.logic.parser.booking;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_CUSTOMERINDEX;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_SERVICEINDEX;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_STARTTIME;

import seedu.trackbeau.logic.commands.booking.AddBookingCommand;
import seedu.trackbeau.logic.parser.ArgumentMultimap;
import seedu.trackbeau.logic.parser.ArgumentTokenizer;
import seedu.trackbeau.logic.parser.Parser;
import seedu.trackbeau.logic.parser.ParserUtil;
import seedu.trackbeau.logic.parser.exceptions.ParseException;
import seedu.trackbeau.model.booking.BookingDateTime;

/**
 * Parses input arguments and creates a new AddBookingCommand object
 */
public class AddBookingCommandParser implements Parser<AddBookingCommand> {
    public static final String EMPTY_FEEDBACK_TYPE = "No data";
    /**
     * Parses the given {@code String} of arguments in the context of the AddBookingCommand
     * and returns an AddCustomerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddBookingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CUSTOMERINDEX, PREFIX_SERVICEINDEX, PREFIX_STARTTIME);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_CUSTOMERINDEX, PREFIX_SERVICEINDEX, PREFIX_STARTTIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddBookingCommand.MESSAGE_USAGE));
        }

        Integer customerIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CUSTOMERINDEX)
                .get()).getZeroBased();
        Integer serviceIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_SERVICEINDEX)
                .get()).getZeroBased();
        BookingDateTime bookingDateTime = ParserUtil.parseStartTime(argMultimap.getValue(PREFIX_STARTTIME).get());

        return new AddBookingCommand(customerIndex, serviceIndex, bookingDateTime);
    }
}
