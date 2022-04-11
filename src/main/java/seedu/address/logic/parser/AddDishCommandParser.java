package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddDishCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.dish.Dish;
import seedu.address.model.dish.NameDish;
import seedu.address.model.dish.PriceDish;

/**
 * Parses input arguments and creates a new AddDishCommand object
 */
public class AddDishCommandParser implements Parser<AddDishCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddDishCommand
     * and returns an AddDishCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddDishCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PRICE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PRICE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDishCommand.MESSAGE_USAGE));
        }

        NameDish name = ParserUtil.parseDishName(argMultimap.getValue(PREFIX_NAME).get());
        PriceDish price = ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get());

        Dish dish = new Dish(name, price);

        return new AddDishCommand(dish);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

