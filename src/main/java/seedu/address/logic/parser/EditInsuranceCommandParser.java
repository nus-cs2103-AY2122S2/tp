package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditInsuranceCommand;
import seedu.address.logic.commands.EditInsuranceCommand.EditInsuranceDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditInsuranceCommand object
 */
public class EditInsuranceCommandParser implements Parser<EditInsuranceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditInsuranceCommand
     * and returns an EditInsuranceCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditInsuranceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_PRICE);

        Index index;

        try {
            index = InsuranceParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditInsuranceCommand.MESSAGE_USAGE), pe);
        }

        EditInsuranceDescriptor editInsuranceDescriptor = new EditInsuranceDescriptor();
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editInsuranceDescriptor.setTitle(InsuranceParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            editInsuranceDescriptor.setPrice(InsuranceParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get()));
        }


        if (!editInsuranceDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditInsuranceCommand.MESSAGE_NOT_EDITED);
        }

        return new EditInsuranceCommand(index, editInsuranceDescriptor);
    }

}
