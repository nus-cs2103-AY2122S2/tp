package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_FIELD_NOT_EDITED;
import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT_BIND;
import static manageezpz.logic.parser.CliSyntax.PREFIX_EMAIL;
import static manageezpz.logic.parser.CliSyntax.PREFIX_NAME;
import static manageezpz.logic.parser.CliSyntax.PREFIX_PHONE;

import manageezpz.commons.core.index.Index;
import manageezpz.logic.commands.EditEmployeeCommand;
import manageezpz.logic.commands.EditEmployeeCommand.EditEmployeeDescriptor;
import manageezpz.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditEmployeeCommand object
 */
public class EditEmployeeCommandParser implements Parser<EditEmployeeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditEmployeeCommand
     * and returns an EditEmployeeCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditEmployeeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL);

        // Invalid command if getPreamble() is empty or contains other whitespaces
        if (argMultimap.getPreamble().isEmpty() || argMultimap.getPreamble().contains(" ")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT_BIND,
                    EditEmployeeCommand.MESSAGE_USAGE));
        }

        Index index;

        EditEmployeeDescriptor editEmployeeDescriptor = new EditEmployeeCommand.EditEmployeeDescriptor();

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());

            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                editEmployeeDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
            }

            if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
                editEmployeeDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
            }

            if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
                editEmployeeDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
            }
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage() + "\n\n" + EditEmployeeCommand.MESSAGE_USAGE, pe);
        }

        if (!editEmployeeDescriptor.isAnyFieldEdited()) {
            throw new ParseException(MESSAGE_FIELD_NOT_EDITED + EditEmployeeCommand.MESSAGE_USAGE);
        }

        return new EditEmployeeCommand(index, editEmployeeDescriptor);
    }
}
