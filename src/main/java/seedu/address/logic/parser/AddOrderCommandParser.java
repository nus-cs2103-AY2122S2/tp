package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLLECTION_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERYDATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.CollectionType;
import seedu.address.model.order.DeliveryDateTime;
import seedu.address.model.order.Details;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;

public class AddOrderCommandParser implements Parser<AddOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddOrderCommand
     * and returns an AddOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddOrderCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PHONE, PREFIX_REMARK,
                        PREFIX_DETAILS, PREFIX_DELIVERYDATETIME, PREFIX_COLLECTION_TYPE);

        if (!arePrefixesPresent(argMultimap, PREFIX_PHONE, PREFIX_DETAILS,
                PREFIX_DELIVERYDATETIME, PREFIX_COLLECTION_TYPE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE));
        }

        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Remark remark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).orElse(""));
        List<Details> details = ParserUtil.parseDetails(argMultimap.getAllValues(PREFIX_DETAILS));
        DeliveryDateTime deliveryDateTime = ParserUtil.parseDeliveryDateTime(argMultimap
                .getValue(PREFIX_DELIVERYDATETIME).get());
        CollectionType collectionType = ParserUtil.parseCollectionType(
                argMultimap.getValue(PREFIX_COLLECTION_TYPE).get());

        return new AddOrderCommand(phone, remark, details, deliveryDateTime, collectionType);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
