package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFORMATION;
import static seedu.address.logic.parser.DeleteProcCommandParser.GET_CLIENT_INDEX;
import static seedu.address.logic.parser.DeleteProcCommandParser.GET_PROCEDURE_INDEX;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditProcCommand;
import seedu.address.logic.commands.EditProcCommand.EditProcedureDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new EditProcCommand object
 */
public class EditProcCommandParser implements Parser<EditProcCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditProcCommand
     * and returns an EditProcCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditProcCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_INFORMATION, PREFIX_DATE, PREFIX_COST);

        List<Index> indexes = new ArrayList<>();

        try {
            indexes.addAll(ParserUtil.parseIndexes(argMultimap.getPreamble()));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditProcCommand.MESSAGE_USAGE), pe);
        }

        EditProcedureDescriptor editProcDescriptor = new EditProcedureDescriptor();
        if (argMultimap.getValue(PREFIX_INFORMATION).isPresent()) {
            editProcDescriptor.setInfo(
                    ParserUtil.parseInformation(argMultimap.getValue(PREFIX_INFORMATION).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editProcDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_COST).isPresent()) {
            editProcDescriptor.setCost(ParserUtil.parseCost(argMultimap.getValue(PREFIX_COST).get()));
        }

        if (!editProcDescriptor.isAnyFieldsEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        if (indexes.size() != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditProcCommand.MESSAGE_USAGE));
        }

        return new EditProcCommand(indexes.get(GET_CLIENT_INDEX),
                indexes.get(GET_PROCEDURE_INDEX), editProcDescriptor);
    }

}
