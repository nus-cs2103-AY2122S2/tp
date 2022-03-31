package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPARE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;

import java.util.NoSuchElementException;

import seedu.address.logic.commands.SortSellerCommand;
import seedu.address.logic.parser.exceptions.ParseException;




public class SortSellerCommandParser implements Parser<SortSellerCommand> {
    @Override
    public SortSellerCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_COMPARE, PREFIX_ORDER);
        String comparedItem = "";
        String order = "";
        try {
            comparedItem = argMultimap.getValue(PREFIX_COMPARE).get();
        } catch (NoSuchElementException e) {
            throw new ParseException(SortSellerCommand.MESSAGE_MISSING_ARGUMENTS);
        }
        if (comparedItem.equals("")) {
            throw new ParseException(SortSellerCommand.MESSAGE_MISSING_ARGUMENTS);
        }
        if (!comparedItem.equals("name") && !comparedItem.equals("time")) {
            throw new ParseException(SortSellerCommand.MESSAGE_NOT_SORTABLE);
        }
        try {
            order = argMultimap.getValue(PREFIX_ORDER).get();
        } catch (NoSuchElementException e) {
            throw new ParseException(SortSellerCommand.MESSAGE_MISSING_ORDER);
        }

        if (order.equals("")) {
            throw new ParseException(SortSellerCommand.MESSAGE_MISSING_ARGUMENTS);
        }

        if (!order.equals("asc") && !order.equals("desc")) {
            throw new ParseException(SortSellerCommand.MESSAGE_INCORRECT_ORDER);
        }
        return new SortSellerCommand(comparedItem, order);
    }
}
