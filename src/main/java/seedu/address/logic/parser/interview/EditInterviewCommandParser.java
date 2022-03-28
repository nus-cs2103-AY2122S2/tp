package seedu.address.logic.parser.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.interview.EditInterviewCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class EditInterviewCommandParser implements Parser<EditInterviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditInterviewCommand
     * and returns an EditInterviewCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditInterviewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_APPLICANT, PREFIX_DATE, PREFIX_POSITION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditInterviewCommand.MESSAGE_USAGE), pe);
        }

        EditInterviewCommand.EditInterviewDescriptor editInterviewDescriptor =
                new EditInterviewCommand.EditInterviewDescriptor();
        if (argMultimap.getValue(PREFIX_APPLICANT).isPresent()) {
            editInterviewDescriptor.setApplicantIndex(ParserUtil
                    .parseIndex(argMultimap.getValue(PREFIX_APPLICANT).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editInterviewDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_POSITION).isPresent()) {
            editInterviewDescriptor.setPositionIndex(ParserUtil
                    .parseIndex(argMultimap.getValue(PREFIX_POSITION).get()));
        }

        if (!editInterviewDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditInterviewCommand.MESSAGE_NOT_EDITED);
        }

        return new EditInterviewCommand(index, editInterviewDescriptor);
    }
}
