package seedu.trackbeau.logic.parser.booking;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_CUSTOMER;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_SERVICE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_STARTTIME;

import java.time.LocalDateTime;

import seedu.trackbeau.logic.commands.booking.AddBookingCommand;
import seedu.trackbeau.logic.parser.ArgumentMultimap;
import seedu.trackbeau.logic.parser.ArgumentTokenizer;
import seedu.trackbeau.logic.parser.Parser;
import seedu.trackbeau.logic.parser.ParserUtil;
import seedu.trackbeau.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddCustomerCommand object
 */
public class AddBookingCommandParser implements Parser<AddBookingCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddCustomerCommand
     * and returns an AddCustomerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddBookingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CUSTOMER, PREFIX_SERVICE, PREFIX_STARTTIME);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_CUSTOMER, PREFIX_STARTTIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBookingCommand.MESSAGE_USAGE));
        }

        Integer customerID = ParserUtil.parseCustomerID(argMultimap.getValue(PREFIX_CUSTOMER).get());
        // Service service = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_SERVICE).get());
        LocalDateTime startTime = ParserUtil.parseStartTime(argMultimap.getValue(PREFIX_STARTTIME).get());

        return new AddBookingCommand(customerID, startTime);
    }
}
