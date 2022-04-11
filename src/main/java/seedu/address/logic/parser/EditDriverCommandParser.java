package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditDriverCommand;
import seedu.address.logic.commands.EditDriverCommand.EditDriverDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new EditDriverCommand object
 */
public class EditDriverCommandParser implements Parser<EditDriverCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditDriverCommand
     * and returns an EditDriverCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditDriverCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_STATUS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditDriverCommand.MESSAGE_USAGE), pe);
        }

        EditDriverDescriptor editDriverDescriptor = new EditDriverDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editDriverDescriptor.setName(ParserUtil.parseNameDriver(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editDriverDescriptor.setPhone(ParserUtil.parsePhoneDriver(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editDriverDescriptor.setStatus(ParserUtil.parseDriverStatus(argMultimap.getValue(PREFIX_STATUS).get()));
        }

        if (!editDriverDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditDriverCommand.MESSAGE_NOT_EDITED);
        }

        return new EditDriverCommand(index, editDriverDescriptor);
    }
}
