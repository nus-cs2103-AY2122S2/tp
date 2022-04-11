package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLLECTION_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERYDATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditOrderCommand;
import seedu.address.logic.commands.EditOrderCommand.EditOrderDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
/**
 * Parses input arguments and creates a new EditOrderCommand object
 */
public class EditOrderCommandParser implements Parser<EditOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditPersonCommand
     * and returns an EditPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditOrderCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DETAILS, PREFIX_REMARK,
                        PREFIX_DELIVERYDATETIME, PREFIX_COLLECTION_TYPE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditOrderCommand.MESSAGE_USAGE),
                    pe
            );
        }

        EditOrderDescriptor editOrderDescriptor = new EditOrderDescriptor();

        if (argMultimap.getValue(PREFIX_DETAILS).isPresent()) {
            editOrderDescriptor.setDetails(ParserUtil.parseDetails(argMultimap.getAllValues(PREFIX_DETAILS)));
        }
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            editOrderDescriptor.setRemark(ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get()));
        }
        if (argMultimap.getValue(PREFIX_DELIVERYDATETIME).isPresent()) {
            editOrderDescriptor.setDeliveryDateTime(ParserUtil.parseDeliveryDateTime(
                    argMultimap.getValue(PREFIX_DELIVERYDATETIME).get()));
        }
        if (argMultimap.getValue(PREFIX_COLLECTION_TYPE).isPresent()) {
            editOrderDescriptor.setCollectionType(ParserUtil.parseCollectionType(
                    argMultimap.getValue(PREFIX_COLLECTION_TYPE).get()));
        }

        if (!editOrderDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditOrderCommand.MESSAGE_NOT_EDITED);
        }

        return new EditOrderCommand(index, editOrderDescriptor);
    }

}
