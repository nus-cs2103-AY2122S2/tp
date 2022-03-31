package seedu.ibook.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_DISCOUNTRATE;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_DISCOUNTSTART;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_PRICE;

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
                PREFIX_PRICE, PREFIX_DESCRIPTION, PREFIX_DISCOUNTRATE, PREFIX_DISCOUNTSTART);

        UpdateProductDescriptor updateProductDescriptor = new UpdateProductDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            updateProductDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_CATEGORY).isPresent()) {
            updateProductDescriptor.setCategory(ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            updateProductDescriptor.setDescription(
                    ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            updateProductDescriptor.setPrice(ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get()));
        }
        if (argMultimap.getValue(PREFIX_DISCOUNTRATE).isPresent()) {
            updateProductDescriptor.setDiscountRate(
                    ParserUtil.parseDiscountRate(argMultimap.getValue(PREFIX_DISCOUNTRATE).get()));
        }
        if (argMultimap.getValue(PREFIX_DISCOUNTSTART).isPresent()) {
            updateProductDescriptor.setDiscountStart(
                    ParserUtil.parseDiscountStart(argMultimap.getValue(PREFIX_DISCOUNTSTART).get()));
        }

        if (!updateProductDescriptor.isAnyFieldUpdated()) {
            throw new ParseException(UpdateAllCommand.MESSAGE_NOT_UPDATED);
        }

        return new UpdateAllCommand(updateProductDescriptor);
    }
}
