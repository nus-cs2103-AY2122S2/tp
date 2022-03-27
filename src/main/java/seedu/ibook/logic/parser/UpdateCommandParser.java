package seedu.ibook.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_PRICE;

import seedu.ibook.commons.core.index.Index;
import seedu.ibook.logic.commands.UpdateCommand;
import seedu.ibook.logic.commands.UpdateCommand.UpdateProductDescriptor;
import seedu.ibook.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UpdateCommand object.
 */
public class UpdateCommandParser implements Parser<UpdateCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UpdateCommand
     * and returns an UpdateCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    @Override
    public UpdateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                        PREFIX_NAME, PREFIX_CATEGORY, PREFIX_PRICE, PREFIX_DESCRIPTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE), pe);
        }

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

        if (!updateProductDescriptor.isAnyFieldUpdated()) {
            throw new ParseException(UpdateCommand.MESSAGE_NOT_UPDATED);
        }

        return new UpdateCommand(index, updateProductDescriptor);
    }

}
