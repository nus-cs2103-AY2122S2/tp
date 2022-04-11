package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditDishCommand;
import seedu.address.logic.commands.EditDishCommand.EditDishDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditDishCommand object
 */
public class EditDishCommandParser implements Parser<EditDishCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditDishCommand
     * and returns an EditDishCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditDishCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PRICE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditDishCommand.MESSAGE_USAGE), pe);
        }

        EditDishDescriptor editDishDescriptor = new EditDishDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editDishDescriptor.setName(ParserUtil.parseDishName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            editDishDescriptor.setPrice(ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get()));
        }

        return new EditDishCommand(index, editDishDescriptor);
    }
}
