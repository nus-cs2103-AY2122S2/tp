package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REC_CLIENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REC_ENDDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REC_INSURANCEID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REC_STARTDATE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditRecordCommand;
import seedu.address.logic.commands.EditRecordCommand.EditRecordDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new EditRecordCommand object
 */
public class EditRecordCommandParser implements Parser<EditRecordCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditRecordCommand
     * and returns an EditRecordCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditRecordCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_REC_CLIENTID, PREFIX_REC_INSURANCEID,
                        PREFIX_REC_STARTDATE, PREFIX_REC_ENDDATE);

        Index index;

        try {
            index = RecordParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditRecordCommand.MESSAGE_USAGE), pe);
        }

        EditRecordDescriptor editRecordDescriptor = new EditRecordDescriptor();

        if (argMultimap.getValue(PREFIX_REC_CLIENTID).isPresent()) {
            editRecordDescriptor.setClientID((RecordParserUtil.parseClientID(
                    argMultimap.getValue(PREFIX_REC_CLIENTID).get())));
        }
        if (argMultimap.getValue(PREFIX_REC_INSURANCEID).isPresent()) {
            editRecordDescriptor.setInsuranceID((RecordParserUtil.parseInsuranceID(
                    argMultimap.getValue(PREFIX_REC_INSURANCEID).get())));
        }
        if (argMultimap.getValue(PREFIX_REC_STARTDATE).isPresent()) {
            editRecordDescriptor.setStartDate((RecordParserUtil.parseStartDate(
                    argMultimap.getValue(PREFIX_REC_STARTDATE).get())));
        }
        if (argMultimap.getValue(PREFIX_REC_ENDDATE).isPresent()) {
            editRecordDescriptor.setEndDate((RecordParserUtil.parseEndDate(
                    argMultimap.getValue(PREFIX_REC_ENDDATE).get())));
        }

        if (!editRecordDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditRecordCommand.MESSAGE_NOT_EDITED);
        }

        return new EditRecordCommand(index, editRecordDescriptor);

    }
}
