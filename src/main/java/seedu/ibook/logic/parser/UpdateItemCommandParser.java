package seedu.ibook.logic.parser;

import static seedu.ibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.ibook.logic.parser.ParserUtil.parseCompoundIndex;
import static seedu.ibook.logic.parser.ParserUtil.parseExpiryDate;
import static seedu.ibook.logic.parser.ParserUtil.parseQuantity;

import seedu.ibook.commons.core.index.CompoundIndex;
import seedu.ibook.logic.commands.item.UpdateItemCommand;
import seedu.ibook.logic.commands.item.UpdateItemCommand.UpdateItemDescriptor;
import seedu.ibook.logic.parser.exceptions.ParseException;

public class UpdateItemCommandParser implements Parser<UpdateItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UpdateItemCommand
     * and returns an UpdateItemCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public UpdateItemCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EXPIRY_DATE, PREFIX_QUANTITY);

        CompoundIndex compoundIndex;

        try {
            compoundIndex = parseCompoundIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateItemCommand.MESSAGE_USAGE),
                    pe);
        }

        UpdateItemDescriptor updateItemDescriptor = new UpdateItemDescriptor();
        if (argMultimap.getValue(PREFIX_EXPIRY_DATE).isPresent()) {
            updateItemDescriptor.setExpiryDate(
                    parseExpiryDate(argMultimap.getValue(PREFIX_EXPIRY_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_QUANTITY).isPresent()) {
            updateItemDescriptor.setQuantity(
                    parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get()));
        }

        if (!updateItemDescriptor.isAnyFieldUpdated()) {
            throw new ParseException(UpdateItemCommand.MESSAGE_NOT_UPDATED);
        }

        return new UpdateItemCommand(compoundIndex, updateItemDescriptor);
    }
}
