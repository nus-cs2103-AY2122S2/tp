package seedu.ibook.logic.parser;

import static seedu.ibook.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_PRICE;

import java.util.ArrayList;
import java.util.List;

import seedu.ibook.logic.commands.FindCommand;
import seedu.ibook.logic.parser.exceptions.ParseException;
import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.Description;
import seedu.ibook.model.product.Name;
import seedu.ibook.model.product.Price;
import seedu.ibook.model.product.filters.AttributeFilter;
import seedu.ibook.model.product.filters.CategoryFilter;
import seedu.ibook.model.product.filters.DescriptionFilter;
import seedu.ibook.model.product.filters.NameFilter;
import seedu.ibook.model.product.filters.PriceFilter;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_CATEGORY, PREFIX_DESCRIPTION, PREFIX_PRICE);

        Name name;
        Category category;
        Description description;
        Price price;

        List<AttributeFilter> filterList = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            filterList.add(new NameFilter(name));
        }

        if (argMultimap.getValue(PREFIX_CATEGORY).isPresent()) {
            category = ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get());
            filterList.add(new CategoryFilter(category));
        }

        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
            filterList.add(new DescriptionFilter(description));
        }

        if (argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            price = ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get());
            filterList.add(new PriceFilter(price));
        }

        return new FindCommand(filterList);
    }

}
