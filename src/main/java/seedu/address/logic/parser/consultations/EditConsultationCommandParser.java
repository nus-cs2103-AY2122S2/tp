package seedu.address.logic.parser.consultations;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.consultation.EditConsultationCommand;
import seedu.address.logic.commands.consultation.EditConsultationCommand.EditConsultationDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditConsultationCommand object
 */
public class EditConsultationCommandParser implements Parser<EditConsultationCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditConsultationCommand
     * and returns an EditConsultationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditConsultationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NRIC, PREFIX_DATE,
                PREFIX_TIME, PREFIX_DIAGNOSIS, PREFIX_FEE, PREFIX_NOTES);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(Messages.MESSAGE_INVALID_CONSULTATION_INDEX);
        }

        if (argMultimap.getValue(PREFIX_NRIC).isPresent()) {
            throw new ParseException(EditConsultationCommand.MESSAGE_NRIC_EDIT_NOT_ALLOWED);
        }

        EditConsultationDescriptor editConsultationDescriptor =
                new EditConsultationDescriptor();

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editConsultationDescriptor.setDate(ParserUtil.parseDate(
                    argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_TIME).isPresent()) {
            editConsultationDescriptor.setTime(ParserUtil.parseTime(
                    argMultimap.getValue(PREFIX_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_DIAGNOSIS).isPresent()) {
            editConsultationDescriptor.setDiagnosis(ParserUtil.parseDiagnosis(
                    argMultimap.getValue(PREFIX_DIAGNOSIS).get()));
        }
        if (argMultimap.getValue(PREFIX_FEE).isPresent()) {
            editConsultationDescriptor.setFee(ParserUtil.parseFee(
                    argMultimap.getValue(PREFIX_FEE).get()));
        }
        if (argMultimap.getValue(PREFIX_NOTES).isPresent()) {
            editConsultationDescriptor.setNotes(ParserUtil.parseNotes(
                    argMultimap.getValue(PREFIX_NOTES).get()));
        }

        if (!editConsultationDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditConsultationCommand.MESSAGE_NOT_EDITED);
        }

        return new EditConsultationCommand(index, editConsultationDescriptor);
    }
}
