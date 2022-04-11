package seedu.ibook.logic.parser;

import static seedu.ibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.ibook.logic.parser.ParserUtil.parseExpiryDate;
import static seedu.ibook.logic.parser.ParserUtil.parseIndex;
import static seedu.ibook.logic.parser.ParserUtil.parseQuantity;

import java.util.stream.Stream;

import seedu.ibook.commons.core.index.Index;
import seedu.ibook.logic.commands.item.AddItemCommand;
import seedu.ibook.logic.parser.exceptions.ParseException;
import seedu.ibook.model.item.ExpiryDate;
import seedu.ibook.model.item.ItemDescriptor;
import seedu.ibook.model.item.Quantity;

public class AddItemCommandParser implements Parser<AddItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddItemCommand
     * and returns an AddItemCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddItemCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EXPIRY_DATE, PREFIX_QUANTITY);

        if (!arePrefixesPresent(argMultimap, PREFIX_EXPIRY_DATE, PREFIX_QUANTITY)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItemCommand.MESSAGE_USAGE));
        }

        Index index = parseIndex(argMultimap.getPreamble());

        ExpiryDate expiry = parseExpiryDate(argMultimap.getValue(PREFIX_EXPIRY_DATE).get());
        Quantity quantity = parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get());

        ItemDescriptor itemDescriptor = new ItemDescriptor(expiry, quantity);

        return new AddItemCommand(index, itemDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
