package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPARE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;

import java.util.stream.Stream;

import seedu.address.logic.commands.SortBuyerCommand;
import seedu.address.logic.parser.exceptions.ParseException;




public class SortBuyerCommandParser implements Parser<SortBuyerCommand> {
    @Override
    public SortBuyerCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_COMPARE, PREFIX_ORDER);
        String comparedItem = "";
        String order = "";

        if (!arePrefixesPresent(argMultimap, PREFIX_COMPARE, PREFIX_ORDER)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortBuyerCommand.MESSAGE_USAGE));
        }
        comparedItem = argMultimap.getValue(PREFIX_COMPARE).get();

        if (comparedItem.equals("")) {
            throw new ParseException(SortBuyerCommand.MESSAGE_MISSING_ARGUMENTS);
        }

        order = argMultimap.getValue(PREFIX_ORDER).get();


        if (order.equals("")) {
            throw new ParseException(SortBuyerCommand.MESSAGE_MISSING_ORDER);
        }

        if (!comparedItem.equals("name") && !comparedItem.equals("time")) {
            throw new ParseException(SortBuyerCommand.MESSAGE_NOT_SORTABLE);
        }

        if (!order.equals("asc") && !order.equals("desc")) {
            throw new ParseException(SortBuyerCommand.MESSAGE_INCORRECT_ORDER);
        }
        return new SortBuyerCommand(comparedItem, order);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
