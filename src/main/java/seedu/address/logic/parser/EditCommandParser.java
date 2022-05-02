package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAILABILITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SENIORITY;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditCandidateDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

//@@author domlimm
/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_COURSE, PREFIX_SENIORITY, PREFIX_APPLICATION_STATUS,
                        PREFIX_AVAILABILITY);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditCandidateDescriptor editCandidateDescriptor = new EditCandidateDescriptor();

        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            editCandidateDescriptor.setStudentId(ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editCandidateDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editCandidateDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editCandidateDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_COURSE).isPresent()) {
            editCandidateDescriptor.setCourse(ParserUtil.parseCourse(argMultimap.getValue(PREFIX_COURSE).get()));
        }
        if (argMultimap.getValue(PREFIX_SENIORITY).isPresent()) {
            editCandidateDescriptor
                    .setSeniority(ParserUtil.parseSeniority(argMultimap.getValue(PREFIX_SENIORITY).get()));
        }

        if (argMultimap.getValue(PREFIX_APPLICATION_STATUS).isPresent()) {
            editCandidateDescriptor.setApplicationStatus(
                    ParserUtil.parseApplicationStatus(argMultimap.getValue(PREFIX_APPLICATION_STATUS).get()));
        }

        if (argMultimap.getValue(PREFIX_AVAILABILITY).isPresent()) {
            editCandidateDescriptor.setAvailability(
                    ParserUtil.parseAvailability(argMultimap.getValue(PREFIX_AVAILABILITY).get()));
        }

        if (!editCandidateDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editCandidateDescriptor);
    }
}
