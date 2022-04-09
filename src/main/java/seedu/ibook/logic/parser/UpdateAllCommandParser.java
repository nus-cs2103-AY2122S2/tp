package seedu.ibook.logic.parser;

import static java.util.Objects.requireNonNull;
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

import seedu.ibook.logic.commands.product.UpdateAllCommand;
import seedu.ibook.logic.commands.product.UpdateCommand.UpdateProductDescriptor;
import seedu.ibook.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UpdateAllCommand object.
 */
public class UpdateAllCommandParser implements Parser<UpdateAllCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UpdateAllCommand
     * and returns an UpdateAllCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    @Override
    public UpdateAllCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_CATEGORY,
                PREFIX_PRICE, PREFIX_DESCRIPTION, PREFIX_DISCOUNT_RATE, PREFIX_DISCOUNT_START);

        UpdateProductDescriptor updateProductDescriptor = new UpdateProductDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            updateProductDescriptor.setName(parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_CATEGORY).isPresent()) {
            updateProductDescriptor.setCategory(parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            updateProductDescriptor.setDescription(
                    parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            updateProductDescriptor.setPrice(parsePrice(argMultimap.getValue(PREFIX_PRICE).get()));
        }
        if (argMultimap.getValue(PREFIX_DISCOUNT_RATE).isPresent()) {
            updateProductDescriptor.setDiscountRate(
                    parseDiscountRate(argMultimap.getValue(PREFIX_DISCOUNT_RATE).get()));
        }
        if (argMultimap.getValue(PREFIX_DISCOUNT_START).isPresent()) {
            updateProductDescriptor.setDiscountStart(
                    parseDiscountStart(argMultimap.getValue(PREFIX_DISCOUNT_START).get()));
        }

        if (!updateProductDescriptor.isAnyFieldUpdated()) {
            throw new ParseException(UpdateAllCommand.MESSAGE_NOT_UPDATED);
        }

        return new UpdateAllCommand(updateProductDescriptor);
    }
}
