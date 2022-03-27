package seedu.trackbeau.logic.parser.booking;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_SERVICE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_STARTTIME;

import seedu.trackbeau.logic.commands.booking.AddBookingCommand;
import seedu.trackbeau.logic.parser.ArgumentMultimap;
import seedu.trackbeau.logic.parser.ArgumentTokenizer;
import seedu.trackbeau.logic.parser.Parser;
import seedu.trackbeau.logic.parser.ParserUtil;
import seedu.trackbeau.logic.parser.exceptions.ParseException;
import seedu.trackbeau.model.booking.Booking;
import seedu.trackbeau.model.booking.BookingDateTime;
import seedu.trackbeau.model.customer.Name;
import seedu.trackbeau.model.customer.Phone;
import seedu.trackbeau.model.service.ServiceName;

/**
 * Parses input arguments and creates a new AddBookingCommand object
 */
public class AddBookingCommandParser implements Parser<AddBookingCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddBookingCommand
     * and returns an AddCustomerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddBookingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_SERVICE, PREFIX_STARTTIME);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_SERVICE, PREFIX_STARTTIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBookingCommand.MESSAGE_USAGE));
        }

        Name customerName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone customerPhone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        ServiceName serviceName = ParserUtil.parseServiceName(argMultimap.getValue(PREFIX_SERVICE).get());
        BookingDateTime bookingDateTime = ParserUtil.parseStartTime(argMultimap.getValue(PREFIX_STARTTIME).get());

        Booking booking = new Booking(customerName, customerPhone, serviceName, bookingDateTime);

        return new AddBookingCommand(booking);
    }
}
