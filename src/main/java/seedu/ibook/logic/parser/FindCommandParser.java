package seedu.ibook.logic.parser;

import static seedu.ibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_PRICE;

import seedu.ibook.logic.commands.FindCommand;
import seedu.ibook.logic.parser.exceptions.ParseException;
import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.Description;
import seedu.ibook.model.product.Name;
import seedu.ibook.model.product.Price;
import seedu.ibook.model.product.Product;
import seedu.ibook.model.product.ProductFulfillsFiltersPredicate;
import seedu.ibook.model.product.item.ExpiryDate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_CATEGORY, PREFIX_EXPIRY_DATE, PREFIX_DESCRIPTION,
                        PREFIX_PRICE);
        Name name;
        Category category;
        ExpiryDate expiryDate;
        Description description;
        Price price;

        int wildCard = 5;

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        } else {
            name = Name.WILDNAME;
            wildCard--;
        }

        if (argMultimap.getValue(PREFIX_CATEGORY).isPresent()) {
            category = ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get());
        } else {
            category = Category.WILDCATEGORY;
            wildCard--;
        }

        if (argMultimap.getValue(PREFIX_EXPIRY_DATE).isPresent()) {
            expiryDate = ParserUtil.parseExpiryDate(argMultimap.getValue(PREFIX_EXPIRY_DATE).get());
        } else {
            expiryDate = ExpiryDate.WILDEXPIRYDATE;
            wildCard--;
        }

        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        } else {
            description = Description.WILDDESCRIPTION;
            wildCard--;
        }

        if (argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            price = ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get());
        } else {
            price = Price.WILDPRICE;
            wildCard--;
        }

        if (wildCard == 0) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE)
            );
        }

        return new FindCommand(
                new ProductFulfillsFiltersPredicate(new Product(name, category, expiryDate, description, price)));
    }

}
