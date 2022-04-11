package seedu.ibook.logic.parser;

import static seedu.ibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_DISCOUNT_RATE;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_DISCOUNT_START;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.ibook.logic.parser.ParserUtil.parseCategory;
import static seedu.ibook.logic.parser.ParserUtil.parseDescription;
import static seedu.ibook.logic.parser.ParserUtil.parseDiscountRate;
import static seedu.ibook.logic.parser.ParserUtil.parseDiscountStart;
import static seedu.ibook.logic.parser.ParserUtil.parseName;
import static seedu.ibook.logic.parser.ParserUtil.parsePrice;

import java.util.stream.Stream;

import seedu.ibook.logic.commands.product.AddCommand;
import seedu.ibook.logic.parser.exceptions.ParseException;
import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.Description;
import seedu.ibook.model.product.DiscountRate;
import seedu.ibook.model.product.DiscountStart;
import seedu.ibook.model.product.Name;
import seedu.ibook.model.product.Price;
import seedu.ibook.model.product.Product;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_CATEGORY, PREFIX_DESCRIPTION,
                PREFIX_PRICE, PREFIX_DISCOUNT_RATE, PREFIX_DISCOUNT_START);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PRICE)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        // Required fields
        Name name = parseName(argMultimap.getValue(PREFIX_NAME).get());
        Price price = parsePrice(argMultimap.getValue(PREFIX_PRICE).get());

        // Optional fields
        Category category = parseCategory(argMultimap.getValue(PREFIX_CATEGORY)
                .orElse(Category.DEFAULT_CATEGORY));
        Description description = parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION)
                .orElse(Description.DEFAULT_DESCRIPTION));
        DiscountRate discountRate =
                parseDiscountRate(argMultimap.getValue(PREFIX_DISCOUNT_RATE)
                .orElse(DiscountRate.DEFAULT_DISCOUNT_RATE));
        DiscountStart discountStart =
                parseDiscountStart(argMultimap.getValue(PREFIX_DISCOUNT_START)
                .orElse(DiscountStart.DEFAULT_DISCOUNT_START));

        Product product = new Product(name, category, description, price, discountRate, discountStart);

        return new AddCommand(product);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
